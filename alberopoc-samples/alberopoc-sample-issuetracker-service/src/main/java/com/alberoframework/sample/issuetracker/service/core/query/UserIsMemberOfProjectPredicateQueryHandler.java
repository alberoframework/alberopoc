package com.alberoframework.sample.issuetracker.service.core.query;

import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.value.ProjectMembershipValue;

@Component
public class UserIsMemberOfProjectPredicateQueryHandler extends AbstractIssueTrackerQueryHandler<UserIsMemberOfProjectPredicateQuery, Boolean> {
    @Override
    protected Boolean doHandle(UserIsMemberOfProjectPredicateQuery query, ContextualizedQueryGateway queryGateway) {

    	Boolean result = queryGateway.handle(new ProjectQuery(query.getProjectId()))
            .map(p -> query.getMembershipType()
    					   .map(t -> p.containsMembership(new ProjectMembershipValue(query.getUserId(), t)))
    					   .orElse(p.userIsMember(query.getUserId()))
             )
            .orElse(false);

        return result;
    }
}
