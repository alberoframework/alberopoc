package com.alberoframework.sample.issuetracker.service.core.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.command.handler.AbstractIssueTrackerVoidCommandHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.ProjectRepository;

import lombok.Setter;

@Setter
@Component
public class AddProjectMembershipCommandHandler extends AbstractIssueTrackerVoidCommandHandler<AddProjectMembershipCommand> {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    protected void doHandle(AddProjectMembershipCommand command, ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway) {
        ProjectEntity project = projectRepository.findOne(command.getProjectId());

        project.addMembership(command.getMembership());

        projectRepository.save(project);
    }
}
