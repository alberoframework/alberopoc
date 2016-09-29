package com.alberoframework.component.command.gateway;

import java.util.List;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.handler.SimpleCommandHandler;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.gateway.SpringRequestHandlerScanner;
import com.alberoframework.component.request.handler.RequestHandlerWrapper;

public class SpringSimpleCommandGateway extends SimpleCommandGatewayImpl {
	
	protected SpringRequestHandlerScanner springHandlerScanner;
	
	protected List<Class<? extends RequestHandlerWrapper>> handlerWrapperTypes;
	
	public SpringSimpleCommandGateway(SpringRequestHandlerScanner springHandlerScanner, List<Class<? extends RequestHandlerWrapper>> handlerWrapperTypes) {
		this.springHandlerScanner = springHandlerScanner;
		this.handlerWrapperTypes = handlerWrapperTypes;
	}

	@Override
	public <R> R handle(RequestEnvelope<? extends Command<R>, R> command) {
		if(isEmpty()) {
			springHandlerScanner.scanAndRegisterHandlers(SimpleCommandHandler.class, handlerWrapperTypes, this);
		}
		return super.handle(command);
	}

}
