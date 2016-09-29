package com.alberoframework.sample.issuetracker.service.core.command;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.lang.VoidUnit;
import com.alberoframework.sample.issuetracker.component.command.handler.AbstractIssueTrackerCommandHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.ProjectRepository;

@Setter
@Component
public class UpdateProjectCommandHandler extends AbstractIssueTrackerCommandHandler<UpdateProjectCommand, VoidUnit> {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    protected VoidUnit doHandle(UpdateProjectCommand command, ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway) {
        final ProjectEntity project = projectRepository.findOne(command.getProjectId());

        if (project == null) {
            throw new IllegalArgumentException(String.format("Project with id %s doesn't exists", command.getProjectId()));
        }

        project.setName(command.getName());
        project.setMemberships(command.getMemberships());

        projectRepository.save(project);

        return VoidUnit.instance();
    }
}
