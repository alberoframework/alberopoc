package com.alberoframework.sample.issuetracker.service.core.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.sample.issuetracker.component.command.handler.AbstractIssueTrackerVoidCommandHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.IssueEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.IssueRepository;

import lombok.Setter;

@Setter
@Component
public class AssignIssueCommandHandler extends AbstractIssueTrackerVoidCommandHandler<AssignIssueCommand> {

    @Autowired
    private IssueRepository issueRepository;

    @Override
    protected void doHandle(AssignIssueCommand command, ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway) {
        IssueEntity issue = issueRepository.findOne(command.getIssueId());

        issue.assign(command.getUserId());

        issueRepository.save(issue);
    }
}
