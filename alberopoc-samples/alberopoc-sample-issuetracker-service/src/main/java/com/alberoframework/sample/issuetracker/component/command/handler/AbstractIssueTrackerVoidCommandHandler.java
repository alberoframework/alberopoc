package com.alberoframework.sample.issuetracker.component.command.handler;

import org.springframework.beans.factory.annotation.Autowired;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.SimpleAuthenticatedCommandGateway;
import com.alberoframework.component.command.handler.AbstractSimpleAuthenticatedVoidCommandHandler;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;
import com.alberoframework.lang.VoidUnit;

public abstract class AbstractIssueTrackerVoidCommandHandler<C extends Command<VoidUnit>> extends AbstractSimpleAuthenticatedVoidCommandHandler<C> implements IssueTrackerCommandHandler<C, VoidUnit> {

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
