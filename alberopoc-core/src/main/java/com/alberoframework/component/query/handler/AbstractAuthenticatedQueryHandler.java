package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.request.contract.AuthenticatedRequestEnvelope;

public abstract class AbstractAuthenticatedQueryHandler<ENV extends AuthenticatedRequestEnvelope<UID, Q, R>, UID, Q extends Query<R>, R, QG> extends AbstractQueryHandler<ENV, Q, R, QG> implements AuthenticatedQueryHandler<ENV, UID, Q, R> {

}
