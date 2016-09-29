package com.alberoframework.domain.repository.contract;

import org.junit.Test;

import com.alberoframework.domain.repository.contract.CrudRepositoryTestOperations.RepositoryFindByIdAndProperty1QueryExecution;
import com.alberoframework.domain.repository.contract.CrudRepositoryTestOperations.RepositoryFindByIdOrProperty1QueryExecution;
import com.alberoframework.domain.repository.contract.CrudRepositoryTestOperations.RepositoryFindByProperty1QueryExecution;
import com.alberoframework.domain.repository.contract.CrudRepositoryTestOperations.RepositoryFindOneByProperty1QueryExecution;
import com.alberoframework.domain.repository.contract.CrudRepositoryTestStubs.EntityStub;
import com.alberoframework.domain.repository.contract.CrudRepositoryTestStubs.EntityStubRepository;
import com.alberoframework.domain.repository.testing.CrudRepositoryTestSupport;

public abstract class CrudRepositoryTest extends CrudRepositoryTestSupport<EntityStubRepository, EntityStub, Long> {

	@Test
	public TestSpecification testSaveSuccess() {
		
		return 	given(
						 		entities(
						 				new EntityStub(1L, "prop")
				 				)
		 				 )
						.when(
								save(
										new EntityStub(2L, "prop2")
								)
						 )
						.then(
								entities(
										new EntityStub(1L, "prop"), 
										new EntityStub(2L, "prop2")
								)
						 );
				 								
	}
	
//	@Test
//	public TestOutcome testSaveSuccessWithoutId() {
//		
//		return 	given(
//						 		entities(
//						 				new EntityStub(1L, "prop")
//				 				)
//		 				 )
//						.when(
//								save(
//										new EntityStub("prop2")
//								)
//						 )
//						.then(
//								entities(
//										new EntityStub(1L, "prop"), 
//										new EntityStub(2L, "prop2")
//								)
//						 )
//						.run();
//				 								
//	}
//	
//	@Test
//	public TestOutcome testSaveSkippingIds() {
//		
//		return 	given(
//						 		entities(
//						 				new EntityStub(1L, "prop"),
//						 				new EntityStub(3L, "prop") //next entity saved will have the id of 4L
//				 				)
//		 				 )
//						.when(
//								save(
//										new EntityStub("prop2")
//								)
//						 )
//						.then(
//								entities(
//										new EntityStub(1L, "prop"), 
//										new EntityStub(3L, "prop"),
//										new EntityStub(4L, "prop2")
//								)
//						 )
//						.run();
//				 								
//	}
	
	@Test
	public TestSpecification testSaveUpdatesAlreadyPresentEntity() {
		
		return 	given(
						 		entities(
						 				new EntityStub(1L, "prop"),
						 				new EntityStub(3L, "prop") 
				 				)
		 				 )
						.when(
								save(
										new EntityStub(3L, "prop2")
								)
						 )
						.then(
								entities(
										new EntityStub(1L, "prop"), 
										new EntityStub(3L, "prop2")
								)
						 );
				 								
	}
	
	@Test
	public TestSpecification testSaveChainSuccess() {
		
		return 	given(
						 		entities(
						 				new EntityStub(1L, "prop")
				 				)
		 				 )
						.when(
								save(
										new EntityStub(2L, "prop2")
								)
						 )
						.then(
								entities(
										new EntityStub(1L, "prop"), 
										new EntityStub(2L, "prop2")
								)
						 )
						.when(
								save(
										new EntityStub(3L, "prop3")
								)
						 )
						.then(
								entities(
										new EntityStub(1L, "prop"), 
										new EntityStub(2L, "prop2"),
										new EntityStub(3L, "prop3")
								)
						 );
		 
	}
	
	
	@Test
	public TestSpecification testFindById() {
		
		return 	given(
						 		entities(
						 				new EntityStub(1L, "prop"),
						 				new EntityStub(2L, "prop2"),
						 				new EntityStub(3L, "prop3")
				 				)
		 				 )
						.when(findById(2L)).then(nonEmpty(new EntityStub(2L, "prop2")))
						.when(findById(4L)).then(empty())
						.when(findById(3L)).then(nonEmpty(new EntityStub(3L, "prop3")));
				 								
	}
	
