package com.alberoframework.domain.repository.testing;

import java.util.Set;

import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.CrudRepository;
import com.alberoframework.domain.repository.testing.CrudRepositoryTestOperations.CrudRepositoryFindAllQueryExecution;
import com.alberoframework.domain.repository.testing.CrudRepositoryTestOperations.CrudRepositoryFindByIdQueryExecution;
import com.alberoframework.domain.repository.testing.CrudRepositoryTestOperations.CrudRepositorySaveBehaviorExecution;
import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.testing.bdd.support.AbstractTestSupport;
import com.google.common.collect.ImmutableSet;

public abstract class CrudRepositoryTestSupport<R extends CrudRepository<E, ID>, E extends Entity<ID>, ID> extends AbstractTestSupport<CrudRepositoryTestContext<R, E, ID>, Set<E>> {

	protected abstract R repository();
	
	@Override
	protected CrudRepositoryTestContext<R, E, ID> context(Set<E> initialState, PortRegistry ports) {
		return new CrudRepositoryTestContext<R, E, ID>(repository(), initialState);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	protected CrudRepositoryTestContext<R, E, ID> context(PortRegistry ports) {
		return context(entities(), ports);
	}
	
//	@Override
//	protected RepositoryTestContext<R, E, ID> context(Set<E> initialState) {
//		return context(initialState, ports());
//	}
	
	@SuppressWarnings("unchecked")
	public Set<E> entities(E ... entities) {
		Set<E> initialState = ImmutableSet.of();
		if (entities != null) 
			initialState = ImmutableSet.copyOf(entities);
//		Preconditions.checkArgument(initialState.size() == entities.length, "Duplicated elements in the initial set of entities");
		return initialState;
	}
	
	public CrudRepositoryFindAllQueryExecution<R, E, ID> findAll() {
		return new CrudRepositoryFindAllQueryExecution<R, E, ID>();
	}
	
	public CrudRepositoryFindByIdQueryExecution<R, E, ID> findById(ID id) {
		return new CrudRepositoryFindByIdQueryExecution<R, E, ID>(id);
	}
	
	public CrudRepositorySaveBehaviorExecution<R, E, ID> save(E entity) {
		return new CrudRepositorySaveBehaviorExecution<R, E, ID>(entity);
	}

	
}
