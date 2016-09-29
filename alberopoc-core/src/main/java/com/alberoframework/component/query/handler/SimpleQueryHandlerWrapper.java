package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.handler.SimpleRequestHandlerWrapper;

public interface SimpleQueryHandlerWrapper<QH extends SimpleQueryHandler<Q, R>, Q extends Query<R>, R> extends QueryHandlerWrapper<QH, RequestEnvelope<Q, R>, Q, R>, SimpleRequestHandlerWrapper<QH, Q, R> {

}
