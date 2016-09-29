package com.alberoframework.component.query.testing;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.handler.AuthenticatedQueryHandler;
import com.alberoframework.component.request.contract.AuthenticatedRequestEnvelope;
import com.alberoframework.component.request.testing.EntityTestingStore;

public abstract class AbstractAuthenticatedQueryHandlerTestSupport<QH extends AuthenticatedQueryHandler<ENV, UID, Q, R>, ENV extends AuthenticatedRequestEnvelope<UID, Q, R>, UID, Q extends Query<R>, R, QG, ES extends EntityTestingStore> extends AbstractQueryHandlerTestSupport<QH, ENV, Q, R, QG, ES> {

}
