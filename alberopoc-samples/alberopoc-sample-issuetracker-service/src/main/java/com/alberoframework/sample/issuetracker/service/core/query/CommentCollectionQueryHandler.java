package com.alberoframework.sample.issuetracker.service.core.query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.CommentEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.CommentRepository;

import lombok.Setter;

@Component
@Setter
public class CommentCollectionQueryHandler extends AbstractIssueTrackerQueryHandler<CommentCollectionQuery, Iterable<CommentEntity>>{

    @Autowired
    private CommentRepository commentRepository;

    @Override
    protected Iterable<CommentEntity> doHandle(CommentCollectionQuery query, ContextualizedQueryGateway queryGateway) {
    	return commentRepository.findByProjectIdAndIssueId(query.getProjectId(), query.getIssueId());
    }
    
}
