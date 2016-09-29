package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.handler.RequestHandlerWrapper;

public interface QueryHandlerWrapper<QH extends QueryHandler<ENV, Q, R>, ENV extends RequestEnvelope<Q, R>, Q extends Query<R>, R> extends RequestHandlerWrapper<QH, ENV, Q, R> {

}
