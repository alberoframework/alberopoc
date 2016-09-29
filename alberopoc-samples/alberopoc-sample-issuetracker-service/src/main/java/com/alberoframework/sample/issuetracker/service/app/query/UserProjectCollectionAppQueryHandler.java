package com.alberoframework.sample.issuetracker.service.app.query;

import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.hypermedia.HypermediaCollectors;
import com.alberoframework.hypermedia.HypermediaObjectCollectionResource;
import com.alberoframework.hypermedia.HypermediaObjectResource;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerEnvelopeQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;
import com.alberoframework.sample.issuetracker.service.core.query.ProjectCollectionByMembershipQuery;

@Component
public class UserProjectCollectionAppQueryHandler extends AbstractIssueTrackerEnvelopeQueryHandler<UserProjectCollectionAppQuery, HypermediaObjectCollectionResource<ProjectEntity>> {

    @Override
    protected HypermediaObjectCollectionResource<ProjectEntity> doHandle(SimpleAuthenticatedRequestEnvelope<UserProjectCollectionAppQuery, HypermediaObjectCollectionResource<ProjectEntity>> env, ContextualizedQueryGateway queryGateway) {
        return queryGateway.handle(new ProjectCollectionByMembershipQuery(env.userId().orElseThrow(IllegalStateException::new)))
            .stream()
            .map(project -> new HypermediaObjectResource<>(project)
                .withLink("projectIssues", new ProjectIssuesCollectionAppQuery(project.getProjectId()))
            )
            .collect(HypermediaCollectors.toObjectCollection());
    }
}
