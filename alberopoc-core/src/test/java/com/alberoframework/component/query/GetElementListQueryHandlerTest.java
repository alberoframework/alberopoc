package com.alberoframework.component.query;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.alberoframework.component.common.CommonTestStubs.EntityForRequestTestStub;
import com.alberoframework.component.common.CommonTestStubs.InMemoryEntityForRequestTestStubRepository;
import com.alberoframework.component.query.QueryHandlerPocTestStubs.ElementForQueryTestStub;
import com.alberoframework.component.query.QueryHandlerPocTestStubs.GetElementListQuery;
import com.alberoframework.component.query.QueryHandlerPocTestStubs.GetElementListQueryHandler;
import com.alberoframework.component.query.testing.SimpleQueryHandlerRepositoryTestSupport;
import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.CrudRepository;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class GetElementListQueryHandlerTest extends SimpleQueryHandlerRepositoryTestSupport<GetElementListQueryHandler, GetElementListQuery, List<ElementForQueryTestStub>>{

	@Test
	public TestSpecification testSuccessWithoutStateCheck() {
		
		return 	given(
						 		entities(
						 				new EntityForRequestTestStub(1L, "prop"),
						 				new EntityForRequestTestStub(2L, "prop"),
						 				new EntityForRequestTestStub(3L, "prop2")
				 				)
						 )
						.when(handle(query(new GetElementListQuery("prop2"))))
						.then(Lists.newArrayList(new ElementForQueryTestStub(3L, "prop2")))
						.when(handle(query(new GetElementListQuery("prop"))))
						.then(Lists.newArrayList(new ElementForQueryTestStub(1L, "prop"), new ElementForQueryTestStub(2L, "prop")))
						.when(handle(query(new GetElementListQuery("prop3"))))
						.then(new ArrayList<ElementForQueryTestStub>());
 								
	}
	
	@Test
	public TestSpecification testSuccessWithStateCheck() {
		
		return 	given(
						 		entities(
						 				new EntityForRequestTestStub(1L, "prop"),
						 				new EntityForRequestTestStub(2L, "prop"),
						 				new EntityForRequestTestStub(3L, "prop2")
				 				)
						 )
						.when(handle(query(new GetElementListQuery("prop2"))))
						.then(Lists.newArrayList(new ElementForQueryTestStub(3L, "prop2")),
								entities(
						 				new EntityForRequestTestStub(1L, "prop"),
						 				new EntityForRequestTestStub(2L, "prop"),
						 				new EntityForRequestTestStub(3L, "prop2")
				 				))
						.when(handle(query(new GetElementListQuery("prop"))))
						.then(Lists.newArrayList(new ElementForQueryTestStub(1L, "prop"), new ElementForQueryTestStub(2L, "prop")))
						.when(handle(query(new GetElementListQuery("prop3"))))
						.then(new ArrayList<ElementForQueryTestStub>(),
								entities(
						 				new EntityForRequestTestStub(1L, "prop"),
						 				new EntityForRequestTestStub(2L, "prop"),
						 				new EntityForRequestTestStub(3L, "prop2")
				 				));
 								
	}
	
	@Override
	protected Set<CrudRepository<? extends Entity<?>, ?>> repositories() {
			return new HashSet<CrudRepository<? extends Entity<?>, ?>>(Sets.newHashSet(
					new InMemoryEntityForRequestTestStubRepository()));
	}
	
}
