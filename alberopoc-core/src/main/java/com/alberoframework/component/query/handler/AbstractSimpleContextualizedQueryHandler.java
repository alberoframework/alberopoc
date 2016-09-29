package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.query.gateway.SimpleQueryGateway;
import com.alberoframework.component.request.contract.RequestEnvelope;

public abstract class AbstractSimpleContextualizedQueryHandler<Q extends Query<R>, R> extends AbstractQueryHandler<RequestEnvelope<Q,R>, Q, R, SimpleQueryGateway> implements SimpleQueryHandler<Q, R> {
	
	@Override
	protected final ContextualizedQueryGateway contextualizeQueryGateway(RequestEnvelope<Q, R> requestEnvelope,
			final SimpleQueryGateway queryGateway) {
		return new ContextualizedQueryGateway() {
			
			@Override
			public <R1> R1 handle(Query<R1> query) {
				return queryGateway.handle(new RequestEnvelope<Query<R1>, R1>(query));
			}
		};
	}

}
