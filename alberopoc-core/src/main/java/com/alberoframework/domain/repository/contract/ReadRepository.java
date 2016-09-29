package com.alberoframework.domain.repository.contract;

import com.alberoframework.domain.entity.contract.Entity;

public interface ReadRepository<E extends Entity<ID>, ID> {

	public E findById(ID id);
	
	public Iterable<E> findAll();

}
