package com.alberoframework.sample.issuetracker.component.query.handler;

import org.springframework.beans.factory.annotation.Autowired;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;
import com.alberoframework.component.query.handler.AbstractSimpleAuthenticatedQueryHandler;

public abstract class AbstractIssueTrackerQueryHandler<Q extends Query<R>, R> extends AbstractSimpleAuthenticatedQueryHandler<Q, R> implements IssueTrackerQueryHandler<Q, R> {

	@Autowired
	@Override
	public void setQueryGateway(SimpleAuthenticatedQueryGateway queryGateway) {
		super.setQueryGateway(queryGateway);
	}
	
}
