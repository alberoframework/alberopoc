package com.alberoframework.sample.issuetracker.service.core.query;

import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.value.IssueRelatedUserValue;
import com.alberoframework.sample.issuetracker.service.core.value.IssueRelationTypeValue;

@Component
public class UserIsAssigneePredicateQueryHandler extends AbstractIssueTrackerQueryHandler<UserIsAssigneePredicateQuery, Boolean> {

    @Override
    protected Boolean doHandle(UserIsAssigneePredicateQuery query, ContextualizedQueryGateway queryGateway) {
        Boolean response = queryGateway.handle(new IssueQuery(query.getIssueId()))
            .map(issue -> issue.getRelatedUser().contains(new IssueRelatedUserValue(query.getUserId(), IssueRelationTypeValue.ASSIGNEE)))
            .orElse(false);
        return response;
    }
}
