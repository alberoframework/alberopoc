package com.alberoframework.sample.issuetracker.service.app.query;

import com.alberoframework.component.query.contract.AbstractQuery;
import com.alberoframework.hypermedia.HypermediaObjectCollectionResource;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectIssuesCollectionAppQuery extends AbstractQuery<HypermediaObjectCollectionResource<IssueEntity>> {
    private String projectId;
}
