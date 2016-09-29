package com.alberoframework.component.query.handler;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.handler.AbstractRequestHandler;
import com.alberoframework.core.validation.Validation;

public abstract class AbstractQueryHandler<ENV extends RequestEnvelope<Q, R>, Q extends Query<R>, R, QG> extends AbstractRequestHandler<ENV, Q, R> implements QueryHandler<ENV, Q, R> {

	private QG queryGateway;
	
	@Override
	public final R handle(ENV requestEnvelope) {
		R response = handleInternal(requestEnvelope, contextualizeQueryGateway(requestEnvelope, queryGateway));
		Validation.validate(response != null, NullPointerException::new, "Handler responded with null, you shouldnt ever use null inside a handler, if you need to return an empty value, use Optional");
		return response;
		
	}
	
	protected abstract R handleInternal(ENV requestEnvelope, ContextualizedQueryGateway queryGateway);
	
	protected abstract ContextualizedQueryGateway contextualizeQueryGateway(ENV requestEnvelope, QG queryGateway);
	
	public void setQueryGateway(QG queryGateway) {
		this.queryGateway = queryGateway;
	}
	
}
