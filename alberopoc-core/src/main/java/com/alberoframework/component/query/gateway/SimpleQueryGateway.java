package com.alberoframework.component.query.gateway;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.handler.QueryHandler;
import com.alberoframework.component.request.contract.RequestEnvelope;

public interface SimpleQueryGateway {

	<R> R handle(RequestEnvelope<? extends Query<R>, R> query);
	
	<ENV extends RequestEnvelope<Q, R>, Q extends Query<R>, R> void registerHandler(Class<Q> requestType, QueryHandler<ENV, Q, R> handler);

}
