package com.alberoframework.sample.issuetracker.service.core.command;

import org.junit.Test;

import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.component.command.testing.IssueTrackerCommandHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.CommentEntity;

public class CreateCommentCommandHandlerTest extends IssueTrackerCommandHandlerTestSupport<CreateCommentCommandHandler, CreateCommentCommand, VoidUnit> {

	@Test
	public TestSpecification testSuccess() {
		return   given()
				.when(handle(command(new CreateCommentCommand("projectId", "issueId", "commentId", "text"), "creatorUserId")))
				.then(
					entities(
						new CommentEntity("projectId", "issueId", "commentId", "text", "creatorUserId")
					)
				 );
	}

}
