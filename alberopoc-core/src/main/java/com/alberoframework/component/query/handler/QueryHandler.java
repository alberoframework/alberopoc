package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.handler.RequestHandler;

public interface QueryHandler<ENV extends RequestEnvelope<Q, R>, Q extends Query<R>, R> extends RequestHandler<ENV, Q, R> {
	
}
