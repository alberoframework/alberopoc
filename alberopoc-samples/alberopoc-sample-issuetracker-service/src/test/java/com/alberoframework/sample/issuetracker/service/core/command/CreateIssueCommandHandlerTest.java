package com.alberoframework.sample.issuetracker.service.core.command;

import org.junit.Test;

import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.component.command.testing.IssueTrackerCommandHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;

public class CreateIssueCommandHandlerTest extends IssueTrackerCommandHandlerTestSupport<CreateIssueCommandHandler, CreateIssueCommand, VoidUnit> {

	@Test
	public TestSpecification testSuccess() {
		return   given()
				.when(handle(command(new CreateIssueCommand("projectId", "issueId", "title", "description"), "creatorUserId")))
				.then(
					entities(
						IssueEntity.create("projectId", "issueId", "title", "description", "creatorUserId")
					)
				 );
	}

}
