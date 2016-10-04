package com.alberoframework.sample.issuetracker.service.core.query;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.IssueRepository;

import lombok.Setter;

@Setter
@Component
public class IssueQueryHandler extends AbstractIssueTrackerQueryHandler<IssueQuery, Optional<IssueEntity>>{

    @Autowired
    private IssueRepository issueRepository;

    @Override
    protected Optional<IssueEntity> doHandle(IssueQuery query, ContextualizedQueryGateway queryGateway) {
    	return Optional.ofNullable(issueRepository.findByProjectIdAndIssueId(query.getProjectId(), query.getIssueId()));
    }
    
}
