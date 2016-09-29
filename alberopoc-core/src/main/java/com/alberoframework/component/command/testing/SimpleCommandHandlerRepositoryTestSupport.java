package com.alberoframework.component.command.testing;

import java.util.Set;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.handler.SimpleCommandHandler;
import com.alberoframework.component.request.testing.RepositoryEntityTestingStore;
import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.CrudRepository;

public abstract class SimpleCommandHandlerRepositoryTestSupport<CH extends SimpleCommandHandler<C, R>, C extends Command<R>, R> extends AbstractSimpleCommandHandlerTestSupport<CH, C, R, RepositoryEntityTestingStore> {

	@Override
	protected RepositoryEntityTestingStore entityStore() {
		return new RepositoryEntityTestingStore(repositories());
	}
	
	protected abstract Set<CrudRepository<? extends Entity<?>, ?>> repositories();
	
}
