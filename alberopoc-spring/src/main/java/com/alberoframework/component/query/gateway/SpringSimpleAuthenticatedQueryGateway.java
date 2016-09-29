package com.alberoframework.component.query.gateway;

import java.util.ArrayList;
import java.util.List;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.handler.SimpleAuthenticatedQueryHandler;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.component.request.gateway.SpringRequestHandlerScanner;
import com.alberoframework.component.request.handler.RequestHandlerWrapper;

public class SpringSimpleAuthenticatedQueryGateway extends SimpleAuthenticatedQueryGatewayImpl {

	protected SpringRequestHandlerScanner springHandlerScanner;
	
	protected List<Class<? extends RequestHandlerWrapper>> handlerWrapperTypes;
	
	public SpringSimpleAuthenticatedQueryGateway(SpringRequestHandlerScanner springHandlerScanner, List<Class<? extends RequestHandlerWrapper>> handlerWrapperTypes) {
		this.springHandlerScanner = springHandlerScanner;
		this.handlerWrapperTypes = handlerWrapperTypes;
	}
	
	

	public SpringSimpleAuthenticatedQueryGateway(SpringRequestHandlerScanner springHandlerScanner) {
		this(springHandlerScanner, new ArrayList<>());
	}



	@Override
	public <Q extends Query<R>, R> R handle(SimpleAuthenticatedRequestEnvelope<Q, R> query) {
		if(isEmpty()) {
			springHandlerScanner.scanAndRegisterHandlers(SimpleAuthenticatedQueryHandler.class, handlerWrapperTypes, this);
		}
		return super.handle(query);
	}

}
