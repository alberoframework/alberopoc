package com.alberoframework.component.query.testing;

import java.util.Set;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.handler.SimpleQueryHandler;
import com.alberoframework.component.request.testing.RepositoryEntityTestingStore;
import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.CrudRepository;

public abstract class SimpleQueryHandlerRepositoryTestSupport<QH extends SimpleQueryHandler<Q, R>, Q extends Query<R>, R> extends AbstractSimpleQueryHandlerTestSupport<QH, Q, R, RepositoryEntityTestingStore> {

	@Override
	protected RepositoryEntityTestingStore entityStore() {
		return new RepositoryEntityTestingStore(repositories());
	}
	
	protected abstract Set<CrudRepository<? extends Entity<?>, ?>> repositories();
	
}
