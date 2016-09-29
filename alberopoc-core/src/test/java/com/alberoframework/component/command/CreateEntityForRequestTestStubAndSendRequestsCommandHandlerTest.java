package com.alberoframework.component.command;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import com.alberoframework.component.command.CommandHandlerPocTestStubs.AQuery;
import com.alberoframework.component.command.CommandHandlerPocTestStubs.AnotherCommand;
import com.alberoframework.component.command.CommandHandlerPocTestStubs.AnotherCommand2;
import com.alberoframework.component.command.CommandHandlerPocTestStubs.CreateEntityForRequestTestStubAndSendRequestsCommand;
import com.alberoframework.component.command.CommandHandlerPocTestStubs.CreateEntityForRequestTestStubAndSendRequestsCommandHandler;
import com.alberoframework.component.command.testing.SimpleCommandHandlerRepositoryTestSupport;
import com.alberoframework.component.common.CommonTestStubs.EntityForRequestTestStub;
import com.alberoframework.component.common.CommonTestStubs.InMemoryEntityForRequestTestStubRepository;
import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.CrudRepository;
import com.google.common.collect.Sets;

public class CreateEntityForRequestTestStubAndSendRequestsCommandHandlerTest extends SimpleCommandHandlerRepositoryTestSupport<CreateEntityForRequestTestStubAndSendRequestsCommandHandler, CreateEntityForRequestTestStubAndSendRequestsCommand, Long>{

	@SuppressWarnings("unchecked")
	@Test
	public TestSpecification testSuccess() {
		
		return 	given(
						 		entities(
						 				new EntityForRequestTestStub(1L, "prop"),
						 				new EntityForRequestTestStub(2L, "prop")
				 				),
						 		portStubs(
						 				commandStubs(
								 				commandStub(command(new AnotherCommand("someString")), 2),
								 				commandStub(command(new AnotherCommand2("someString2")), 3),
								 				commandStub(command(new AnotherCommand("someString4")), 2) //Not used but it doesnt make the test fail
								 		),
						 				queryStubs(
						 						queryStub(query(new AQuery(2)), "asdf"),
						 						queryStub(query(new AQuery(3)), "asdfg") //Not used but it doesnt make the test fail
						 				)
						 		)
						 		
						 )
						.when(
								handle(command(
										new CreateEntityForRequestTestStubAndSendRequestsCommand(3L, "prop2")
								))
						 )
						.then(
								14L,
								entities(
										new EntityForRequestTestStub(1L, "prop"), 
										new EntityForRequestTestStub(2L, "prop"),
										new EntityForRequestTestStub(3L, "prop2")
								),
								portRequests(
										commandsSent(
												command(new AnotherCommand("someString")),
												command(new AnotherCommand2("someString2")),
												command(new AnotherCommand("someString"))
										),
										queriesSent(
												query(new AQuery(2))
										)
								)
						 );
 								
	}
	
	@Override
	protected Set<CrudRepository<? extends Entity<?>, ?>> repositories() {
			return new HashSet<CrudRepository<? extends Entity<?>, ?>>(Sets.newHashSet(
					new InMemoryEntityForRequestTestStubRepository()));
	}
	
}
