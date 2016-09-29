package com.alberoframework.component.command.gateway;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.handler.CommandHandler;
import com.alberoframework.component.request.contract.RequestEnvelope;

public interface SimpleCommandGateway  {

	<R> R handle(RequestEnvelope<? extends Command<R>, R> command);
	
	<ENV extends RequestEnvelope<C, R>, C extends Command<R>, R> void registerHandler(Class<C> requestType, CommandHandler<ENV, C, R> handler);
	
}
