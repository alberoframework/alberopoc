package com.alberoframework.sample.issuetracker.component.query.handler;

import org.springframework.beans.factory.annotation.Autowired;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;
import com.alberoframework.component.query.handler.AbstractSimpleAuthenticatedEnvelopeQueryHandler;

public abstract class AbstractIssueTrackerEnvelopeQueryHandler<Q extends Query<R>, R> extends AbstractSimpleAuthenticatedEnvelopeQueryHandler<Q, R> implements IssueTrackerQueryHandler<Q, R> {

	@Autowired
	@Override
	public void setQueryGateway(SimpleAuthenticatedQueryGateway queryGateway) {
		super.setQueryGateway(queryGateway);
	}
	
}
