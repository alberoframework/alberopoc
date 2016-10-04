package com.alberoframework.sample.issuetracker.service.core.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.component.command.handler.AbstractIssueTrackerVoidEnvelopeCommandHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.CommentEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.CommentRepository;

import lombok.Setter;

@Setter
@Component
public class CreateCommentCommandHandler extends AbstractIssueTrackerVoidEnvelopeCommandHandler<CreateCommentCommand> {

	@Autowired
	CommentRepository commentRepository;

	@Override
	protected void doHandle(SimpleAuthenticatedRequestEnvelope<CreateCommentCommand, VoidUnit> env,
			ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway) {

		CreateCommentCommand command = env.getRequest();
		
		CommentEntity comment = new CommentEntity(
			command.getProjectId(),
			command.getIssueId(),
			command.getCommentId(),
			command.getText(),
			env.userId().orElse(null)
		);
		commentRepository.save(comment);
	}
	
}
