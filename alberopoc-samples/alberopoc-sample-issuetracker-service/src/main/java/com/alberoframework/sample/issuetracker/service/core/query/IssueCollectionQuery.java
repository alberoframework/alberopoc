package com.alberoframework.sample.issuetracker.service.core.query;

import com.alberoframework.component.query.contract.AbstractQuery;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.value.IssueStatusValue;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PACKAGE)
public class IssueCollectionQuery extends AbstractQuery<Iterable<IssueEntity>> {

    private String projectId;
    private IssueStatusValue status;
    
	public IssueCollectionQuery(String projectId) {
		this.projectId = projectId;
	}
    
}
