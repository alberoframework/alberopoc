package com.alberoframework.sample.issuetracker.service.core.query;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerEnvelopeQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.ProjectRepository;
import com.alberoframework.sample.issuetracker.service.core.repository.UserRepository;

import java.util.Optional;

@Setter
@Component
public class ProjectQueryHandler extends AbstractIssueTrackerEnvelopeQueryHandler<ProjectQuery, Optional<ProjectEntity>>{

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    protected Optional<ProjectEntity> doHandle(SimpleAuthenticatedRequestEnvelope<ProjectQuery, Optional<ProjectEntity>> env, ContextualizedQueryGateway queryGateway) {
        return Optional.ofNullable(
            projectRepository.findOne(env.getRequest().getProjectId())
        );
    }
}
