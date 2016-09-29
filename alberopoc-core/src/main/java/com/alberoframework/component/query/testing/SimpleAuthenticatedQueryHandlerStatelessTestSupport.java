package com.alberoframework.component.query.testing;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.handler.SimpleAuthenticatedQueryHandler;
import com.alberoframework.component.request.testing.EmptyEntityTestingStore;

public abstract class SimpleAuthenticatedQueryHandlerStatelessTestSupport<QH extends SimpleAuthenticatedQueryHandler<Q, R>, Q extends Query<R>, R> extends AbstractSimpleAuthenticatedQueryHandlerTestSupport<QH, Q, R, EmptyEntityTestingStore> {

	@Override
	protected EmptyEntityTestingStore entityStore() {
		return new EmptyEntityTestingStore();
	}
	
	
}
