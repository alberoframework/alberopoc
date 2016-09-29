package com.alberoframework.component.common;

import com.alberoframework.domain.entity.contract.AbstractEntity;
import com.alberoframework.domain.repository.contract.CrudRepository;
import com.alberoframework.domain.repository.persistence.memory.AbstractInMemoryCrudRepository;

public class CommonTestStubs {

	public static abstract class AbstractEntitForRequestTestStub extends AbstractEntity<Long> {
		
		public abstract void setId(Long id);
		
		public abstract Long getId();
		
//		@Override
//		public void assignIdentity(Long id) {
//			setId(id);
//		}
		
		@Override
		public Long identity() {
			return getId();
		}
	}
	
	public static class EntityForRequestTestStub extends AbstractEntitForRequestTestStub {
		
		private Long id;
		private String property1;
		
		public EntityForRequestTestStub(Long id, String property1) {
			this.id = id;
			this.property1 = property1;
		}
		
		public EntityForRequestTestStub(String property1) {
			this.property1 = property1;
		}

		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}
	
		public String getProperty1() {
			return property1;
		}
	
		public void setProperty1(String property1) {
			this.property1 = property1;
		}
	
	}
	
	public static class EntityForRequestTestStub2 extends AbstractEntitForRequestTestStub {
		
		private Long id;
		private String property1;
		
		public EntityForRequestTestStub2(Long id, String property1) {
			this.id = id;
			this.property1 = property1;
		}
		
		public EntityForRequestTestStub2(String property1) {
			this.property1 = property1;
		}
	
		public Long getId() {
			return id;
		}
		
		public void setId(Long id) {
			this.id = id;
		}
	
		public String getProperty1() {
			return property1;
		}
	
		public void setProperty1(String property1) {
			this.property1 = property1;
		}
	
	}


	public static interface EntityForRequestTestStubRepository extends CrudRepository<EntityForRequestTestStub, Long> {
		 public Iterable<EntityForRequestTestStub> findByProperty1(String property1);
	}


	public static interface EntityForRequestTestStub2Repository extends CrudRepository<EntityForRequestTestStub2, Long> {
		 public Iterable<EntityForRequestTestStub2> findByProperty1(String property1);
	}

	
	
	public static class InMemoryEntityForRequestTestStubRepository extends AbstractInMemoryCrudRepository<EntityForRequestTestStub, Long> implements EntityForRequestTestStubRepository {
		@Override
		public Iterable<EntityForRequestTestStub> findByProperty1(String property1) {
			return findByProperty("property1", property1);
		}
		
		@Override
		protected EntityForRequestTestStub copy(EntityForRequestTestStub entity) {
			return entity;
		}
	}
	
	public static class InMemoryEntityForRequestTestStub2Repository extends AbstractInMemoryCrudRepository<EntityForRequestTestStub2, Long> implements EntityForRequestTestStub2Repository {
		@Override
		public Iterable<EntityForRequestTestStub2> findByProperty1(String property1) {
			return findByProperty("property1", property1);
		}
		
		@Override
		protected EntityForRequestTestStub2 copy(EntityForRequestTestStub2 entity) {
			return entity;
		}
	}

	
}
