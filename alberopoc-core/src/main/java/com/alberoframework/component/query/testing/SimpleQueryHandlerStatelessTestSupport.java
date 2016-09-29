package com.alberoframework.component.query.testing;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.handler.SimpleQueryHandler;
import com.alberoframework.component.request.testing.EmptyEntityTestingStore;

public abstract class SimpleQueryHandlerStatelessTestSupport<QH extends SimpleQueryHandler<Q, R>, Q extends Query<R>, R> extends AbstractSimpleQueryHandlerTestSupport<QH, Q, R, EmptyEntityTestingStore> {

	@Override
	protected EmptyEntityTestingStore entityStore() {
		return new EmptyEntityTestingStore();
	}
	
}
