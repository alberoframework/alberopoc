package com.alberoframework.sample.issuetracker.service.core.query;

import com.alberoframework.component.query.contract.AbstractPredicateQuery;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PACKAGE)
public class UserIsAssigneeOfIssuePredicateQuery extends AbstractPredicateQuery {
	
	private String userId;
	private String projectId;
	private String issueId;
    
}
