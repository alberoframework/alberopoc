package com.alberoframework.sample.issuetracker.service.core.query;

import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;

@Component
public class UserAdminPredicateQueryHandler extends AbstractIssueTrackerQueryHandler<UserAdminPredicateQuery, Boolean>{

    @Override
    protected Boolean doHandle(UserAdminPredicateQuery query, ContextualizedQueryGateway queryGateway) {
        Boolean response = queryGateway.handle(new UserQuery(query.getUserId()))
            .map(UserEntity::isAdmin)
            .orElse(false);
        return response;
    }
}
