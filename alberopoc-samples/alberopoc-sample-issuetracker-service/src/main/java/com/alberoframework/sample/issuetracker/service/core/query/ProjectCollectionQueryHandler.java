package com.alberoframework.sample.issuetracker.service.core.query;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.ProjectRepository;

@Setter
@Component
public class ProjectCollectionQueryHandler extends AbstractIssueTrackerQueryHandler<ProjectCollectionQuery, Iterable<ProjectEntity>> {
    
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    protected Iterable<ProjectEntity> doHandle(ProjectCollectionQuery query, ContextualizedQueryGateway queryGateway) {
        return projectRepository.findAll();
    }
}
