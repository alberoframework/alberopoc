package com.alberoframework.sample.issuetracker.service.core.query;

import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerQueryHandler;

import java.util.Objects;

@Component
public class IssueInStatusPredicateQueryHandler extends AbstractIssueTrackerQueryHandler<IssueInStatusPredicateQuery, Boolean>{
    @Override
    protected Boolean doHandle(IssueInStatusPredicateQuery query, ContextualizedQueryGateway queryGateway) {
        Boolean response = queryGateway.handle(new IssueQuery(query.getIssueId()))
            .map(issue -> Objects.equals(issue.getStatus(), query.getStatus()))
            .orElse(false);

        return response;
    }
}
