package com.alberoframework.component.command.testing;

import java.util.Set;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.handler.SimpleAuthenticatedCommandHandler;
import com.alberoframework.component.request.testing.RepositoryEntityTestingStore;
import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.CrudRepository;

public abstract class SimpleAuthenticatedCommandHandlerRepositoryTestSupport<CH extends SimpleAuthenticatedCommandHandler<C, R>, C extends Command<R>, R> extends AbstractSimpleAuthenticatedCommandHandlerTestSupport<CH, C, R, RepositoryEntityTestingStore> {

	@Override
	protected RepositoryEntityTestingStore entityStore() {
		return new RepositoryEntityTestingStore(repositories());
	}
	
	protected abstract Set<CrudRepository<? extends Entity<?>, ?>> repositories();
	
}
