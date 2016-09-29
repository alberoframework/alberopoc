package com.alberoframework.type.conversion.converter;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.query.gateway.SimpleQueryGateway;
import com.alberoframework.component.request.contract.RequestEnvelope;

public abstract class AbstractSimpleTypeConverter<S, T> extends AbstractTypeConverter<S, T, SimpleQueryGateway> {

	@Override
	protected ContextualizedQueryGateway contextualizeQueryGateway(
			final SimpleQueryGateway queryGateway) {
		return new ContextualizedQueryGateway() {
			@Override
			public <R1> R1 handle(Query<R1> query) {
				return queryGateway.handle(new RequestEnvelope<Query<R1>, R1>(query));
			}
		};
	}

}
