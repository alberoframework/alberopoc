package com.alberoframework.sample.issuetracker.service.core.query;

import java.util.Optional;

import org.junit.Test;

import com.alberoframework.sample.issuetracker.component.query.testing.IssueTrackerQueryHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;
import com.alberoframework.sample.issuetracker.service.core.value.UserRoleValue;

public class UserQueryHandlerTest extends IssueTrackerQueryHandlerTestSupport<UserQueryHandler, UserQuery, Optional<UserEntity>> {

	@Test
	public TestSpecification testSuccess() {
		return   given(
					entities(
						UserEntity.create("userId", "username", "password", UserRoleValue.USER),
						UserEntity.create("userId2", "username2", "password2", UserRoleValue.ADMIN)
					)
				 )
				.when(handle(query(new UserQuery("userId"))))
				.then(nonEmpty(UserEntity.create("userId", "username", "password", UserRoleValue.USER)))
				.when(handle(query(new UserQuery("userId2"))))
				.then(nonEmpty(UserEntity.create("userId2", "username2", "password2", UserRoleValue.ADMIN)))
				.when(handle(query(new UserQuery("userId3"))))
				.then(empty());
	}

}
