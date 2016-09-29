package com.alberoframework.component.query.testing;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;
import com.alberoframework.component.query.handler.QueryHandler;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;

public class SimpleAuthenticatedQueryGatewayStub extends AbstractQueryGatewayStub implements SimpleAuthenticatedQueryGateway {

	@Override
	public <Q extends Query<R>, R> R handle(SimpleAuthenticatedRequestEnvelope<Q, R> query) {
		return handleRequest(query);
	}
	
	@Override
	public <ENV extends SimpleAuthenticatedRequestEnvelope<Q, R>, Q extends Query<R>, R> void registerHandler(
			Class<Q> requestType, QueryHandler<ENV, Q, R> handler) {
		super.registerHandler(requestType, handler);
	}
	
}
