package com.alberoframework.sample.issuetracker.service.core.query;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.UserRepository;

import java.util.Optional;

@Setter
@Component
public class UserByUsernameQueryHandler extends AbstractIssueTrackerQueryHandler<UserByUsernameQuery, Optional<UserEntity>> {

    @Autowired
    private UserRepository userRepository;

    @Override
    protected Optional<UserEntity> doHandle(UserByUsernameQuery query, ContextualizedQueryGateway queryGateway) {
        return Optional.ofNullable(
            userRepository.findOneByUsername(query.getUsername())
        );
    }
}
