package com.alberoframework.type.conversion.testing;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;
import com.alberoframework.component.query.testing.SimpleAuthenticatedQueryGatewayStub;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.type.conversion.converter.AbstractSimpleAuthenticatedTypeConverter;

public class SimpleAuthenticatedTypeConverterTestSupport<C extends AbstractSimpleAuthenticatedTypeConverter<S, T>, S, T> extends AbstractTypeConverterTestSupport<C, S, T, SimpleAuthenticatedQueryGateway> {

	public <Q1 extends Query<R1>, R1> SimpleAuthenticatedRequestEnvelope<Q1, R1> query(Q1 query) {
		return SimpleAuthenticatedRequestEnvelope.envelopeWithSystemUser(query);
	}
	
	@Override
	protected SimpleAuthenticatedQueryGateway queryGateway() {
		return new SimpleAuthenticatedQueryGatewayStub();
	}
	
}
