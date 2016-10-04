package com.alberoframework.sample.issuetracker.service.core.command;

import java.util.HashSet;

import org.junit.Test;

import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.component.command.testing.IssueTrackerCommandHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.value.IssueStatusValue;

public class SetIssueInProgressCommandHandlerTest extends IssueTrackerCommandHandlerTestSupport<SetIssueInProgressCommandHandler, SetIssueInProgressCommand, VoidUnit> {

	@Test
	public TestSpecification testSuccess() {
		return   given(
					entities(
						IssueEntity.create("projectId", "issueId", "title", "description", "creatorUserId")
					)
				 )
				.when(
					handle(command(new SetIssueInProgressCommand("projectId", "issueId")))
				 )
				.then(
					entities(
						new IssueEntity("projectId", "issueId", "title", "description", "creatorUserId", IssueStatusValue.IN_PROGRESS, new HashSet<>())
					)
				 );
	}
	
	@Test
	public TestSpecification testFailureBecauseIssueAlreadyInProgress() {
		return   given(
					entities(
						new IssueEntity("projectId", "issueId", "title", "description", "creatorUserId", IssueStatusValue.IN_PROGRESS, new HashSet<>())
					)
				 )
				.when(
					handle(command(new SetIssueInProgressCommand("projectId", "issueId")))
				 )
				.then(
					IllegalStateException.class
				 );
	}
	
	@Test
	public TestSpecification testFailureBecauseIssueAlreadyClosed() {
		return   given(
					entities(
						new IssueEntity("projectId", "issueId", "title", "description", "creatorUserId", IssueStatusValue.CLOSED, new HashSet<>())
					)
				 )
				.when(
					handle(command(new SetIssueInProgressCommand("projectId", "issueId")))
				 )
				.then(
					IllegalStateException.class
				 );
	}

}
