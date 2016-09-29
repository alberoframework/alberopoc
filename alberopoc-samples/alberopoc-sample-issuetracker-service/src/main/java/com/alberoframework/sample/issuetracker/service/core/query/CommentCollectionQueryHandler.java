package com.alberoframework.sample.issuetracker.service.core.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerEnvelopeQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.CommentEntity;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.CommentRepository;

import java.util.Collection;
import java.util.Optional;

@Component
public class CommentCollectionQueryHandler extends AbstractIssueTrackerEnvelopeQueryHandler<CommentCollectionQuery, Iterable<CommentEntity>>{

    @Autowired
    private CommentRepository commentRepository;

    @Override
    protected Iterable<CommentEntity> doHandle(SimpleAuthenticatedRequestEnvelope<CommentCollectionQuery, Iterable<CommentEntity>> env, ContextualizedQueryGateway queryGateway) {
        return commentRepository.findAllByIssueId(env.getRequest().getIssueId());
    }
}
