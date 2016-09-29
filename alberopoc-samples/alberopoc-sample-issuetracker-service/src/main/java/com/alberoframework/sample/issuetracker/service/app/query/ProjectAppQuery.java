package com.alberoframework.sample.issuetracker.service.app.query;

import com.alberoframework.component.query.contract.AbstractQuery;
import com.alberoframework.hypermedia.HypermediaObjectResource;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter
public class ProjectAppQuery extends AbstractQuery<HypermediaObjectResource<ProjectEntity>>{

    private String projectId;
}
