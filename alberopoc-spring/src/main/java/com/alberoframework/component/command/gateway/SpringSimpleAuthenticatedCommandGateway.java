package com.alberoframework.component.command.gateway;

import java.util.ArrayList;
import java.util.List;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.handler.SimpleAuthenticatedCommandHandler;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.component.request.gateway.SpringRequestHandlerScanner;
import com.alberoframework.component.request.handler.RequestHandlerWrapper;

public class SpringSimpleAuthenticatedCommandGateway extends SimpleAuthenticatedCommandGatewayImpl {
	
	protected SpringRequestHandlerScanner springHandlerScanner;

	protected List<Class<? extends RequestHandlerWrapper>> handlerWrapperTypes;
	
	public SpringSimpleAuthenticatedCommandGateway(SpringRequestHandlerScanner springHandlerScanner, List<Class<? extends RequestHandlerWrapper>> handlerWrapperTypes) {
		this.springHandlerScanner = springHandlerScanner;
		this.handlerWrapperTypes = handlerWrapperTypes;
	}
	
	public SpringSimpleAuthenticatedCommandGateway(SpringRequestHandlerScanner springHandlerScanner) {
		this(springHandlerScanner, new ArrayList<>());
	}

	@Override
	public <C extends Command<R>, R> R handle(SimpleAuthenticatedRequestEnvelope<C, R> command) {
		if(isEmpty()) {
			springHandlerScanner.scanAndRegisterHandlers(SimpleAuthenticatedCommandHandler.class, handlerWrapperTypes, this);
		}
		return super.handle(command);
	}

}
