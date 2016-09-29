package com.alberoframework.component.query.gateway;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.handler.QueryHandler;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;

public interface SimpleAuthenticatedQueryGateway  {

	<Q extends Query<R>, R> R handle(SimpleAuthenticatedRequestEnvelope<Q, R> query);
	
	<ENV extends SimpleAuthenticatedRequestEnvelope<Q, R>, Q extends Query<R>, R> void registerHandler(Class<Q> requestType, QueryHandler<ENV, Q, R> handler);
	
}
