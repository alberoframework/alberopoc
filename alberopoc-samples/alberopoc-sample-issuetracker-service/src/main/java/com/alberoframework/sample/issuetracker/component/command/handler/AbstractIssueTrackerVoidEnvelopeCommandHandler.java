package com.alberoframework.sample.issuetracker.component.command.handler;

import org.springframework.beans.factory.annotation.Autowired;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.SimpleAuthenticatedCommandGateway;
import com.alberoframework.component.command.handler.AbstractSimpleAuthenticatedVoidEnvelopeCommandHandler;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;
import com.alberoframework.lang.VoidUnit;

public abstract class AbstractIssueTrackerVoidEnvelopeCommandHandler<C extends Command<VoidUnit>> extends AbstractSimpleAuthenticatedVoidEnvelopeCommandHandler<C> implements IssueTrackerCommandHandler<C, VoidUnit> {

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
