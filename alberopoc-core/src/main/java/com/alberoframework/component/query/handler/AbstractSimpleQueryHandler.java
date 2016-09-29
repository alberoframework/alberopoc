package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.RequestEnvelope;

public abstract class AbstractSimpleQueryHandler<Q extends Query<R>, R> extends AbstractSimpleContextualizedQueryHandler<Q, R> { 

	@Override
	protected R handleInternal(RequestEnvelope<Q, R> requestEnvelope,
			ContextualizedQueryGateway queryGateway) {
		return doHandle(requestEnvelope.getRequest(), queryGateway);
	}
	
	protected abstract R doHandle(Q query, ContextualizedQueryGateway queryGateway);
	
}
