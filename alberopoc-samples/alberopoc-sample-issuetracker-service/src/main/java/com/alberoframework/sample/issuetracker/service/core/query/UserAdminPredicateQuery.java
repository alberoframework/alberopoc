package com.alberoframework.sample.issuetracker.service.core.query;

import com.alberoframework.component.query.contract.AbstractPredicateQuery;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PACKAGE)
public class UserAdminPredicateQuery extends AbstractPredicateQuery {
    private String userId;
}
