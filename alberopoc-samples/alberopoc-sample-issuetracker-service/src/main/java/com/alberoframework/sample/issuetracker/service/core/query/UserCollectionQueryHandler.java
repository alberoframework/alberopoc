package com.alberoframework.sample.issuetracker.service.core.query;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.UserRepository;

@Setter
@Component
public class UserCollectionQueryHandler extends AbstractIssueTrackerQueryHandler<UserCollectionQuery, Iterable<UserEntity>>{

    @Autowired
    private UserRepository userRepository;

    @Override
    protected Iterable<UserEntity> doHandle(UserCollectionQuery query, ContextualizedQueryGateway queryGateway) {
        return userRepository.findAll();
    }
}
