package com.alberoframework.sample.issuetracker.service.core.query;

import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerQueryHandler;

@Component
public class UserIsAssigneeOfIssuePredicateQueryHandler extends AbstractIssueTrackerQueryHandler<UserIsAssigneeOfIssuePredicateQuery, Boolean> {

    @Override
    protected Boolean doHandle(UserIsAssigneeOfIssuePredicateQuery query, ContextualizedQueryGateway queryGateway) {
        Boolean response = queryGateway.handle(new IssueQuery(query.getProjectId(), query.getIssueId()))
            .map(issue -> issue.getAssignedUserIds().contains(query.getUserId()))
            .orElse(false);
        return response;
    }
}
