package com.alberoframework.sample.issuetracker.service.core.query;

import java.util.HashSet;

import org.assertj.core.util.Lists;
import org.junit.Test;

import com.alberoframework.sample.issuetracker.component.query.testing.IssueTrackerQueryHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.value.IssueStatusValue;

public class IssueCollectionQueryHandlerTest extends IssueTrackerQueryHandlerTestSupport<IssueCollectionQueryHandler, IssueCollectionQuery, Iterable<IssueEntity>> {

	@Test
	public TestSpecification testSuccess() {
		return   given(
					entities(
						new IssueEntity("projectId", "issueId", "title1", "description1", "creatorUserId", IssueStatusValue.IN_PROGRESS, new HashSet<>()),
						new IssueEntity("projectId", "issueId2", "title2", "description2", "creatorUserId2", IssueStatusValue.CLOSED, new HashSet<>()),
						new IssueEntity("projectId2", "issueId3", "title3", "description3", "creatorUserId2", IssueStatusValue.TODO, new HashSet<>()),
						new IssueEntity("projectId2", "issueId4", "title4", "description4", "creatorUserId", IssueStatusValue.TODO, new HashSet<>())
					)
				 )
				.when(handle(query(new IssueCollectionQuery("projectId"))))
				.then(
					Lists.newArrayList(
						new IssueEntity("projectId", "issueId", "title1", "description1", "creatorUserId", IssueStatusValue.IN_PROGRESS, new HashSet<>()),
						new IssueEntity("projectId", "issueId2", "title2", "description2", "creatorUserId2", IssueStatusValue.CLOSED, new HashSet<>())
					)
				 )
				.when(handle(query(new IssueCollectionQuery("projectId", IssueStatusValue.IN_PROGRESS))))
				.then(
					Lists.newArrayList(
						new IssueEntity("projectId", "issueId", "title1", "description1", "creatorUserId", IssueStatusValue.IN_PROGRESS, new HashSet<>())
					)
				 )
				.when(handle(query(new IssueCollectionQuery("projectId", IssueStatusValue.CLOSED))))
				.then(
					Lists.newArrayList(
							new IssueEntity("projectId", "issueId2", "title2", "description2", "creatorUserId2", IssueStatusValue.CLOSED, new HashSet<>())
					)
				 )
				.when(handle(query(new IssueCollectionQuery("projectId", IssueStatusValue.TODO))))
				.then(
					Lists.newArrayList()
				 )
				.when(handle(query(new IssueCollectionQuery("projectId2"))))
				.then(
					Lists.newArrayList(
							new IssueEntity("projectId2", "issueId3", "title3", "description3", "creatorUserId2", IssueStatusValue.TODO, new HashSet<>()),
							new IssueEntity("projectId2", "issueId4", "title4", "description4", "creatorUserId", IssueStatusValue.TODO, new HashSet<>())
					)
				 )
				.when(handle(query(new IssueCollectionQuery("projectId2", IssueStatusValue.TODO))))
				.then(
					Lists.newArrayList(
							new IssueEntity("projectId2", "issueId3", "title3", "description3", "creatorUserId2", IssueStatusValue.TODO, new HashSet<>()),
							new IssueEntity("projectId2", "issueId4", "title4", "description4", "creatorUserId", IssueStatusValue.TODO, new HashSet<>())
					)
				 )
				.when(handle(query(new IssueCollectionQuery("projectId2", IssueStatusValue.CLOSED))))
				.then(
						Lists.newArrayList()
				 )
				.when(handle(query(new IssueCollectionQuery("projectId3"))))
				.then(
						Lists.newArrayList()
				 )
				.when(handle(query(new IssueCollectionQuery("projectId3", IssueStatusValue.TODO))))
				.then(
						Lists.newArrayList()
				 );
	}

}
