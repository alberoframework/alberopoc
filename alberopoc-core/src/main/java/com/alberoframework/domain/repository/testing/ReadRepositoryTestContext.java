package com.alberoframework.domain.repository.testing;

import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.ReadRepository;
import com.alberoframework.testing.bdd.context.AbstractStatelessTestContext;

public class ReadRepositoryTestContext<R extends ReadRepository<E, ID>, E extends Entity<ID>, ID> extends AbstractStatelessTestContext {

	private R repository;

	public ReadRepositoryTestContext(R repository) {
		this.repository = repository;
	}
	
	public R repository() {
		return repository;
	}
	
}