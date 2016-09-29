package com.alberoframework.domain.repository.testing;

import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.ReadRepository;
import com.alberoframework.testing.bdd.testcase.query.AbstractQueryTestOperationWithNullableOutput;

public class ReadRepositoryTestOperations {

  public static class ReadRepositoryFindAllQueryExecution<R extends ReadRepository<E, ID>, E extends Entity<ID>, ID> extends AbstractQueryTestOperationWithNullableOutput<ReadRepositoryTestContext<R, E, ID>, Iterable<E>> {
		
		@Override
		protected Iterable<E> doExecute(ReadRepositoryTestContext<R, E, ID> context) {
			return context.repository().findAll();
		}

	}
	
	public static class ReadRepositoryFindByIdQueryExecution<R extends ReadRepository<E, ID>, E extends Entity<ID>, ID> extends AbstractQueryTestOperationWithNullableOutput<ReadRepositoryTestContext<R, E, ID>, E> {
		
		private ID id;
		
		public ReadRepositoryFindByIdQueryExecution(ID id) {
			this.id = id;
		}

		@Override
		protected E doExecute(ReadRepositoryTestContext<R, E, ID> context) {
			return context.repository().findById(id);
		}

	}
	
}
