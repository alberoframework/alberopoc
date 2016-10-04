package com.alberoframework.sample.issuetracker.service.core.query;

import org.junit.Test;

import com.alberoframework.sample.issuetracker.component.query.testing.IssueTrackerQueryHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.value.IssueStatusValue;
import com.google.common.collect.Sets;

public class UserIsAssigneeOfIssuePredicateQueryHandlerTest extends IssueTrackerQueryHandlerTestSupport<UserIsAssigneeOfIssuePredicateQueryHandler, UserIsAssigneeOfIssuePredicateQuery, Boolean> {

	@Test
	public TestSpecification testSuccess() {
		return   given(
					portStubs(
						queryStubs(
	 						queryStub(query(new IssueQuery("projectId", "issueId")), nonEmpty(new IssueEntity("projectId", "issueId", "title1", "description1", "creatorUserId", IssueStatusValue.IN_PROGRESS, Sets.newHashSet("userId")))),
	 						queryStub(query(new IssueQuery("projectId", "issueId2")), nonEmpty(new IssueEntity("projectId", "issueId2", "title2", "description2", "creatorUserId2", IssueStatusValue.CLOSED, Sets.newHashSet("userId", "userId2")))),
	 						queryStub(query(new IssueQuery("projectId2", "issueId3")), nonEmpty(new IssueEntity("projectId", "issueId", "title1", "description1", "creatorUserId", IssueStatusValue.TODO, Sets.newHashSet())))
		 				)
					)
				 )
				.when(handle(query(new UserIsAssigneeOfIssuePredicateQuery("userId", "projectId", "issueId"))))
				.then(true)
				.when(handle(query(new UserIsAssigneeOfIssuePredicateQuery("userId2", "projectId", "issueId"))))
				.then(false)
				.when(handle(query(new UserIsAssigneeOfIssuePredicateQuery("userId", "projectId", "issueId2"))))
				.then(true)
				.when(handle(query(new UserIsAssigneeOfIssuePredicateQuery("userId2", "projectId", "issueId2"))))
				.then(true)
				.when(handle(query(new UserIsAssigneeOfIssuePredicateQuery("userId", "projectId2", "issueId3"))))
				.then(false)
				.when(handle(query(new UserIsAssigneeOfIssuePredicateQuery("userId2", "projectId2", "issueId3"))))
				.then(false);
	}

}
