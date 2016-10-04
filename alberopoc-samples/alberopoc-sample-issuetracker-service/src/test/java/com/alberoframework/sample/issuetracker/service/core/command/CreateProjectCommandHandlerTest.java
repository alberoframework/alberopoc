package com.alberoframework.sample.issuetracker.service.core.command;

import org.junit.Test;

import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.component.command.testing.IssueTrackerCommandHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;

public class CreateProjectCommandHandlerTest extends IssueTrackerCommandHandlerTestSupport<CreateProjectCommandHandler, CreateProjectCommand, VoidUnit> {

	@Test
	public TestSpecification testSuccessWithEmptyState() {
		return   given()
				.when(handle(command(new CreateProjectCommand("projectId", "project name"))))
				.then(
					entities(
						new ProjectEntity("projectId", "project name")
					)
				 );
	}
	
	@Test
	public TestSpecification testSuccessWithNonEmptyState() {
		return   given(
					entities(
						new ProjectEntity("oldProjectId", "old project name")
					)
				 )
				.when(handle(command(new CreateProjectCommand("projectId", "project name"))))
				.then(
					entities(
						new ProjectEntity("oldProjectId", "old project name"),
						new ProjectEntity("projectId", "project name")
					)
				 );
	}

}
