package com.alberoframework.component.query.gateway;

import java.util.List;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.handler.SimpleQueryHandler;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.gateway.SpringRequestHandlerScanner;
import com.alberoframework.component.request.handler.RequestHandlerWrapper;

public class SpringSimpleQueryGateway extends SimpleQueryGatewayImpl {

	protected SpringRequestHandlerScanner springHandlerScanner;
	
	protected List<Class<? extends RequestHandlerWrapper>> handlerWrapperTypes;
	
	public SpringSimpleQueryGateway(SpringRequestHandlerScanner springHandlerScanner, List<Class<? extends RequestHandlerWrapper>> handlerWrapperTypes) {
		this.springHandlerScanner = springHandlerScanner;
		this.handlerWrapperTypes = handlerWrapperTypes;
	}
	
	

	@Override
	public <R> R handle(RequestEnvelope<? extends Query<R>, R> query) {
		if(isEmpty()) {
			springHandlerScanner.scanAndRegisterHandlers(SimpleQueryHandler.class, handlerWrapperTypes, this);
		}
		return super.handle(query);
	}

}
