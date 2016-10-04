package com.alberoframework.sample.issuetracker.service.app.query;

import org.junit.Test;

import com.alberoframework.hypermedia.HypermediaObjectResource;
import com.alberoframework.sample.issuetracker.component.query.testing.IssueTrackerQueryHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.app.entity.RootAppEntity;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;
import com.alberoframework.sample.issuetracker.service.core.query.UserQuery;
import com.alberoframework.sample.issuetracker.service.core.value.UserRoleValue;

public class RootAppQueryHandlerTest extends IssueTrackerQueryHandlerTestSupport<RootAppQueryHandler, RootAppQuery, HypermediaObjectResource<RootAppEntity>> {

	@Test
	public TestSpecification testSuccess() {
		return   given(
					portStubs(
						queryStubs(
	 						queryStub(query(new UserQuery("userId"), "userId"), nonEmpty(UserEntity.create("userId", "username", "password", UserRoleValue.USER))),
	 						queryStub(query(new UserQuery("userId2"), "userId2"), nonEmpty(UserEntity.create("userId2", "username2", "password2", UserRoleValue.ADMIN)))
		 				)
					)
				 )
				.when(handle(query(new RootAppQuery(), "userId")))
				.then(
					new HypermediaObjectResource<>(new RootAppEntity("username"))
					.withLink("projectCollection", new ProjectAppCollectionQuery())
				 )
				.when(handle(query(new RootAppQuery(), "userId2")))
				.then(
					new HypermediaObjectResource<>(new RootAppEntity("username2"))
					.withLink("projectCollection", new ProjectAppCollectionQuery())
				 )
				.when(handle(query(new RootAppQuery())))
				.then(
					new HypermediaObjectResource<>(new RootAppEntity())
					.withLink("projectCollection", new ProjectAppCollectionQuery())
				 );
				
	}

}
