package com.alberoframework.component.command;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.alberoframework.component.command.CommandHandlerPocTestStubs.CreateEntityForRequestTestStubCommand;
import com.alberoframework.component.command.CommandHandlerPocTestStubs.CreateEntityForRequestTestStubCommandHandler;
import com.alberoframework.component.command.testing.SimpleCommandHandlerRepositoryTestSupport;
import com.alberoframework.component.common.CommonTestStubs.EntityForRequestTestStub;
import com.alberoframework.component.common.CommonTestStubs.InMemoryEntityForRequestTestStubRepository;
import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.CrudRepository;
import com.google.common.collect.Sets;

public class CreateEntityForRequestTestStubCommandHandlerTest extends SimpleCommandHandlerRepositoryTestSupport<CreateEntityForRequestTestStubCommandHandler, CreateEntityForRequestTestStubCommand, Long>{

	@Test
	public TestSpecification testSuccess() {
		
		return 	given(
						 		entities(
						 				new EntityForRequestTestStub(1L, "prop"),
						 				new EntityForRequestTestStub(2L, "prop")
				 				)
						 )
						.when(
								handle(command(
										new CreateEntityForRequestTestStubCommand(3L, "prop2")
								))
						 )
						.then(
								3L,
								entities(
										new EntityForRequestTestStub(1L, "prop"), 
										new EntityForRequestTestStub(2L, "prop"),
										new EntityForRequestTestStub(3L, "prop2")
								)
						 );
 								
	}
	
	@Override
	protected Set<CrudRepository<? extends Entity<?>, ?>> repositories() {
			return new HashSet<CrudRepository<? extends Entity<?>, ?>>(Sets.newHashSet(
					new InMemoryEntityForRequestTestStubRepository()));
	}
	
}
