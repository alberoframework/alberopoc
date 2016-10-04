package com.alberoframework.sample.issuetracker.service.core.query;

import org.assertj.core.util.Lists;
import org.junit.Test;

import com.alberoframework.sample.issuetracker.component.query.testing.IssueTrackerQueryHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.CommentEntity;

public class CommentCollectionQueryHandlerTest extends IssueTrackerQueryHandlerTestSupport<CommentCollectionQueryHandler, CommentCollectionQuery, Iterable<CommentEntity>> {

	@Test
	public TestSpecification testSuccess() {
		return   given(
					entities(
						new CommentEntity("projectId", "issueId", "commentId", "comment1", "creatorUserId"),
						new CommentEntity("projectId", "issueId", "commentId2", "comment2", "creatorUserId"),
						new CommentEntity("projectId", "issueId2", "commentId3", "comment3", "creatorUserId"),
						new CommentEntity("projectId2", "issueId3", "commentId4", "comment4", "creatorUserId")
					)
				 )
				.when(handle(query(new CommentCollectionQuery("projectId", "issueId"))))
				.then(
					Lists.newArrayList(
						new CommentEntity("projectId", "issueId", "commentId", "comment1", "creatorUserId"),
						new CommentEntity("projectId", "issueId", "commentId2", "comment2", "creatorUserId")
					)
				 )
				.when(handle(query(new CommentCollectionQuery("projectId", "issueId2"))))
				.then(
					Lists.newArrayList(
						new CommentEntity("projectId", "issueId2", "commentId3", "comment3", "creatorUserId")
					)
				 )
				.when(handle(query(new CommentCollectionQuery("projectId2", "issueId3"))))
				.then(
					Lists.newArrayList(
						new CommentEntity("projectId2", "issueId3", "commentId4", "comment4", "creatorUserId")
					)
				 )
				.when(handle(query(new CommentCollectionQuery("projectId2", "issueId2"))))
				.then(
					Lists.newArrayList()
				 )
				.when(handle(query(new CommentCollectionQuery("projectId4", "issueId4"))))
				.then(
					Lists.newArrayList()
				 );
	}

}
