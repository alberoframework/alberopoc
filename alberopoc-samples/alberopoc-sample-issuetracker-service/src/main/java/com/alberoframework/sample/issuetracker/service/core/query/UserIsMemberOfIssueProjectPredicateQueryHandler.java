package com.alberoframework.sample.issuetracker.service.core.query;

import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.value.MembershipValue;

import java.util.Objects;
import java.util.function.Predicate;

@Component
public class UserIsMemberOfIssueProjectPredicateQueryHandler extends AbstractIssueTrackerQueryHandler<UserIsMemberOfIssueProjectPredicateQuery, Boolean> {
    @Override
    protected Boolean doHandle(UserIsMemberOfIssueProjectPredicateQuery query, ContextualizedQueryGateway queryGateway) {
        final Predicate<MembershipValue> predicate = query.getMembershipType()
            .map(type -> Predicate.<MembershipValue>isEqual(new MembershipValue(query.getUserId(), type)))
            .orElse(membership -> Objects.equals(membership.getUserId(), query.getProjectId()));

        Boolean result = queryGateway.handle(new ProjectQuery(query.getProjectId()))
            .map(project -> project.getMemberships().stream().anyMatch(predicate))
            .orElse(false);

        return result;
    }
}
