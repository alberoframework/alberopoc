package com.alberoframework.domain.repository.persistence.memory;

import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.CrudRepository;

public abstract class AbstractInMemoryCrudRepository<E extends Entity<ID>, ID> extends AbstractInMemoryReadRepository<E, ID> implements CrudRepository<E, ID> {

	@Override
	public <S extends E> S save(S entity) {
		return persist(entity);
	}
	
	@Override
	public <S extends E> Iterable<S> save(Iterable<S> entities) {
		for (E entity : entities) {
			save(entity);
		}
		return entities;
	}

	@Override
	public void delete(E entity) {
		remove(entity);
	}
	
	@Override
	public void delete(Iterable<? extends E> entities) {
		for (E entity : entities) {
			delete(entity);
		}
	}

}
