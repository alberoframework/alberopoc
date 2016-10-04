package com.alberoframework.sample.issuetracker.service.core.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.IssueRepository;

import lombok.Setter;

@Setter
@Component
public class IssueCollectionQueryHandler extends AbstractIssueTrackerQueryHandler<IssueCollectionQuery, Iterable<IssueEntity>> {

    @Autowired
    private IssueRepository issueRepository;

    @Override
    protected Iterable<IssueEntity> doHandle(IssueCollectionQuery query, ContextualizedQueryGateway queryGateway) {
        return issueRepository.findAll(Example.of(new IssueEntity(
            query.getProjectId(),
            null,
            null,
            null,
            null,
            query.getStatus(),
            null
        )));
    }
}
