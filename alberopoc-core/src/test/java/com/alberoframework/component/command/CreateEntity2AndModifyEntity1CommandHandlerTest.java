package com.alberoframework.component.command;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.alberoframework.component.command.CommandHandlerPocTestStubs.CreateEntity2AndModifyEntity1Command;
import com.alberoframework.component.command.CommandHandlerPocTestStubs.CreateEntity2AndModifyEntity1CommandHandler;
import com.alberoframework.component.command.testing.SimpleCommandHandlerRepositoryTestSupport;
import com.alberoframework.component.common.CommonTestStubs.EntityForRequestTestStub;
import com.alberoframework.component.common.CommonTestStubs.EntityForRequestTestStub2;
import com.alberoframework.component.common.CommonTestStubs.InMemoryEntityForRequestTestStub2Repository;
import com.alberoframework.component.common.CommonTestStubs.InMemoryEntityForRequestTestStubRepository;
import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.CrudRepository;
import com.alberoframework.lang.VoidUnit;
import com.google.common.collect.Sets;

public class CreateEntity2AndModifyEntity1CommandHandlerTest extends SimpleCommandHandlerRepositoryTestSupport<CreateEntity2AndModifyEntity1CommandHandler, CreateEntity2AndModifyEntity1Command, VoidUnit>{

	@Test
	public TestSpecification testSuccess() {
		
		return 	given(
						 		entities(
						 				new EntityForRequestTestStub(1L, "prop"),
						 				new EntityForRequestTestStub(2L, "prop"),
						 				new EntityForRequestTestStub2(1L, "prop3"),
						 				new EntityForRequestTestStub2(2L, "prop3")
				 				)
						 )
						.when(
								handle(command(
										new CreateEntity2AndModifyEntity1Command(2L, 3L, "prop2")
								))
						 )
						.then(
								entities(
										new EntityForRequestTestStub(1L, "prop"),
						 				new EntityForRequestTestStub(2L, "prop2"),
						 				new EntityForRequestTestStub2(1L, "prop3"),
						 				new EntityForRequestTestStub2(2L, "prop3"),
						 				new EntityForRequestTestStub2(3L, "prop2")
								)
						 );
 								
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected Set<CrudRepository<? extends Entity<?>, ?>> repositories() {
			return new HashSet<CrudRepository<? extends Entity<?>, ?>>(Sets.newHashSet(
					new InMemoryEntityForRequestTestStubRepository(),
					new InMemoryEntityForRequestTestStub2Repository()));
	}
	
}
