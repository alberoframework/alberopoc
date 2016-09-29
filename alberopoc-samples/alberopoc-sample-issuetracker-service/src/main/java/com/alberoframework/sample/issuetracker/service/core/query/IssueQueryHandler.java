package com.alberoframework.sample.issuetracker.service.core.query;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerEnvelopeQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.IssueRepository;

import java.util.Optional;

@Setter
@Component
public class IssueQueryHandler extends AbstractIssueTrackerEnvelopeQueryHandler<IssueQuery, Optional<IssueEntity>>{

    @Autowired
    private IssueRepository issueRepository;

    @Override
    protected Optional<IssueEntity> doHandle(SimpleAuthenticatedRequestEnvelope<IssueQuery, Optional<IssueEntity>> env, ContextualizedQueryGateway queryGateway) {
        return Optional.ofNullable(
            issueRepository.findOne(env.getRequest().getIssueId())
        );
    }
}
