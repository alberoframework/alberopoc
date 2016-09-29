package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.TruePredicateQuery;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;

public class TrueSimpleAuthenticatedPredicateQueryHandler extends AbstractSimpleAuthenticatedQueryHandler<TruePredicateQuery, Boolean> {

	public TrueSimpleAuthenticatedPredicateQueryHandler() {
	}
	
	public TrueSimpleAuthenticatedPredicateQueryHandler(SimpleAuthenticatedQueryGateway queryGateway) {
		setQueryGateway(queryGateway);
	}
	
	@Override
	protected Boolean doHandle(TruePredicateQuery query, ContextualizedQueryGateway queryGateway) {
		return true;
	}
	
}
