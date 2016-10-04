package com.alberoframework.sample.issuetracker.service.core.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.component.command.handler.AbstractIssueTrackerVoidEnvelopeCommandHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.IssueRepository;

import lombok.Setter;

@Setter
@Component
public class CreateIssueCommandHandler extends AbstractIssueTrackerVoidEnvelopeCommandHandler<CreateIssueCommand> {

	@Autowired
	IssueRepository issueRepository;

	@Override
	protected void doHandle(SimpleAuthenticatedRequestEnvelope<CreateIssueCommand, VoidUnit> env, ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway) {
		final CreateIssueCommand command = env.getRequest();
		IssueEntity issue = IssueEntity.create(
			command.getProjectId(),
			command.getIssueId(),
			command.getTitle(),
			command.getDescription(),
			env.userId().orElse(null)
		);
		issueRepository.save(issue);
	}
}
