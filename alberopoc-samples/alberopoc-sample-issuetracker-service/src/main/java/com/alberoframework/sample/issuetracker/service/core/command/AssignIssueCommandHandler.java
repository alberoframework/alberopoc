package com.alberoframework.sample.issuetracker.service.core.command;

import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.command.handler.AbstractSimpleVoidCommandHandler;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.IssueRepository;

@Setter
@Component
public class AssignIssueCommandHandler extends AbstractSimpleVoidCommandHandler<AssignIssueCommand> {

    @Autowired
    private IssueRepository issueRepository;

    @Override
    protected void doHandle(AssignIssueCommand command, ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway) {
        IssueEntity issue = issueRepository.findOne(command.getIssueId());

        issue.assign(command.getAssignedUserId());

        issueRepository.save(issue);
    }
}
