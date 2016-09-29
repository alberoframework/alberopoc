package com.alberoframework.sample.issuetracker.endpoint.url;

import com.alberoframework.component.url.RequestUrlMapper;
import com.alberoframework.component.url.SpringRequestUrlMapper;
import com.alberoframework.sample.issuetracker.content.conversion.converter.json.IssueTrackerJacksonObjectMapper;
import com.alberoframework.sample.issuetracker.service.app.query.ProjectAppQuery;
import com.alberoframework.sample.issuetracker.service.app.query.ProjectCollectionAppQuery;
import com.alberoframework.sample.issuetracker.service.app.query.ProjectIssuesCollectionAppQuery;
import com.alberoframework.sample.issuetracker.service.app.query.RootAppQuery;
import com.alberoframework.sample.issuetracker.service.app.query.UserCollectionAppQuery;
import com.alberoframework.sample.issuetracker.service.app.query.UserProjectCollectionAppQuery;
import com.alberoframework.sample.issuetracker.service.core.command.CreateIssueCategoryCommand;
import com.alberoframework.sample.issuetracker.service.core.command.CreateIssueCommand;
import com.alberoframework.sample.issuetracker.service.core.command.CreateProjectCommand;
import com.alberoframework.sample.issuetracker.service.core.command.CreateUserCommand;
import com.alberoframework.sample.issuetracker.service.core.command.UpdateProjectCommand;

public class IssueTrackerUrlMapping {

	public static RequestUrlMapper createRequestUrlMapperWithMappings() {
		RequestUrlMapper requestUrlMapper = new SpringRequestUrlMapper(new IssueTrackerJacksonObjectMapper(), "");
		
		requestUrlMapper.registerQuery("/api", RootAppQuery.class);

		requestUrlMapper.registerQuery("/api/dashboard", UserProjectCollectionAppQuery.class);

		requestUrlMapper.registerQuery("/api/projects", ProjectCollectionAppQuery.class);
		requestUrlMapper.registerCommand("/api/projects/new", CreateProjectCommand.class);
		requestUrlMapper.registerQuery("/api/projects/{projectId}", ProjectAppQuery.class);
		requestUrlMapper.registerCommand("/api/projects/{projectId}", UpdateProjectCommand.class);
		requestUrlMapper.registerQuery("/api/projects/{projectId}/issues", ProjectIssuesCollectionAppQuery.class);
		requestUrlMapper.registerCommand("/api/projects/{projectId}/issues/new", CreateIssueCommand.class);
		requestUrlMapper.registerCommand("/api/issues-categories/new", CreateIssueCategoryCommand.class);

		requestUrlMapper.registerQuery("/api/users", UserCollectionAppQuery.class);
		requestUrlMapper.registerCommand("/api/users/new", CreateUserCommand.class);

		return requestUrlMapper;
	}
	
}
