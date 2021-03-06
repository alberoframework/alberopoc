package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.lang.VoidUnit;

public abstract class AbstractSimpleVoidEnvelopeCommandHandler<C extends Command<VoidUnit>> extends AbstractSimpleContextualizedCommandHandler<C, VoidUnit> {

	@Override
	protected VoidUnit handleInternal(RequestEnvelope<C, VoidUnit> requestEnvelope, ContextualizedQueryGateway queryGateway,
			ContextualizedCommandGateway commandGateway) {
		doHandle(requestEnvelope, queryGateway, commandGateway);
		return VoidUnit.instance();
	}
	
	protected abstract void doHandle(RequestEnvelope<C, VoidUnit> requestEnvelope, ContextualizedQueryGateway queryGateway,
			ContextualizedCommandGateway commandGateway);
	
}
