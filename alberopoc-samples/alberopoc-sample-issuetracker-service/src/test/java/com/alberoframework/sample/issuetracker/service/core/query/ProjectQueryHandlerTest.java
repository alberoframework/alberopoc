package com.alberoframework.sample.issuetracker.service.core.query;

import java.util.Optional;

import org.junit.Test;

import com.alberoframework.sample.issuetracker.component.query.testing.IssueTrackerQueryHandlerTestSupport;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;
import com.alberoframework.sample.issuetracker.service.core.value.ProjectMembershipTypeValue;
import com.alberoframework.sample.issuetracker.service.core.value.ProjectMembershipValue;
import com.google.common.collect.Sets;

public class ProjectQueryHandlerTest extends IssueTrackerQueryHandlerTestSupport<ProjectQueryHandler, ProjectQuery, Optional<ProjectEntity>> {

	@Test
	public TestSpecification testSuccess() {
		return   given(
					entities(
						new ProjectEntity("projectId", "project name", Sets.newHashSet(new ProjectMembershipValue("userId", ProjectMembershipTypeValue.USER))),
						new ProjectEntity("projectId2", "project name2", Sets.newHashSet())
					)
				 )
				.when(handle(query(new ProjectQuery("projectId"))))
				.then(nonEmpty(new ProjectEntity("projectId", "project name", Sets.newHashSet(new ProjectMembershipValue("userId", ProjectMembershipTypeValue.USER)))))
				.when(handle(query(new ProjectQuery("projectId2"))))
				.then(nonEmpty(new ProjectEntity("projectId2", "project name2", Sets.newHashSet())))
				.when(handle(query(new ProjectQuery("projectId3"))))
				.then(empty());
	}

}
