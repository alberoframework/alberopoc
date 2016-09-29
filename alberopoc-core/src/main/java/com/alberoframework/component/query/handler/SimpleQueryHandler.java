package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.handler.SimpleRequestHandler;

public interface SimpleQueryHandler<Q extends Query<R>, R> extends QueryHandler<RequestEnvelope<Q, R>, Q, R>, SimpleRequestHandler<Q, R> {

}
