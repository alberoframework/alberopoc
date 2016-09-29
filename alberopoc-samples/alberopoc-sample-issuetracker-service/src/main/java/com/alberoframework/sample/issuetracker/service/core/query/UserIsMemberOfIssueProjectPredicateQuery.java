package com.alberoframework.sample.issuetracker.service.core.query;

import lombok.*;

import java.util.Optional;

import com.alberoframework.component.query.contract.AbstractPredicateQuery;
import com.alberoframework.sample.issuetracker.service.core.value.MembershipTypeValue;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PACKAGE)
public class UserIsMemberOfIssueProjectPredicateQuery extends AbstractPredicateQuery {
    private String userId;
    private Optional<MembershipTypeValue> membershipType;
    private String projectId;
}
