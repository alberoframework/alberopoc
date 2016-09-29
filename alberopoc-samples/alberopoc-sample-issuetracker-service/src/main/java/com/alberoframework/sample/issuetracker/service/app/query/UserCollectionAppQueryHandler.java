package com.alberoframework.sample.issuetracker.service.app.query;

import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.core.iterable.Iterables;
import com.alberoframework.hypermedia.HypermediaCollectors;
import com.alberoframework.hypermedia.HypermediaObjectCollectionResource;
import com.alberoframework.hypermedia.HypermediaObjectResource;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.command.CreateProjectCommand;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;
import com.alberoframework.sample.issuetracker.service.core.query.UserCollectionQuery;

@Component
public class UserCollectionAppQueryHandler extends AbstractIssueTrackerQueryHandler<UserCollectionAppQuery, HypermediaObjectCollectionResource<UserEntity>> {

    @Override
    protected HypermediaObjectCollectionResource<UserEntity> doHandle(UserCollectionAppQuery query, ContextualizedQueryGateway queryGateway) {
        return Iterables.stream(queryGateway.handle(new UserCollectionQuery()))
            .map(user -> new HypermediaObjectResource<>(user))
            .collect(HypermediaCollectors.toObjectCollection())
            .withLink("create-project", new CreateProjectCommand());
    }
}
