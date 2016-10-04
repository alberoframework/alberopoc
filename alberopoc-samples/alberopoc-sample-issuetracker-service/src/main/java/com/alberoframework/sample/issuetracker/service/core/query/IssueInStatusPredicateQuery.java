package com.alberoframework.sample.issuetracker.service.core.query;

import com.alberoframework.component.query.contract.PredicateQuery;
import com.alberoframework.sample.issuetracker.service.core.value.IssueStatusValue;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
public class IssueInStatusPredicateQuery implements PredicateQuery {
	private String projectId;
    private String issueId;
    private IssueStatusValue status;
}
