package com.alberoframework.sample.issuetracker.service.app.query;

import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.core.iterable.Iterables;
import com.alberoframework.hypermedia.HypermediaCollectors;
import com.alberoframework.hypermedia.HypermediaObjectCollectionResource;
import com.alberoframework.hypermedia.HypermediaObjectResource;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerEnvelopeQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.command.CreateProjectCommand;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;
import com.alberoframework.sample.issuetracker.service.core.query.ProjectCollectionQuery;

@Component
public class ProjectCollectionAppQueryHandler extends AbstractIssueTrackerEnvelopeQueryHandler<ProjectCollectionAppQuery, HypermediaObjectCollectionResource<ProjectEntity>> {
    @Override
    protected HypermediaObjectCollectionResource<ProjectEntity> doHandle(SimpleAuthenticatedRequestEnvelope<ProjectCollectionAppQuery, HypermediaObjectCollectionResource<ProjectEntity>> env, ContextualizedQueryGateway queryGateway) {
        return Iterables.stream(queryGateway.handle(new ProjectCollectionQuery()))
            .map(project -> new HypermediaObjectResource<>(project))
            .collect(HypermediaCollectors.toObjectCollection())
            .withLink("create-project", new CreateProjectCommand());
    }
}
