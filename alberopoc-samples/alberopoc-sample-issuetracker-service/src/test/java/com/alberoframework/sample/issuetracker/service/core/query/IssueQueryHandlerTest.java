package com.alberoframework.sample.issuetracker.service.core.query;

import java.util.HashSet;
import java.util.Optional;

import org.junit.Test;

import com.alberoframework.sample.issuetracker.component.query.testing.IssueTrackerQueryHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.value.IssueStatusValue;

public class IssueQueryHandlerTest extends IssueTrackerQueryHandlerTestSupport<IssueQueryHandler, IssueQuery, Optional<IssueEntity>> {

	@Test
	public TestSpecification testSuccess() {
		return   given(
					entities(
						new IssueEntity("projectId", "issueId", "title1", "description1", "creatorUserId", IssueStatusValue.IN_PROGRESS, new HashSet<>()),
						new IssueEntity("projectId", "issueId2", "title2", "description2", "creatorUserId2", IssueStatusValue.CLOSED, new HashSet<>())
					)
				 )
				.when(handle(query(new IssueQuery("projectId", "issueId"))))
				.then(nonEmpty(new IssueEntity("projectId", "issueId", "title1", "description1", "creatorUserId", IssueStatusValue.IN_PROGRESS, new HashSet<>())))
				.when(handle(query(new IssueQuery("projectId", "issueId2"))))
				.then(nonEmpty(new IssueEntity("projectId", "issueId2", "title2", "description2", "creatorUserId2", IssueStatusValue.CLOSED, new HashSet<>())))
				.when(handle(query(new IssueQuery("projectId3", "issueId"))))
				.then(empty())
				.when(handle(query(new IssueQuery("projectId", "issueId3"))))
				.then(empty());
		
	}

}
