package com.alberoframework.sample.issuetracker.service.core.query;

import org.assertj.core.util.Lists;
import org.junit.Test;

import com.alberoframework.sample.issuetracker.component.query.testing.IssueTrackerQueryHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;
import com.alberoframework.sample.issuetracker.service.core.value.UserRoleValue;

public class UserCollectionQueryHandlerTest extends IssueTrackerQueryHandlerTestSupport<UserCollectionQueryHandler, UserCollectionQuery, Iterable<UserEntity>> {

	@Test
	public TestSpecification testSuccess() {
		return   given(
					entities(
						UserEntity.create("userId", "username", "password", UserRoleValue.USER),
						UserEntity.create("userId2", "username2", "password2", UserRoleValue.ADMIN)
					)
				 )
				.when(handle(query(new UserCollectionQuery())))
				.then(
					Lists.newArrayList(
						UserEntity.create("userId", "username", "password", UserRoleValue.USER),
						UserEntity.create("userId2", "username2", "password2", UserRoleValue.ADMIN)
					)
				 );
	}

}
