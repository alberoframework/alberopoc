package com.alberoframework.sample.issuetracker.service.core.query;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerEnvelopeQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueCategoryEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.IssueCategoryRepository;

import java.util.Optional;

@Setter
@Component
public class IssueCategoryQueryHandler extends AbstractIssueTrackerEnvelopeQueryHandler<IssueCategoryQuery, Optional<IssueCategoryEntity>>{

    @Autowired
    private IssueCategoryRepository issueCategoryRepository;

    @Override
    protected Optional<IssueCategoryEntity> doHandle(SimpleAuthenticatedRequestEnvelope<IssueCategoryQuery, Optional<IssueCategoryEntity>> env, ContextualizedQueryGateway queryGateway) {
        return Optional.ofNullable(
            issueCategoryRepository.findOne(env.getRequest().getIssueCategoryId())
        );
    }
}
