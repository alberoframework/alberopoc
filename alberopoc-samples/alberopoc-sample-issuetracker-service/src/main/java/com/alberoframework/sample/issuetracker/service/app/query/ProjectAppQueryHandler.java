package com.alberoframework.sample.issuetracker.service.app.query;

import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.hypermedia.HypermediaObjectResource;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerEnvelopeQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.command.UpdateProjectCommand;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;
import com.alberoframework.sample.issuetracker.service.core.query.ProjectQuery;

@Component
public class ProjectAppQueryHandler extends AbstractIssueTrackerEnvelopeQueryHandler<ProjectAppQuery, HypermediaObjectResource<ProjectEntity>>{
    @Override
    protected HypermediaObjectResource<ProjectEntity> doHandle(SimpleAuthenticatedRequestEnvelope<ProjectAppQuery, HypermediaObjectResource<ProjectEntity>> env, ContextualizedQueryGateway queryGateway) {
        final String projectId = env.getRequest().getProjectId();

        return  queryGateway.handle(new ProjectQuery(projectId))
				            .map(project -> new HypermediaObjectResource<>(project)
						    .withLink("update", new UpdateProjectCommand(projectId))
						    .withLink("issues", new ProjectIssuesCollectionAppQuery(projectId)))
				            .orElseThrow(() -> new IllegalStateException("No project with id " + projectId + " found"));
    }
}
