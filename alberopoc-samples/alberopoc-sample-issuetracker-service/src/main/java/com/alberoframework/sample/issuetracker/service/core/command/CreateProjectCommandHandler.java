package com.alberoframework.sample.issuetracker.service.core.command;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.command.handler.AbstractIssueTrackerVoidCommandHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.CommentEntity;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.CommentRepository;
import com.alberoframework.sample.issuetracker.service.core.repository.ProjectRepository;

@Setter
@Component
public class CreateProjectCommandHandler extends AbstractIssueTrackerVoidCommandHandler<CreateProjectCommand> {

	@Autowired
	ProjectRepository projectRepository;


	@Override
	protected void doHandle(CreateProjectCommand command, ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway) {
		ProjectEntity project = new ProjectEntity(
			command.getProjectId(),
			command.getName(),
			command.getMemberships()
		);
		projectRepository.save(project);
	}
}
