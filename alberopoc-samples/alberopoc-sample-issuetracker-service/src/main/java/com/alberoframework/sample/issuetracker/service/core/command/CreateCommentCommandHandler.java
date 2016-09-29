package com.alberoframework.sample.issuetracker.service.core.command;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.command.handler.AbstractIssueTrackerVoidCommandHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.CommentEntity;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.CommentRepository;

@Setter
@Component
public class CreateCommentCommandHandler extends AbstractIssueTrackerVoidCommandHandler<CreateCommentCommand> {

	@Autowired
	CommentRepository commentRepository;

	@Override
	protected void doHandle(CreateCommentCommand command, ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway) {
		CommentEntity comment = new CommentEntity(
			command.getCommentId(),
			command.getText(),
			command.getIssueId(),
			command.getAuthorUserId()
		);
		commentRepository.save(comment);
	}

}
