package com.alberoframework.component.request.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.gateway.AbstractRequestGateway;
import com.alberoframework.core.validation.Validation;
import com.google.common.base.Optional;

public abstract class AbstractRequestGatewayStub extends AbstractRequestGateway {

	private Map<RequestEnvelope<? extends Request<?>, ?>, Object> requestStubs = new HashMap<RequestEnvelope<? extends Request<?>, ?>, Object>();
	
	private List<RequestEnvelope<? extends Request<?>, ?>> requestsReceived = new ArrayList<RequestEnvelope<? extends Request<?>, ?>>();
	
	@Override
	protected <ENV extends RequestEnvelope<REQ, RES>, REQ extends Request<RES>, RES> RES handleRequest(ENV request) {
		Optional<RES> response = stubResponse(request);
		if (!response.isPresent()) {
			return super.handleRequest(request);
		}
//		Validation.validate(response != null, IllegalArgumentException::new, "Received a request that was not stubbed: " + request);
		requestsReceived.add(request);
		return response.get();
	}
	
	protected void stubRequest(RequestEnvelope<? extends Request<?>, ?> request, Object response) {
		Validation.validate(request != null, IllegalArgumentException::new, "Request cannot be null");
		Validation.validate(response != null, IllegalArgumentException::new, "Respone cannot be null");
		requestStubs.put(request, response);
	}
	
	protected List<? extends RequestEnvelope<? extends Request<?>, ?>> requestsReceived() {
		return requestsReceived;
	}
	
	@SuppressWarnings("unchecked")
	protected <ENV extends RequestEnvelope<REQ, RES>, REQ extends Request<RES>, RES> Optional<RES> stubResponse(ENV request) {
		return Optional.fromNullable((RES) requestStubs.get(request));
	}
	
}
