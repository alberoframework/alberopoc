package com.alberoframework.sample.issuetracker.service.core.query;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.UserRepository;

import lombok.Setter;

@Component
@Setter
public class UserQueryHandler extends AbstractIssueTrackerQueryHandler<UserQuery, Optional<UserEntity>>{

    @Autowired
    private UserRepository userRepository;

    @Override
    protected Optional<UserEntity> doHandle(UserQuery query, ContextualizedQueryGateway queryGateway) {
    	return Optional.ofNullable(userRepository.findOne(query.getUserId()));
    }
}
