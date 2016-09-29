package com.alberoframework.component.command.testing;

import java.util.Map;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.handler.SimpleAuthenticatedCommandHandler;
import com.alberoframework.component.request.testing.SpringDataEntityTestingStore;
import com.alberoframework.core.reflection.Reflection;
import com.alberoframework.domain.entity.contract.Entity;

public abstract class SimpleAuthenticatedCommandHandlerSpringDataTestSupport<CH extends SimpleAuthenticatedCommandHandler<C, R>, C extends Command<R>, R, ES extends SpringDataEntityTestingStore> extends AbstractSimpleAuthenticatedCommandHandlerTestSupport<CH, C, R, ES> {

	@Override
	protected ES entityStore() {
		return SpringDataEntityTestingStore.createStore(entityStoreType(), repositoryTypes());
	}
	
	@SuppressWarnings("unchecked")
	protected Class<? extends ES> entityStoreType() {
		return (Class<? extends ES>) Reflection.resolveGenericParameters(getClass())[3];
	}
	
	protected abstract Set<Class<? extends CrudRepository<? extends Entity<?>, ?>>> repositoryTypes();
	
}
