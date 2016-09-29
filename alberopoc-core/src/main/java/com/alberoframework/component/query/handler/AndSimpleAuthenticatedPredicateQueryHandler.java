package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.AndPredicateQuery;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;

public class AndSimpleAuthenticatedPredicateQueryHandler extends AbstractSimpleAuthenticatedQueryHandler<AndPredicateQuery, Boolean> {

	public AndSimpleAuthenticatedPredicateQueryHandler() {
	}
	
	public AndSimpleAuthenticatedPredicateQueryHandler(SimpleAuthenticatedQueryGateway queryGateway) {
		setQueryGateway(queryGateway);
	}
	
	@Override
	protected Boolean doHandle(AndPredicateQuery query, ContextualizedQueryGateway queryGateway) {
		return queryGateway.handle(query.getFirst()) && queryGateway.handle(query.getSecond());
	}
	
}
