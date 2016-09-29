package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.XorPredicateQuery;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;

public class XorSimpleAuthenticatedPredicateQueryHandler extends AbstractSimpleAuthenticatedQueryHandler<XorPredicateQuery, Boolean> {

	public XorSimpleAuthenticatedPredicateQueryHandler() {
	}
	
	public XorSimpleAuthenticatedPredicateQueryHandler(SimpleAuthenticatedQueryGateway queryGateway) {
		setQueryGateway(queryGateway);
	}
	
	@Override
	protected Boolean doHandle(XorPredicateQuery query, ContextualizedQueryGateway queryGateway) {
		
		Boolean first = queryGateway.handle(query.getFirst());
		Boolean second = queryGateway.handle(query.getSecond());
		
		return first ^ second;
	}
	
}
