package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.FalsePredicateQuery;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;

public class FalseSimpleAuthenticatedPredicateQueryHandler extends AbstractSimpleAuthenticatedQueryHandler<FalsePredicateQuery, Boolean> {

	public FalseSimpleAuthenticatedPredicateQueryHandler() {
	}
	
	public FalseSimpleAuthenticatedPredicateQueryHandler(SimpleAuthenticatedQueryGateway queryGateway) {
		setQueryGateway(queryGateway);
	}
	
	@Override
	protected Boolean doHandle(FalsePredicateQuery query, ContextualizedQueryGateway queryGateway) {
		return false;
	}
	
}
