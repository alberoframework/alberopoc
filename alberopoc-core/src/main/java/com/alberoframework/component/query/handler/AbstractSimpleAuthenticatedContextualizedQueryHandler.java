package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;

public abstract class AbstractSimpleAuthenticatedContextualizedQueryHandler<Q extends Query<R>, R> extends AbstractAuthenticatedQueryHandler<SimpleAuthenticatedRequestEnvelope<Q, R>, String, Q, R, SimpleAuthenticatedQueryGateway> implements SimpleAuthenticatedQueryHandler<Q, R> {

	@Override
	protected ContextualizedQueryGateway contextualizeQueryGateway(final SimpleAuthenticatedRequestEnvelope<Q, R> requestEnvelope,
			final SimpleAuthenticatedQueryGateway queryGateway) {
		return new ContextualizedQueryGateway() {
			
			@Override
			public <R1> R1 handle(Query<R1> query) {
				if (requestEnvelope.userId().isPresent())
					return queryGateway.handle(new SimpleAuthenticatedRequestEnvelope<Query<R1>, R1>(requestEnvelope.userId().get(), query));
				return queryGateway.handle(new SimpleAuthenticatedRequestEnvelope<Query<R1>, R1>(query));
			}
		};
	}
	
}
