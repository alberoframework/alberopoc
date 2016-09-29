package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.handler.AbstractRequestHandler;
import com.alberoframework.core.validation.Validation;

public abstract class AbstractCommandHandler<ENV extends RequestEnvelope<C, R>, C extends Command<R>, R, QG, CG> extends AbstractRequestHandler<ENV, C, R> implements CommandHandler<ENV, C, R> {

	private QG queryGateway;
	
	private CG commandGateway;

	@Override
	public final R handle(ENV requestEnvelope) {
		R response = handleInternal(requestEnvelope, contextualizeQueryGateway(requestEnvelope, queryGateway), contextualizeCommandGateway(requestEnvelope, commandGateway));
		Validation.validate(response != null, NullPointerException::new, "Handler responded with null, you shouldnt ever use null inside a handler, if you need to return an empty value, use Optional");
		return response;
	}
	
	protected abstract R handleInternal(ENV requestEnvelope, ContextualizedQueryGateway queryGateway, ContextualizedCommandGateway commandGateway);
	
	protected abstract ContextualizedQueryGateway contextualizeQueryGateway(ENV requestEnvelope, QG queryGateway);
	
	protected abstract ContextualizedCommandGateway contextualizeCommandGateway(ENV requestEnvelope, CG commandGateway);
	
	public void setCommandGateway(CG commandGateway) {
		this.commandGateway = commandGateway;
	}
	
	public void setQueryGateway(QG queryGateway) {
		this.queryGateway = queryGateway;
	}
	
}