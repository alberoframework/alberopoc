package com.alberoframework.component.command.testing;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.SimpleCommandGateway;
import com.alberoframework.component.command.handler.CommandHandler;
import com.alberoframework.component.request.contract.RequestEnvelope;

public class SimpleCommandGatewayStub extends AbstractCommandGatewayStub implements SimpleCommandGateway {

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
