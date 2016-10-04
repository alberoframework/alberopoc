package com.alberoframework.sample.issuetracker.service.core.command;

import java.util.HashSet;

import org.junit.Test;

import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.component.command.testing.IssueTrackerCommandHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.value.IssueStatusValue;
import com.google.common.collect.Sets;

public class AssignIssueCommandHandlerTest extends IssueTrackerCommandHandlerTestSupport<AssignIssueCommandHandler, AssignIssueCommand, VoidUnit> {

	@Test
	public TestSpecification testSuccessSingleUser() {
		return   given(
					entities(
						new IssueEntity("projectId", "issueId", "title", "description", "creatorUserId", IssueStatusValue.TODO, new HashSet<>())
					)
				 )
				.when(
					handle(command(new AssignIssueCommand("projectId", "issueId", "userId"))))
				.then(
					entities(
						new IssueEntity("projectId", "issueId", "title", "description", "creatorUserId", IssueStatusValue.TODO, Sets.newHashSet("userId"))
					)
				 );
	}
	
	@Test
	public TestSpecification testSuccessMultipleUsers() {
		return   given(
					entities(
						new IssueEntity("projectId", "issueId", "title", "description", "creatorUserId", IssueStatusValue.TODO, Sets.newHashSet("oldUserId"))
					)
				 )
				.when(
					handle(command(new AssignIssueCommand("projectId", "issueId", "userId"))))
				.then(
					entities(
						new IssueEntity("projectId", "issueId", "title", "description", "creatorUserId", IssueStatusValue.TODO, Sets.newHashSet("oldUserId", "userId"))
					)
				 );
	}

}
