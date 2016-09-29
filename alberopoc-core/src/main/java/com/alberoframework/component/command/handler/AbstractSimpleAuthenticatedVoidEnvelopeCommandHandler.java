package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.lang.VoidUnit;

public abstract class AbstractSimpleAuthenticatedVoidEnvelopeCommandHandler<C extends Command<VoidUnit>> extends AbstractSimpleAuthenticatedContextualizedCommandHandler<C, VoidUnit> {

	@Override
	protected final VoidUnit handleInternal(SimpleAuthenticatedRequestEnvelope<C, VoidUnit> requestEnvelope,
			ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway) {
		doHandle(requestEnvelope, queryGateway, commandGateway);
		return VoidUnit.instance();
	}
	
	protected abstract void doHandle(SimpleAuthenticatedRequestEnvelope<C, VoidUnit> env,
			ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway);
	
}
