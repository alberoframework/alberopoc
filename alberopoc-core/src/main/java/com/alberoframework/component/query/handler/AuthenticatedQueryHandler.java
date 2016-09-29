package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.request.contract.AuthenticatedRequestEnvelope;
import com.alberoframework.component.request.handler.AuthenticatedRequestHandler;

public interface AuthenticatedQueryHandler<ENV extends AuthenticatedRequestEnvelope<UID, Q, R>, UID, Q extends Query<R>, R> extends AuthenticatedRequestHandler<ENV, UID, Q, R>, QueryHandler<ENV, Q, R> {

}
