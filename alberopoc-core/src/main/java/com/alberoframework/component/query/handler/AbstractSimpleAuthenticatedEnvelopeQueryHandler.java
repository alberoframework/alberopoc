package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;

public abstract class AbstractSimpleAuthenticatedEnvelopeQueryHandler<Q extends Query<R>, R> extends AbstractSimpleAuthenticatedContextualizedQueryHandler<Q, R> {

	@Override
	protected R handleInternal(SimpleAuthenticatedRequestEnvelope<Q, R> requestEnvelope,
			ContextualizedQueryGateway queryGateway) {
		return doHandle(requestEnvelope, queryGateway);
	}
	
	
	protected abstract R doHandle(SimpleAuthenticatedRequestEnvelope<Q, R> env,
			ContextualizedQueryGateway queryGateway);
	
}
