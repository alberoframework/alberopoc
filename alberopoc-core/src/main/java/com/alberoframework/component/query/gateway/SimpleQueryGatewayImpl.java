package com.alberoframework.component.query.gateway;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.handler.QueryHandler;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.gateway.AbstractRequestGateway;

public class SimpleQueryGatewayImpl extends AbstractRequestGateway implements SimpleQueryGateway {

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
