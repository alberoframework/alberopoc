package com.alberoframework.sample.issuetracker.service.core.command;

import org.junit.Test;

import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.component.command.testing.IssueTrackerCommandHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;

public class UpdateIssueCommandHandlerTest extends IssueTrackerCommandHandlerTestSupport<UpdateIssueCommandHandler, UpdateIssueCommand, VoidUnit> {

	@Test
	public TestSpecification testSuccess() {
		return   given(
					entities(
						IssueEntity.create("projectId", "issueId", "title", "description", "creatorUserId")
					)
				 )
				.when(handle(command(new UpdateIssueCommand("projectId", "issueId", "new title", "new description"))))
				.then(
					entities(
						IssueEntity.create("projectId", "issueId", "new title", "new description", "creatorUserId")
					)
				 );
	}

}
