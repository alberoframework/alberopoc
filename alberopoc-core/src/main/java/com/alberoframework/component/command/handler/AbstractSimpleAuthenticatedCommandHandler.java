package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;

public abstract class AbstractSimpleAuthenticatedCommandHandler<C extends Command<R>, R> extends AbstractSimpleAuthenticatedContextualizedCommandHandler<C, R> {

	@Override
	protected final R handleInternal(SimpleAuthenticatedRequestEnvelope<C, R> requestEnvelope,
			ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway) {
		return doHandle(requestEnvelope.getRequest(), queryGateway, commandGateway);
	}
	
	protected abstract R doHandle(C command,
			ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway);
	
}
