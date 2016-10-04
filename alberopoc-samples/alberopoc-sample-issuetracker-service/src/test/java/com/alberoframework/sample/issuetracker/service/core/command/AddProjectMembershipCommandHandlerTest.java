package com.alberoframework.sample.issuetracker.service.core.command;

import org.junit.Test;

import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.component.command.testing.IssueTrackerCommandHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;
import com.alberoframework.sample.issuetracker.service.core.value.ProjectMembershipTypeValue;
import com.alberoframework.sample.issuetracker.service.core.value.ProjectMembershipValue;
import com.google.common.collect.Sets;

public class AddProjectMembershipCommandHandlerTest extends IssueTrackerCommandHandlerTestSupport<AddProjectMembershipCommandHandler, AddProjectMembershipCommand, VoidUnit> {

	@Test
	public TestSpecification testSuccessWithOneMembership() {
		return   given(
					entities(
						new ProjectEntity("projectId", "project name")
					)
				 )
				.when(handle(command(new AddProjectMembershipCommand("projectId", new ProjectMembershipValue("userId", ProjectMembershipTypeValue.USER)))))
				.then(
					entities(
						new ProjectEntity("projectId", "project name", Sets.newHashSet(new ProjectMembershipValue("userId", ProjectMembershipTypeValue.USER)))
					)
				 );
	}
	
	@Test
	public TestSpecification testSuccessWithManyMemberships() {
		return   given(
					entities(
						new ProjectEntity("projectId", "project name", Sets.newHashSet(new ProjectMembershipValue("oldUserId", ProjectMembershipTypeValue.USER)))
					)
				 )
				.when(handle(command(new AddProjectMembershipCommand("projectId", new ProjectMembershipValue("userId", ProjectMembershipTypeValue.MANAGER)))))
				.then(
					entities(
						new ProjectEntity("projectId", "project name", Sets.newHashSet(new ProjectMembershipValue("oldUserId", ProjectMembershipTypeValue.USER), new ProjectMembershipValue("userId", ProjectMembershipTypeValue.MANAGER)))
					)
				 );
	}

}
