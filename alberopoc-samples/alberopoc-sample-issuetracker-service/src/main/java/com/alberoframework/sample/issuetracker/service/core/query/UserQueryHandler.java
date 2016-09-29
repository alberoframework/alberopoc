package com.alberoframework.sample.issuetracker.service.core.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerEnvelopeQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.UserRepository;

import java.util.Optional;

@Component
public class UserQueryHandler extends AbstractIssueTrackerEnvelopeQueryHandler<UserQuery, Optional<UserEntity>>{

    @Autowired
    private UserRepository userRepository;

    @Override
    protected Optional<UserEntity> doHandle(SimpleAuthenticatedRequestEnvelope<UserQuery, Optional<UserEntity>> env, ContextualizedQueryGateway queryGateway) {
        return Optional.ofNullable(
            userRepository.findOne(env.getRequest().getUserId())
        );
    }
}
