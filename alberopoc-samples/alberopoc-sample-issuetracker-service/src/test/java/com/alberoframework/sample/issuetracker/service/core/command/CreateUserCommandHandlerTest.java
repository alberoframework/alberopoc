package com.alberoframework.sample.issuetracker.service.core.command;

import org.junit.Test;

import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.component.command.testing.IssueTrackerCommandHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;
import com.alberoframework.sample.issuetracker.service.core.value.UserRoleValue;

public class CreateUserCommandHandlerTest extends IssueTrackerCommandHandlerTestSupport<CreateUserCommandHandler, CreateUserCommand, VoidUnit> {

	@Test
	public TestSpecification testSuccess() {
		return   given()
				.when(handle(command(new CreateUserCommand("userId", "username", "password", UserRoleValue.USER))))
				.then(
					entities(
						UserEntity.create("userId", "username", "password", UserRoleValue.USER)
					)
				 );
	}

}
