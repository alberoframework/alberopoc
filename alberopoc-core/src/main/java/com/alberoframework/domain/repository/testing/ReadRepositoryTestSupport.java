package com.alberoframework.domain.repository.testing;

import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.ReadRepository;
import com.alberoframework.domain.repository.testing.ReadRepositoryTestOperations.ReadRepositoryFindAllQueryExecution;
import com.alberoframework.domain.repository.testing.ReadRepositoryTestOperations.ReadRepositoryFindByIdQueryExecution;
import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.testing.bdd.support.AbstractStatelessTestSupport;

public abstract class ReadRepositoryTestSupport<R extends ReadRepository<E, ID>, E extends Entity<ID>, ID> extends AbstractStatelessTestSupport<ReadRepositoryTestContext<R, E, ID>> {

	protected abstract R repository();
	
	@Override
	protected ReadRepositoryTestContext<R, E, ID> context(PortRegistry ports) {
		return new ReadRepositoryTestContext<R, E, ID>(repository());
	}
	
	public ReadRepositoryFindAllQueryExecution<R, E, ID> findAll() {
		return new ReadRepositoryFindAllQueryExecution<R, E, ID>();
	}
	
	public ReadRepositoryFindByIdQueryExecution<R, E, ID> findById(ID id) {
		return new ReadRepositoryFindByIdQueryExecution<R, E, ID>(id);
	}
	
}
