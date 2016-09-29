package com.alberoframework.sample.issuetracker.service.core.query;

import lombok.*;

import org.springframework.stereotype.Component;

import com.alberoframework.component.query.contract.AbstractQuery;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;

import java.util.Collection;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Getter
@Setter(AccessLevel.PACKAGE)
public class ProjectCollectionByMembershipQuery extends AbstractQuery<Collection<ProjectEntity>>{

    private String memberId;
}
