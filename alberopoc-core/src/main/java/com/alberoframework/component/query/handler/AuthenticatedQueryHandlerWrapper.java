package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.request.contract.AuthenticatedRequestEnvelope;
import com.alberoframework.component.request.handler.AuthenticatedRequestHandlerWrapper;

public interface AuthenticatedQueryHandlerWrapper<QH extends AuthenticatedQueryHandler<ENV, UID, Q, R>, ENV extends AuthenticatedRequestEnvelope<UID, Q, R>, UID, Q extends Query<R>, R> extends QueryHandlerWrapper<QH, ENV, Q, R>, AuthenticatedRequestHandlerWrapper<QH, ENV, UID, Q, R> {

}
