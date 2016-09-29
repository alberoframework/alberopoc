package com.alberoframework.domain.repository.contract;

import com.alberoframework.domain.entity.contract.AbstractEntity;
import com.alberoframework.domain.repository.contract.CrudRepository;

public class CrudRepositoryTestStubs {

	public static class EntityStub extends AbstractEntity<Long> {
		
		private Long id;
		private String property1;
		
		public EntityStub(Long id, String property1) {
			this.id = id;
			this.property1 = property1;
		}
	
		public EntityStub(String property1) {
			this.property1 = property1;
		}

		public Long getId() {
			return id;
		}
	
		public String getProperty1() {
			return property1;
		}
	
		public void setProperty1(String property1) {
			this.property1 = property1;
		}

		public void setId(Long id) {
			this.id = id;
		}
		
//		@Override
//		public void assignIdentity(Long id) {
//			setId(id);
//		}
		
		@Override
		public Long identity() {
			return getId();
		}
	
	}


	public static interface EntityStubRepository extends CrudRepository<EntityStub, Long> {
		 public Iterable<EntityStub> findByProperty1(String property1);
		 
		 public EntityStub findOneByProperty1(String property1);
		 
		 public Iterable<EntityStub> findByIdAndProperty1(Long id, String property1);
		 
		 public Iterable<EntityStub> findByIdOrProperty1(Long id, String property1);
	}


	
}
