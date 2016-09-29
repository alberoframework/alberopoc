package com.alberoframework.domain.repository.testing;

import java.util.Set;

import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.CrudRepository;
import com.alberoframework.testing.bdd.context.AbstractTestContext;
import com.google.common.collect.ImmutableSet;

public class CrudRepositoryTestContext<R extends CrudRepository<E, ID>, E extends Entity<ID>, ID> extends AbstractTestContext<Set<E>> {

	private R repository;

	public CrudRepositoryTestContext(R repository, Set<E> entities) {
		this.repository = repository;
		for (E entity : entities) {
			this.repository.save(entity);
		}
	}
	
	public R repository() {
		return repository;
	}
	
	@Override
	public Set<E> currentState() {
		return ImmutableSet.copyOf(repository.findAll());
	}
	
}