package com.alberoframework.component.query.testing;

import java.util.Set;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.handler.SimpleAuthenticatedQueryHandler;
import com.alberoframework.component.request.testing.RepositoryEntityTestingStore;
import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.CrudRepository;

public abstract class SimpleAuthenticatedQueryHandlerRepositoryTestSupport<QH extends SimpleAuthenticatedQueryHandler<Q, R>, Q extends Query<R>, R> extends AbstractSimpleAuthenticatedQueryHandlerTestSupport<QH, Q, R, RepositoryEntityTestingStore> {

	@Override
	protected RepositoryEntityTestingStore entityStore() {
		return new RepositoryEntityTestingStore(repositories());
	}
	
	protected abstract Set<CrudRepository<? extends Entity<?>, ?>> repositories();
	
}
