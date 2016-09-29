package com.alberoframework.component.command.gateway;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.handler.CommandHandler;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;

public interface SimpleAuthenticatedCommandGateway {

	<C extends Command<R>, R> R handle(SimpleAuthenticatedRequestEnvelope<C, R> command);
	
	<ENV extends SimpleAuthenticatedRequestEnvelope<C, R>, C extends Command<R>, R> void registerHandler(Class<C> requestType, CommandHandler<ENV, C, R> handler);
}
