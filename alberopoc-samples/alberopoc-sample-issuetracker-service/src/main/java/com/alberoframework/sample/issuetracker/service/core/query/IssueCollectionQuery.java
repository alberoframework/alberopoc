package com.alberoframework.sample.issuetracker.service.core.query;

import lombok.*;

import java.util.Collection;

import com.alberoframework.component.query.contract.AbstractQuery;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.value.IssueRelatedUserValue;
import com.alberoframework.sample.issuetracker.service.core.value.IssueStatusValue;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PACKAGE)
public class IssueCollectionQuery extends AbstractQuery<Collection<IssueEntity>> {

    private String projectId;
    private IssueStatusValue status;
    private String issueCategoryId;
    private IssueRelatedUserValue relatedUser;
}
