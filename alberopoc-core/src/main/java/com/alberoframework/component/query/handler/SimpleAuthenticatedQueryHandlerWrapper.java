package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.component.request.handler.SimpleAuthenticatedRequestHandlerWrapper;

public interface SimpleAuthenticatedQueryHandlerWrapper<QH extends SimpleAuthenticatedQueryHandler<Q, R>, Q extends Query<R>, R> extends AuthenticatedQueryHandlerWrapper<QH, SimpleAuthenticatedRequestEnvelope<Q, R>, String, Q, R>, SimpleAuthenticatedRequestHandlerWrapper<QH, Q, R> {

}