	@Test
	public TestSpecification testFindByAll() {
		
		return 	given(
						 		entities(
						 				new EntityStub(1L, "prop"),
						 				new EntityStub(2L, "prop2"),
						 				new EntityStub(3L, "prop3")
				 				)
		 				 )
						.when(findAll())
						.then(
								entities(
						 				new EntityStub(1L, "prop"),
						 				new EntityStub(2L, "prop2"),
						 				new EntityStub(3L, "prop3")
				 				)
						 );
				 								
	}
	
	@Test
	public TestSpecification testFindByProperty1() {
		
		return 	given(
						 		entities(
						 				new EntityStub(1L, "prop"),
						 				new EntityStub(2L, "prop2"),
						 				new EntityStub(3L, "prop3"),
						 				new EntityStub(4L, "prop")
				 				)
		 				 )
						.when(findByProperty1("prop2")).then(entities(new EntityStub(2L, "prop2")))
						.when(findByProperty1("prop7")).then(entities()) //NO OUTPUT AND AN EMPTY LIST ARE TWO DIFFERENT THINGS!
						.when(findByProperty1("prop3")).then(entities(new EntityStub(3L, "prop3")))
						.when(findByProperty1("prop")).then(entities(new EntityStub(1L, "prop"), new EntityStub(4L, "prop")));
						
				 								
	}
	
	@Test
	public TestSpecification testFindOneByProperty1() {
		
		return 	given(
						 		entities(
						 				new EntityStub(1L, "prop"),
						 				new EntityStub(2L, "prop2"),
						 				new EntityStub(3L, "prop3"),
						 				new EntityStub(4L, "prop")
				 				)
		 				 )
						.when(findOneByProperty1("prop2")).then(nonEmpty(new EntityStub(2L, "prop2")))
						.when(findOneByProperty1("prop7")).then(empty()) 
						.when(findOneByProperty1("prop3")).then(nonEmpty(new EntityStub(3L, "prop3")))
						.when(findOneByProperty1("prop")).then(empty());  //If there are more than one matches it returns null
						
				 								
	}
	
	
	@Test
	public TestSpecification testFindByIdAndProperty1() {
		return 	given(
						 		entities(
						 				new EntityStub(1L, "prop"),
						 				new EntityStub(2L, "prop2"),
						 				new EntityStub(3L, "prop3"),
						 				new EntityStub(4L, "prop")
				 				)
		 				 )
						.when(findByIdAndProperty1(2L, "prop2")).then(entities(new EntityStub(2L, "prop2")))
						.when(findByIdAndProperty1(1L, "prop2")).then(entities())
						.when(findByIdAndProperty1(2L, "prop")).then(entities())
						.when(findByIdAndProperty1(3L, "prop3")).then(entities(new EntityStub(3L, "prop3")))
						.when(findByIdAndProperty1(4L, "prop")).then(entities(new EntityStub(4L, "prop")));
						
	}
	
	@Test
	public TestSpecification testFindByIdOrProperty1() {
		return 	given(
						 		entities(
						 				new EntityStub(1L, "prop"),
						 				new EntityStub(2L, "prop2"),
						 				new EntityStub(3L, "prop3"),
						 				new EntityStub(4L, "prop")
				 				)
		 				 )
						.when(findByIdOrProperty1(2L, "prop2")).then(entities(new EntityStub(2L, "prop2")))
						.when(findByIdOrProperty1(1L, "prop2")).then(entities(new EntityStub(1L, "prop"), new EntityStub(2L, "prop2")))
						.when(findByIdOrProperty1(2L, "prop")).then(entities(new EntityStub(1L, "prop"), new EntityStub(2L, "prop2"), new EntityStub(4L, "prop")))
						.when(findByIdOrProperty1(3L, "prop3")).then(entities(new EntityStub(3L, "prop3")))
						.when(findByIdOrProperty1(4L, "prop")).then(entities(new EntityStub(1L, "prop"), new EntityStub(4L, "prop")));
						
	}
	

	public RepositoryFindByProperty1QueryExecution findByProperty1(String property1) {
		 return new RepositoryFindByProperty1QueryExecution(property1);
	}	
	
	public RepositoryFindOneByProperty1QueryExecution findOneByProperty1(String property1) {
		 return new RepositoryFindOneByProperty1QueryExecution(property1);
	}	
	
	public RepositoryFindByIdAndProperty1QueryExecution findByIdAndProperty1(Long id, String property1) {
		 return new RepositoryFindByIdAndProperty1QueryExecution(id, property1);
	}	
	
	public RepositoryFindByIdOrProperty1QueryExecution findByIdOrProperty1(Long id, String property1) {
		 return new RepositoryFindByIdOrProperty1QueryExecution(id, property1);
	}

}
