package com.alberoframework.sample.issuetracker.service.core.query;

import java.util.HashSet;

import org.junit.Test;

import com.alberoframework.sample.issuetracker.component.query.testing.IssueTrackerQueryHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.value.IssueStatusValue;

public class IssueInStatusPredicateQueryHandlerTest extends IssueTrackerQueryHandlerTestSupport<IssueInStatusPredicateQueryHandler, IssueInStatusPredicateQuery, Boolean> {

	@Test
	public TestSpecification testSuccess() {
		return   given(
					portStubs(
						queryStubs(
	 						queryStub(query(new IssueQuery("projectId", "issueId")), nonEmpty(new IssueEntity("projectId", "issueId", "title1", "description1", "creatorUserId", IssueStatusValue.IN_PROGRESS, new HashSet<>()))),
	 						queryStub(query(new IssueQuery("projectId", "issueId2")), nonEmpty(new IssueEntity("projectId", "issueId2", "title2", "description2", "creatorUserId2", IssueStatusValue.CLOSED, new HashSet<>())))
		 				)
					)
				 )
				.when(handle(query(new IssueInStatusPredicateQuery("projectId", "issueId", IssueStatusValue.TODO))))
				.then(false)
				.when(handle(query(new IssueInStatusPredicateQuery("projectId", "issueId", IssueStatusValue.IN_PROGRESS))))
				.then(true)
				.when(handle(query(new IssueInStatusPredicateQuery("projectId", "issueId", IssueStatusValue.CLOSED))))
				.then(false)
				.when(handle(query(new IssueInStatusPredicateQuery("projectId", "issueId2", IssueStatusValue.TODO))))
				.then(false)
				.when(handle(query(new IssueInStatusPredicateQuery("projectId", "issueId2", IssueStatusValue.IN_PROGRESS))))
				.then(false)
				.when(handle(query(new IssueInStatusPredicateQuery("projectId", "issueId2", IssueStatusValue.CLOSED))))
				.then(true);
	}

}
