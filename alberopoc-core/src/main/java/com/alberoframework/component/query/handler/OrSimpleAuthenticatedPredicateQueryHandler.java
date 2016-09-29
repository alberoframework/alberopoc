package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.OrPredicateQuery;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;

public class OrSimpleAuthenticatedPredicateQueryHandler extends AbstractSimpleAuthenticatedQueryHandler<OrPredicateQuery, Boolean> {

	public OrSimpleAuthenticatedPredicateQueryHandler() {
	}

	public OrSimpleAuthenticatedPredicateQueryHandler(SimpleAuthenticatedQueryGateway queryGateway) {
		setQueryGateway(queryGateway);
	}
	
	@Override
	protected Boolean doHandle(OrPredicateQuery query, ContextualizedQueryGateway queryGateway) {
		return queryGateway.handle(query.getFirst()) || queryGateway.handle(query.getSecond());
	}
	
}
