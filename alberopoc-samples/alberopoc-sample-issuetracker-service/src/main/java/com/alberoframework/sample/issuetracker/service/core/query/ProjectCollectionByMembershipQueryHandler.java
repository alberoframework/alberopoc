package com.alberoframework.sample.issuetracker.service.core.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerEnvelopeQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.ProjectRepository;

import java.util.Collection;

@Component
public class ProjectCollectionByMembershipQueryHandler extends AbstractIssueTrackerEnvelopeQueryHandler<ProjectCollectionByMembershipQuery, Collection<ProjectEntity>>{

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    protected Collection<ProjectEntity> doHandle(SimpleAuthenticatedRequestEnvelope<ProjectCollectionByMembershipQuery, Collection<ProjectEntity>> env, ContextualizedQueryGateway queryGateway) {
        return projectRepository.findAllByMembershipsUserId(env.getRequest().getMemberId());
    }
}
