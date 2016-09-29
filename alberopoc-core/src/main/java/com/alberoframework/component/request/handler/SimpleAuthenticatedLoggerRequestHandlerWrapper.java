package com.alberoframework.component.request.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;

public class SimpleAuthenticatedLoggerRequestHandlerWrapper implements SimpleAuthenticatedRequestHandlerWrapper<SimpleAuthenticatedRequestHandler<Request<Object>, Object>, Request<Object>, Object> {

	private static final Logger logger = LoggerFactory.getLogger(SimpleAuthenticatedLoggerRequestHandlerWrapper.class);
	
	@Override
	public SimpleAuthenticatedRequestHandler<Request<Object>, Object> wrap(
			RequestHandler<? super SimpleAuthenticatedRequestEnvelope<Request<Object>, Object>, ? super Request<Object>, ? super Object> requestHandler) {
		return new SimpleAuthenticatedRequestHandler<Request<Object>, Object>() {
			
			@Override
			public Object handle(SimpleAuthenticatedRequestEnvelope<Request<Object>, Object> requestEnvelope) {
				logger.info("-----Processing Request of type: " + requestEnvelope.getRequest().getClass().getSimpleName() + " id: " + requestEnvelope.hashCode() + "  content: " + requestEnvelope);
				Object response = requestHandler.handle(requestEnvelope);
				logger.info("-----Request Processed successfully, type: " + requestEnvelope.getRequest().getClass().getSimpleName() + " id: " + requestEnvelope.hashCode() + " response: " + response);
				return response;
			}
		};
	}
	
}
