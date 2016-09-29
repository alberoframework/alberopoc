package com.alberoframework.component.query.testing;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;
import com.alberoframework.component.query.handler.SimpleAuthenticatedQueryHandler;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.component.request.testing.EntityTestingStore;

public abstract class AbstractSimpleAuthenticatedQueryHandlerTestSupport<QH extends SimpleAuthenticatedQueryHandler<Q, R>, Q extends Query<R>, R, ES extends EntityTestingStore> extends AbstractAuthenticatedQueryHandlerTestSupport<QH, SimpleAuthenticatedRequestEnvelope<Q, R>, String, Q, R, SimpleAuthenticatedQueryGateway, ES> {

//	public QueryHandlerTestOperation<QH, SimpleAuthenticatedRequestEnvelope<Q, R>, Q, R> handle(Q query, String userId) {
//		return handle(query(query, userId));
//	}
	
	public <Q1 extends Query<R1>, R1> SimpleAuthenticatedRequestEnvelope<Q1, R1> query(Q1 query, String userId) {
		return new SimpleAuthenticatedRequestEnvelope<Q1, R1>(userId, query);
	}
	
	public <Q1 extends Query<R1>, R1> SimpleAuthenticatedRequestEnvelope<Q1, R1> query(Q1 query) {
		return new SimpleAuthenticatedRequestEnvelope<Q1, R1>(query);
	}
	
	@Override
	protected SimpleAuthenticatedQueryGateway queryGateway() {
		return new SimpleAuthenticatedQueryGatewayStub();
	}
	
}
