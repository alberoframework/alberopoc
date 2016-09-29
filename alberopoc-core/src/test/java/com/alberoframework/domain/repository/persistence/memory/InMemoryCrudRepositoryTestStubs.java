package com.alberoframework.domain.repository.persistence.memory;

import com.alberoframework.domain.repository.contract.CrudRepositoryTestStubs.EntityStub;
import com.alberoframework.domain.repository.contract.CrudRepositoryTestStubs.EntityStubRepository;
import com.alberoframework.domain.repository.persistence.memory.AbstractInMemoryCrudRepository;

public class InMemoryCrudRepositoryTestStubs {

	
	public static class InMemoryEntityStubRepository extends AbstractInMemoryCrudRepository<EntityStub, Long> implements EntityStubRepository {
		
		@Override
		protected EntityStub copy(EntityStub entity) {
			return entity;
		}
		
		@Override
		public Iterable<EntityStub> findByProperty1(String property1) {
			return findByProperty("property1", property1);
		}

		@Override
		public EntityStub findOneByProperty1(String property1) {
			return findOneByProperty("property1", property1);
		}
	
		@Override
		public Iterable<EntityStub> findByIdAndProperty1(Long id, String property1) {
			return findByFilterMatchAll(
						new EntityPropertyFilter("id", id),
						new EntityPropertyFilter("property1", property1)
					);
		}
	
		@Override
		public Iterable<EntityStub> findByIdOrProperty1(Long id, String property1) {
			return findByFilterMatchOne(
					new EntityPropertyFilter("id", id),
					new EntityPropertyFilter("property1", property1)
				);
		}
		
		
	}
	
}
