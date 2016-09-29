package com.alberoframework.component.command.gateway;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.handler.CommandHandler;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.component.request.gateway.AbstractRequestGateway;

public class SimpleAuthenticatedCommandGatewayImpl extends AbstractRequestGateway implements SimpleAuthenticatedCommandGateway {
	
	@Override
	public <C extends Command<R>, R> R handle(SimpleAuthenticatedRequestEnvelope<C, R> command) {
		return handleRequest(command);
	}

	@Override
	public <ENV extends SimpleAuthenticatedRequestEnvelope<C, R>, C extends Command<R>, R> void registerHandler(Class<C> requestType,
			CommandHandler<ENV, C, R> handler) {
		super.registerHandler(requestType, handler);
	}
	
}
