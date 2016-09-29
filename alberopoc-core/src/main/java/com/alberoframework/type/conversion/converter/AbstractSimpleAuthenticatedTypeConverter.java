package com.alberoframework.type.conversion.converter;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;

public abstract class AbstractSimpleAuthenticatedTypeConverter<S, T> extends AbstractTypeConverter<S, T, SimpleAuthenticatedQueryGateway> {

	@Override
	protected ContextualizedQueryGateway contextualizeQueryGateway(
			final SimpleAuthenticatedQueryGateway queryGateway) {
		return new ContextualizedQueryGateway() {
			@Override
			public <R1> R1 handle(Query<R1> query) {
				return queryGateway.handle(SimpleAuthenticatedRequestEnvelope.envelopeWithSystemUser(query));
			}
		};
	}

}
