package com.alberoframework.type.conversion.testing;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.SimpleQueryGateway;
import com.alberoframework.component.query.testing.SimpleQueryGatewayStub;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.type.conversion.converter.AbstractSimpleTypeConverter;

public class SimpleTypeConverterTestSupport<C extends AbstractSimpleTypeConverter<S, T>, S, T> extends AbstractTypeConverterTestSupport<C, S, T, SimpleQueryGateway> {

	public <Q1 extends Query<R1>, R1> RequestEnvelope<Q1, R1> query(Q1 query) {
		return new RequestEnvelope<Q1, R1>(query);
	}
	
	@Override
	protected SimpleQueryGateway queryGateway() {
		return new SimpleQueryGatewayStub();
	}
	
}
