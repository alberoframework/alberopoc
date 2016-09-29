package com.alberoframework.domain.repository.contract;

import com.alberoframework.domain.repository.contract.CrudRepositoryTestStubs.EntityStub;
import com.alberoframework.domain.repository.contract.CrudRepositoryTestStubs.EntityStubRepository;
import com.alberoframework.domain.repository.testing.CrudRepositoryTestContext;
import com.alberoframework.testing.bdd.testcase.query.AbstractQueryTestOperation;
import com.alberoframework.testing.bdd.testcase.query.AbstractQueryTestOperationWithNullableOutput;

public class CrudRepositoryTestOperations {

	public static class RepositoryFindByProperty1QueryExecution extends AbstractQueryTestOperation<CrudRepositoryTestContext<EntityStubRepository, EntityStub, Long>, Iterable<EntityStub>> {
		
		private String property1;
		
		public RepositoryFindByProperty1QueryExecution(String property1) {
			this.property1 = property1;
		}

		@Override
		protected Iterable<EntityStub> doExecute(CrudRepositoryTestContext<EntityStubRepository, EntityStub, Long> context) {
			return context.repository().findByProperty1(property1);
		}

	}
	
	public static class RepositoryFindOneByProperty1QueryExecution extends AbstractQueryTestOperationWithNullableOutput<CrudRepositoryTestContext<EntityStubRepository, EntityStub, Long>, EntityStub> {
		
		private String property1;
		
		public RepositoryFindOneByProperty1QueryExecution(String property1) {
			this.property1 = property1;
		}

		@Override
		protected EntityStub doExecute(CrudRepositoryTestContext<EntityStubRepository, EntityStub, Long> context) {
			return context.repository().findOneByProperty1(property1);
		}

	}
	
	public static class RepositoryFindByIdAndProperty1QueryExecution extends AbstractQueryTestOperation<CrudRepositoryTestContext<EntityStubRepository, EntityStub, Long>, Iterable<EntityStub>> {
		private Long id;
		private String property1;
		
		public RepositoryFindByIdAndProperty1QueryExecution(Long id, String property1) {
			this.id = id;
			this.property1 = property1;
		}

		@Override
		protected Iterable<EntityStub> doExecute(CrudRepositoryTestContext<EntityStubRepository, EntityStub, Long> context) {
			return context.repository().findByIdAndProperty1(id, property1);
		}

	}
	
	public static class RepositoryFindByIdOrProperty1QueryExecution extends AbstractQueryTestOperation<CrudRepositoryTestContext<EntityStubRepository, EntityStub, Long>, Iterable<EntityStub>> {
		private Long id;
		private String property1;
		
		public RepositoryFindByIdOrProperty1QueryExecution(Long id, String property1) {
			this.id = id;
			this.property1 = property1;
		}

		@Override
		protected Iterable<EntityStub> doExecute(CrudRepositoryTestContext<EntityStubRepository, EntityStub, Long> context) {
			return context.repository().findByIdOrProperty1(id, property1);
		}

	}
	
}
