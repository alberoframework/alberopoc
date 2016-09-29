package com.alberoframework.component.command.gateway;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.handler.CommandHandler;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.gateway.AbstractRequestGateway;

public class SimpleCommandGatewayImpl extends AbstractRequestGateway implements SimpleCommandGateway {

	@Override
	public <R> R handle(RequestEnvelope<? extends Command<R>, R> command) {
		return handleRequest(command);
	}

	@Override
	public <ENV extends RequestEnvelope<C, R>, C extends Command<R>, R> void registerHandler(Class<C> requestType,
			CommandHandler<ENV, C, R> handler) {
		super.registerHandler(requestType, handler);
	}
	
}
