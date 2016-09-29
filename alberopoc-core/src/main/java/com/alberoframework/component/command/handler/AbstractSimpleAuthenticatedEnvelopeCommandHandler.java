package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;

public abstract class AbstractSimpleAuthenticatedEnvelopeCommandHandler<C extends Command<R>, R> extends AbstractSimpleAuthenticatedContextualizedCommandHandler<C, R> {

	@Override
	protected final R handleInternal(SimpleAuthenticatedRequestEnvelope<C, R> requestEnvelope,
			ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway) {
		return doHandle(requestEnvelope, queryGateway, commandGateway);
	}
	
	protected abstract R doHandle(SimpleAuthenticatedRequestEnvelope<C, R> env,
			ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway);
	
}
