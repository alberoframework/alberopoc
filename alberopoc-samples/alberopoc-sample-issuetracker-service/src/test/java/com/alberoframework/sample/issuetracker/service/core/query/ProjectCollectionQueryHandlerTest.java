package com.alberoframework.sample.issuetracker.service.core.query;

import org.assertj.core.util.Lists;
import org.junit.Test;

import com.alberoframework.sample.issuetracker.component.query.testing.IssueTrackerQueryHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;
import com.alberoframework.sample.issuetracker.service.core.value.ProjectMembershipTypeValue;
import com.alberoframework.sample.issuetracker.service.core.value.ProjectMembershipValue;
import com.google.common.collect.Sets;

public class ProjectCollectionQueryHandlerTest extends IssueTrackerQueryHandlerTestSupport<ProjectCollectionQueryHandler, ProjectCollectionQuery, Iterable<ProjectEntity>> {

	@Test
	public TestSpecification testSuccess() {
		return   given(
					entities(
						new ProjectEntity("projectId", "project name", Sets.newHashSet(new ProjectMembershipValue("userId", ProjectMembershipTypeValue.USER))),
						new ProjectEntity("projectId2", "project name2", Sets.newHashSet(new ProjectMembershipValue("userId", ProjectMembershipTypeValue.MANAGER), new ProjectMembershipValue("userId3", ProjectMembershipTypeValue.USER))),
						new ProjectEntity("projectId3", "project name3", Sets.newHashSet(new ProjectMembershipValue("userId2", ProjectMembershipTypeValue.MANAGER))),
						new ProjectEntity("projectId4", "project name4", Sets.newHashSet())
					),
					portStubs(
						queryStubs(
	 						queryStub(query(new UserAdminPredicateQuery("userId"), "userId"), false),
	 						queryStub(query(new UserAdminPredicateQuery("userId2"), "userId2"), true),
	 						queryStub(query(new UserAdminPredicateQuery("userId3"), "userId3"), false)
		 				)
					)
				 )
				.when(handle(query(new ProjectCollectionQuery(), "userId")))
				.then(
					Lists.newArrayList(
						new ProjectEntity("projectId", "project name", Sets.newHashSet(new ProjectMembershipValue("userId", ProjectMembershipTypeValue.USER))),
						new ProjectEntity("projectId2", "project name2", Sets.newHashSet(new ProjectMembershipValue("userId", ProjectMembershipTypeValue.MANAGER), new ProjectMembershipValue("userId3", ProjectMembershipTypeValue.USER)))
					)
				 )
				.when(handle(query(new ProjectCollectionQuery(), "userId2")))
				.then(
					Lists.newArrayList(
						new ProjectEntity("projectId", "project name", Sets.newHashSet(new ProjectMembershipValue("userId", ProjectMembershipTypeValue.USER))),
						new ProjectEntity("projectId2", "project name2", Sets.newHashSet(new ProjectMembershipValue("userId", ProjectMembershipTypeValue.MANAGER), new ProjectMembershipValue("userId3", ProjectMembershipTypeValue.USER))),
						new ProjectEntity("projectId3", "project name3", Sets.newHashSet(new ProjectMembershipValue("userId2", ProjectMembershipTypeValue.MANAGER))),
						new ProjectEntity("projectId4", "project name4", Sets.newHashSet())
					)
				 )
				.when(handle(query(new ProjectCollectionQuery(), "userId3")))
				.then(
					Lists.newArrayList(
						new ProjectEntity("projectId2", "project name2", Sets.newHashSet(new ProjectMembershipValue("userId", ProjectMembershipTypeValue.MANAGER), new ProjectMembershipValue("userId3", ProjectMembershipTypeValue.USER)))
					)
				 )
				.when(handle(query(new ProjectCollectionQuery())))
				.then(Lists.newArrayList());
	}

}
