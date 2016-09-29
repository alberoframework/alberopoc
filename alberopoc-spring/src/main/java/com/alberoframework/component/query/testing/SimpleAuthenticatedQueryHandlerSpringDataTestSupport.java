package com.alberoframework.component.query.testing;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.handler.SimpleAuthenticatedQueryHandler;
import com.alberoframework.component.request.testing.SpringDataEntityTestingStore;
import com.alberoframework.core.reflection.Reflection;
import com.alberoframework.domain.entity.contract.Entity;

public abstract class SimpleAuthenticatedQueryHandlerSpringDataTestSupport<QH extends SimpleAuthenticatedQueryHandler<Q, R>, Q extends Query<R>, R, ES extends SpringDataEntityTestingStore> extends AbstractSimpleAuthenticatedQueryHandlerTestSupport<QH, Q, R, ES> {

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
