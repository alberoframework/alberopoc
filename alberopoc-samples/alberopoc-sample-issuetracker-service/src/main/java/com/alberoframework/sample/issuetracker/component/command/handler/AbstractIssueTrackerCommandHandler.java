package com.alberoframework.sample.issuetracker.component.command.handler;

import org.springframework.beans.factory.annotation.Autowired;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.SimpleAuthenticatedCommandGateway;
import com.alberoframework.component.command.handler.AbstractSimpleAuthenticatedCommandHandler;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;

public abstract class AbstractIssueTrackerCommandHandler<C extends Command<R>, R> extends AbstractSimpleAuthenticatedCommandHandler<C, R> implements IssueTrackerCommandHandler<C, R> {

	@Autowired
	@Override
	public void setQueryGateway(SimpleAuthenticatedQueryGateway queryGateway) {
		super.setQueryGateway(queryGateway);
	}
	
	@Autowired
	@Override
	public void setCommandGateway(SimpleAuthenticatedCommandGateway commandGateway) {
		super.setCommandGateway(commandGateway);
	}
	
}
