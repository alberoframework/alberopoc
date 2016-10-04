package com.alberoframework.sample.issuetracker.service.core.query;

import org.junit.Test;

import com.alberoframework.sample.issuetracker.component.query.testing.IssueTrackerQueryHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;
import com.alberoframework.sample.issuetracker.service.core.value.UserRoleValue;

public class UserAdminPredicateQueryHandlerTest extends IssueTrackerQueryHandlerTestSupport<UserAdminPredicateQueryHandler, UserAdminPredicateQuery, Boolean> {

	@Test
	public TestSpecification testSuccess() {
		return   given(
					portStubs(
						queryStubs(
	 						queryStub(query(new UserQuery("userId")), nonEmpty(UserEntity.create("userId", "username", "password", UserRoleValue.USER))),
	 						queryStub(query(new UserQuery("userId2")), nonEmpty(UserEntity.create("userId2", "username2", "password2", UserRoleValue.ADMIN)))
		 				)
					)
				 )
				.when(handle(query(new UserAdminPredicateQuery("userId"))))
				.then(false)
				.when(handle(query(new UserAdminPredicateQuery("userId2"))))
				.then(true);
	}

}
