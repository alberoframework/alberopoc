package com.alberoframework.sample.issuetracker.service.core.command;

import org.junit.Test;

import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.component.command.testing.IssueTrackerCommandHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;

public class UpdateProjectCommandHandlerTest extends IssueTrackerCommandHandlerTestSupport<UpdateProjectCommandHandler, UpdateProjectCommand, VoidUnit> {

	@Test
	public TestSpecification testSuccessWithOneProject() {
		return   given(
					entities(
						new ProjectEntity("projectId", "project name")
					)
				 )
				.when(handle(command(new UpdateProjectCommand("projectId", "new project name"))))
				.then(
					entities(
						new ProjectEntity("projectId", "new project name")
					)
				 );
	}
	
	@Test
	public TestSpecification testSuccessWithManyProjects() {
		return   given(
					entities(
						new ProjectEntity("projectId", "project name"),
						new ProjectEntity("projectId2", "project name2")
					)
				 )
				.when(handle(command(new UpdateProjectCommand("projectId", "new project name"))))
				.then(
					entities(
						new ProjectEntity("projectId", "new project name"),
						new ProjectEntity("projectId2", "project name2")
					)
				 );
	}

}
