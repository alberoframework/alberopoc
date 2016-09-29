package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.component.request.handler.SimpleAuthenticatedRequestHandler;

public interface SimpleAuthenticatedQueryHandler<Q extends Query<R>, R> extends AuthenticatedQueryHandler<SimpleAuthenticatedRequestEnvelope<Q, R>, String, Q, R>, SimpleAuthenticatedRequestHandler<Q, R> {

}
