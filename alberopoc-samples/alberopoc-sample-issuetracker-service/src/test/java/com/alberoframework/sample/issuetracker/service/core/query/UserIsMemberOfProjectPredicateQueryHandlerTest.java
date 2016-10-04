package com.alberoframework.sample.issuetracker.service.core.query;

import org.junit.Test;

import com.alberoframework.sample.issuetracker.component.query.testing.IssueTrackerQueryHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;
import com.alberoframework.sample.issuetracker.service.core.value.ProjectMembershipTypeValue;
import com.alberoframework.sample.issuetracker.service.core.value.ProjectMembershipValue;
import com.google.common.collect.Sets;

public class UserIsMemberOfProjectPredicateQueryHandlerTest extends IssueTrackerQueryHandlerTestSupport<UserIsMemberOfProjectPredicateQueryHandler, UserIsMemberOfProjectPredicateQuery, Boolean> {

	@Test
	public TestSpecification testSuccess() {
		return   given(
					portStubs(
						queryStubs(
	 						queryStub(query(new ProjectQuery("projectId")), nonEmpty(new ProjectEntity("projectId", "project name", Sets.newHashSet(new ProjectMembershipValue("userId", ProjectMembershipTypeValue.USER))))),
	 						queryStub(query(new ProjectQuery("projectId2")), nonEmpty(new ProjectEntity("projectId2", "project name2", Sets.newHashSet(new ProjectMembershipValue("userId", ProjectMembershipTypeValue.MANAGER), new ProjectMembershipValue("userId2", ProjectMembershipTypeValue.USER))))),
	 						queryStub(query(new ProjectQuery("projectId3")), nonEmpty(new ProjectEntity("projectId4", "project name4", Sets.newHashSet())))
		 				)
					)
				 )
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId", "projectId"))))
				.then(true)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId", "projectId", ProjectMembershipTypeValue.USER))))
				.then(true)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId", "projectId", ProjectMembershipTypeValue.MANAGER))))
				.then(false)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId2", "projectId"))))
				.then(false)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId2", "projectId", ProjectMembershipTypeValue.USER))))
				.then(false)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId2", "projectId", ProjectMembershipTypeValue.MANAGER))))
				.then(false)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId", "projectId2"))))
				.then(true)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId", "projectId2", ProjectMembershipTypeValue.USER))))
				.then(false)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId", "projectId2", ProjectMembershipTypeValue.MANAGER))))
				.then(true)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId2", "projectId2"))))
				.then(true)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId2", "projectId2", ProjectMembershipTypeValue.USER))))
				.then(true)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId2", "projectId2", ProjectMembershipTypeValue.MANAGER))))
				.then(false)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId", "projectId3"))))
				.then(false)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId", "projectId3", ProjectMembershipTypeValue.USER))))
				.then(false)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId", "projectId3", ProjectMembershipTypeValue.MANAGER))))
				.then(false)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId2", "projectId3"))))
				.then(false)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId2", "projectId3", ProjectMembershipTypeValue.USER))))
				.then(false)
				.when(handle(query(new UserIsMemberOfProjectPredicateQuery("userId2", "projectId3", ProjectMembershipTypeValue.MANAGER))))
				.then(false);
	}

}
