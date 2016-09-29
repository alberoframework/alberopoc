package com.alberoframework.domain.repository.testing;

import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.CrudRepository;
import com.alberoframework.testing.bdd.testcase.behavior.AbstractBehaviorTestOperationWithoutOutput;
import com.alberoframework.testing.bdd.testcase.query.AbstractQueryTestOperation;
import com.alberoframework.testing.bdd.testcase.query.AbstractQueryTestOperationWithNullableOutput;

public class CrudRepositoryTestOperations {

	public static class CrudRepositoryFindAllQueryExecution<R extends CrudRepository<E, ID>, E extends Entity<ID>, ID> extends AbstractQueryTestOperation<CrudRepositoryTestContext<R, E, ID>, Iterable<E>> {
		
		@Override
		public Iterable<E> doExecute(CrudRepositoryTestContext<R, E, ID> context) {
			return context.repository().findAll();
		}

	}
	
	public static class CrudRepositoryFindByIdQueryExecution<R extends CrudRepository<E, ID>, E extends Entity<ID>, ID> extends AbstractQueryTestOperationWithNullableOutput<CrudRepositoryTestContext<R, E, ID>, E> {
		
		private ID id;
		
		public CrudRepositoryFindByIdQueryExecution(ID id) {
			this.id = id;
		}

		@Override
		protected E doExecute(CrudRepositoryTestContext<R, E, ID> context) {
			return context.repository().findById(id);
		}

	}
	
	public static class CrudRepositorySaveBehaviorExecution<R extends CrudRepository<E, ID>, E extends Entity<ID>, ID> extends AbstractBehaviorTestOperationWithoutOutput<CrudRepositoryTestContext<R, E, ID>> {
		
		private E entity;
		
		public CrudRepositorySaveBehaviorExecution(E entity) {
			this.entity = entity;
		}

		@Override
		protected void doExecute(CrudRepositoryTestContext<R, E, ID> context) {
			context.repository().save(entity);
		}

	}
	
	public static class CrudRepositorySaveManyBehaviorExecution<R extends CrudRepository<E, ID>, E extends Entity<ID>, ID> extends AbstractBehaviorTestOperationWithoutOutput<CrudRepositoryTestContext<R, E, ID>> {
		
		private Iterable<E> entities;
		
		public CrudRepositorySaveManyBehaviorExecution(Iterable<E> entities) {
			this.entities = entities;
		}

		@Override
		protected void doExecute(CrudRepositoryTestContext<R, E, ID> context) {
			context.repository().save(entities);
		}

	}
	
	public static class CrudRepositoryDeleteBehaviorExecution<R extends CrudRepository<E, ID>, E extends Entity<ID>, ID> extends AbstractBehaviorTestOperationWithoutOutput<CrudRepositoryTestContext<R, E, ID>> {
		
		private E entity;
		
		public CrudRepositoryDeleteBehaviorExecution(E entity) {
			this.entity = entity;
		}

		@Override
		protected void doExecute(CrudRepositoryTestContext<R, E, ID> context) {
			context.repository().delete(entity);
		}

	}
	
}
