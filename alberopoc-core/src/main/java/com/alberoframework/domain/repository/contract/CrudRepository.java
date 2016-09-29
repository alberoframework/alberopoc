package com.alberoframework.domain.repository.contract;

import com.alberoframework.domain.entity.contract.Entity;

public interface CrudRepository<E extends Entity<ID>, ID> extends ReadRepository<E, ID> {

	public <S extends E> S save(S entity);
	
	<S extends E> Iterable<S> save(Iterable<S> entities);
	
	public void delete(E entity);
	
	public void delete(Iterable<? extends E> entities);

}
