package com.alberoframework.sample.issuetracker.service.core.command;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.command.handler.AbstractIssueTrackerVoidCommandHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueCategoryEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.IssueCategoryRepository;

@Setter
@Component
public class CreateIssueCategoryCommandHandler extends AbstractIssueTrackerVoidCommandHandler<CreateIssueCategoryCommand> {

	@Autowired
	IssueCategoryRepository issueCategoryRepository;

	@Override
	protected void doHandle(CreateIssueCategoryCommand command, ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway) {
		IssueCategoryEntity issueCategory = new IssueCategoryEntity(
			command.getIssueCategoryId(),
			command.getName()
		);
		issueCategoryRepository.save(issueCategory);
	}

}
