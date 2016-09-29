package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.NotPredicateQuery;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;

public class NotSimpleAuthenticatedPredicateQueryHandler extends AbstractSimpleAuthenticatedQueryHandler<NotPredicateQuery, Boolean> {

	public NotSimpleAuthenticatedPredicateQueryHandler() {
	}
	
	public NotSimpleAuthenticatedPredicateQueryHandler(SimpleAuthenticatedQueryGateway queryGateway) {
		setQueryGateway(queryGateway);
	}
	
	@Override
	protected Boolean doHandle(NotPredicateQuery query, ContextualizedQueryGateway queryGateway) {
		
		Boolean result = queryGateway.handle(query.getQuery());
		
		return !result;
	}
	
}
