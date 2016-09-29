package com.alberoframework.sample.issuetracker.service.core.query;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.IssueRepository;
import com.google.common.collect.ImmutableList;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatcher;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Setter
@Component
public class IssueCollectionQueryHandler extends AbstractIssueTrackerQueryHandler<IssueCollectionQuery, Collection<IssueEntity>> {

    @Autowired
    private IssueRepository issueRepository;

    @Override
    protected Collection<IssueEntity> doHandle(IssueCollectionQuery query, ContextualizedQueryGateway queryGateway) {
        return ImmutableList.copyOf(issueRepository.findAll(Example.of(new IssueEntity(
            null,
            query.getProjectId(),
            null,
            query.getIssueCategoryId(),
            null,
            query.getStatus(),
            null
        ))));
    }
}
