package com.alberoframework.component.request.gateway;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.handler.RequestHandler;
import com.alberoframework.core.validation.Validation;

public abstract class AbstractRequestGateway {

	private Map<Class<?>, Object> requestHandlerMap = new HashMap<Class<?>, Object>();
	
	protected <ENV extends RequestEnvelope<REQ, RES>, REQ extends Request<RES>, RES> void registerHandler(Class<REQ> requestType, RequestHandler<ENV, REQ, RES> handler) {
		requestHandlerMap.put(requestType, handler);
	}
	
	protected <ENV extends RequestEnvelope<REQ, RES>, REQ extends Request<RES>, RES> RES handleRequest(ENV request) {
		Optional<RequestHandler<ENV, REQ, RES>> handler = requestHandler(request.getRequest().getClass());
		Validation.validate(handler.isPresent(), IllegalArgumentException::new, "Cannot handle Request: " + request + ". Request type not registered: " + request.getRequest().getClass());
		return handler.get().handle(request);
	}
	
	@SuppressWarnings("unchecked")
	protected <ENV extends RequestEnvelope<REQ, RES>, REQ extends Request<RES>, RES> Optional<RequestHandler<ENV, REQ, RES>> requestHandler(Class<?> requestType) {
		return Optional.ofNullable((RequestHandler<ENV, REQ, RES>)requestHandlerMap.get(requestType));
	}
	
	protected Boolean isEmpty(){
		return requestHandlerMap.isEmpty();
	}
	
}
