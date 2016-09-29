package com.alberoframework.component.query.testing;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.SimpleQueryGateway;
import com.alberoframework.component.query.handler.QueryHandler;
import com.alberoframework.component.request.contract.RequestEnvelope;

public class SimpleQueryGatewayStub extends AbstractQueryGatewayStub implements SimpleQueryGateway {

	@Override
	public <R> R handle(RequestEnvelope<? extends Query<R>, R> query) {
		return handleRequest(query);
	}
	
	@Override
	public <ENV extends RequestEnvelope<Q, R>, Q extends Query<R>, R> void registerHandler(
			Class<Q> requestType, QueryHandler<ENV, Q, R> handler) {
		super.registerHandler(requestType, handler);
	}
	
}
