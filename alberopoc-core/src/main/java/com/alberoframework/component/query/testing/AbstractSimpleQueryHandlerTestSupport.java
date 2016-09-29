package com.alberoframework.component.query.testing;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.SimpleQueryGateway;
import com.alberoframework.component.query.handler.SimpleQueryHandler;
import com.alberoframework.component.query.testing.QueryHandlerTestOperations.QueryHandlerTestOperation;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.testing.EntityTestingStore;

public abstract class AbstractSimpleQueryHandlerTestSupport<QH extends SimpleQueryHandler<Q, R>, Q extends Query<R>, R, ES extends EntityTestingStore> extends AbstractQueryHandlerTestSupport<QH, RequestEnvelope<Q, R>, Q, R, SimpleQueryGateway, ES> {

//	public QueryHandlerTestOperation<QH, RequestEnvelope<Q, R>, Q, R> sendQuery(Q query) {
//		return handle(query(query));
//	}
	
	public <Q1 extends Query<R1>, R1> RequestEnvelope<Q1, R1> query(Q1 query) {
		return new RequestEnvelope<Q1, R1>(query);
	}
	
	@Override
	protected SimpleQueryGateway queryGateway() {
		return new SimpleQueryGatewayStub();
	}
	
}
