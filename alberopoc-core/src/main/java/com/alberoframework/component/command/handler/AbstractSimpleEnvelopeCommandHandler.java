package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.RequestEnvelope;

public abstract class AbstractSimpleEnvelopeCommandHandler<C extends Command<R>, R> extends AbstractSimpleContextualizedCommandHandler<C, R> {

	@Override
	protected R handleInternal(RequestEnvelope<C, R> requestEnvelope, ContextualizedQueryGateway queryGateway,
			ContextualizedCommandGateway commandGateway) {
		return doHandle(requestEnvelope, queryGateway, commandGateway);
	}
	
	protected abstract R doHandle(RequestEnvelope<C, R> requestEnvelope, ContextualizedQueryGateway queryGateway,
			ContextualizedCommandGateway commandGateway);
	
}
