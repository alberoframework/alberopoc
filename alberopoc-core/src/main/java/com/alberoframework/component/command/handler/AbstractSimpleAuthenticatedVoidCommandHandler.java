package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.lang.VoidUnit;

public abstract class AbstractSimpleAuthenticatedVoidCommandHandler<C extends Command<VoidUnit>> extends AbstractSimpleAuthenticatedContextualizedCommandHandler<C, VoidUnit> {

	@Override
	protected final VoidUnit handleInternal(SimpleAuthenticatedRequestEnvelope<C, VoidUnit> requestEnvelope,
			ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway) {
		doHandle(requestEnvelope.getRequest(), queryGateway, commandGateway);
		return VoidUnit.instance();
	}
	
	protected abstract void doHandle(C command,
			ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway);
	
}
