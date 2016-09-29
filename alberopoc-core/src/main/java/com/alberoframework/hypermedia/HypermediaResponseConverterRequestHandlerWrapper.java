package com.alberoframework.hypermedia;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.alberoframework.component.authorization.gateway.SimpleAuthorizationGateway;
import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.component.request.handler.RequestHandler;
import com.alberoframework.component.request.handler.SimpleAuthenticatedRequestHandler;
import com.alberoframework.component.request.handler.SimpleAuthenticatedRequestHandlerWrapper;
import com.alberoframework.component.url.RequestUrlMapper;
import com.alberoframework.core.iterable.Iterables;
import com.alberoframework.lang.object.BaseBeanUtils;

public class HypermediaResponseConverterRequestHandlerWrapper implements SimpleAuthenticatedRequestHandlerWrapper<SimpleAuthenticatedRequestHandler<Request<Object>, Object>, Request<Object>, Object> {

	private RequestUrlMapper requestUrlMapper;
	
	private SimpleAuthorizationGateway authorizationGateway;
	
	public HypermediaResponseConverterRequestHandlerWrapper(RequestUrlMapper requestUrlMapper, SimpleAuthorizationGateway authorizationGateway) {
		this.requestUrlMapper = requestUrlMapper;
		this.authorizationGateway = authorizationGateway;
	}
	
	@Override
	public SimpleAuthenticatedRequestHandler<Request<Object>, Object> wrap(
			RequestHandler<? super SimpleAuthenticatedRequestEnvelope<Request<Object>, Object>, ? super Request<Object>, ? super Object> requestHandler) {
		return new SimpleAuthenticatedRequestHandler<Request<Object>, Object>() {
			@SuppressWarnings("unchecked")
			@Override
			public Object handle(SimpleAuthenticatedRequestEnvelope<Request<Object>, Object> envelope) {
				Object responseOrig = requestHandler.handle(envelope);
				Object response = responseOrig;
				if (response instanceof Optional) {
					Optional<Object> responseOpt = (Optional<Object>) response;
					if (!responseOpt.isPresent() || !(responseOpt.get() instanceof HypermediaResource))
						return responseOpt;
					
					response = responseOpt.get();
				}
				
				if (!(response instanceof HypermediaResource)) 
					return response;
					
				
				HypermediaResource<?, ?> resource = (HypermediaResource<?, ?>) response;
				resource = resource.withLink("_self", envelope.getRequest());
				
				response = enrichHypermediaResource(envelope, resource);
				
				return (responseOrig instanceof Optional) ? Optional.of(response) : response;
			}
		};
	}

	private HypermediaResource<?, ?> enrichHypermediaResource(SimpleAuthenticatedRequestEnvelope<Request<Object>, Object> envelope, HypermediaResource<?, ?> resource) {
		Map<String, HypermediaLink> links = new HashMap<>(resource.getLinks());
		for (Map.Entry<String, HypermediaLink> linkEntry : links.entrySet()) {
			HypermediaLink link = linkEntry.getValue();
			//templates do not have to be authorized
			boolean authorized = (link.isTemplate()) ? true : authorizationGateway.authorized(envelope.withNewRequest(link.getRequest()));
			if (!authorized) {
				resource = resource.withLinkRemoved(link.getRel());
				continue;
			}
			if (link.getRequest() != null) {
				link.setUrl(requestUrlMapper.resolveUrl(link.getRequest()));
			}
			resource = resource.withLink(link);
		}
		
		if (resource instanceof HypermediaObjectCollectionResource) {
			HypermediaObjectCollectionResource<Object> listResource = (HypermediaObjectCollectionResource<Object>) resource;
			List<HypermediaObjectResource<Object>> elements = Iterables.stream(listResource.getElements())
						.filter(e -> e instanceof HypermediaObjectResource)
						.map(e -> (HypermediaObjectResource<Object>) enrichHypermediaResource(envelope, (HypermediaObjectResource<Object>) e))
						.collect(Collectors.toList());
			
			resource = listResource.withElements(elements);
			
		}
		
		if (resource instanceof HypermediaObjectResource) {
			HypermediaObjectResource<Object> objectResource = (HypermediaObjectResource<Object>) resource;
			BaseBeanUtils.updatePropertiesOfType(objectResource.getValue(), HypermediaResource.class, o -> (o != null) ? enrichHypermediaResource(envelope, o) : o);
		}
		
		return resource;
	}
	
}
