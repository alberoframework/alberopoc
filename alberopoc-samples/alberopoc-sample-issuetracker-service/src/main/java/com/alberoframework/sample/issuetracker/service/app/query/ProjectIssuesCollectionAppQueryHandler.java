package com.alberoframework.sample.issuetracker.service.app.query;

import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.core.iterable.Iterables;
import com.alberoframework.hypermedia.HypermediaCollectors;
import com.alberoframework.hypermedia.HypermediaObjectCollectionResource;
import com.alberoframework.hypermedia.HypermediaObjectResource;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerEnvelopeQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.query.IssueCollectionQuery;

@Component
public class ProjectIssuesCollectionAppQueryHandler extends AbstractIssueTrackerEnvelopeQueryHandler<ProjectIssuesCollectionAppQuery, HypermediaObjectCollectionResource<IssueEntity>> {

    @Override
    protected HypermediaObjectCollectionResource<IssueEntity> doHandle(SimpleAuthenticatedRequestEnvelope<ProjectIssuesCollectionAppQuery, HypermediaObjectCollectionResource<IssueEntity>> env, ContextualizedQueryGateway queryGateway) {
        return Iterables.stream(queryGateway.handle(new IssueCollectionQuery(env.getRequest().getProjectId(), null)))
			            .map(issue -> new HypermediaObjectResource<>(issue))
			            .collect(HypermediaCollectors.toObjectCollection());
    }
}
