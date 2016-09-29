package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.command.gateway.SimpleCommandGateway;
import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.query.gateway.SimpleQueryGateway;
import com.alberoframework.component.request.contract.RequestEnvelope;

public abstract class AbstractSimpleContextualizedCommandHandler<C extends Command<R>, R> extends AbstractCommandHandler<RequestEnvelope<C,R>, C, R, SimpleQueryGateway, SimpleCommandGateway> implements SimpleCommandHandler<C, R> {

	@Override
	protected ContextualizedQueryGateway contextualizeQueryGateway(RequestEnvelope<C, R> requestEnvelope,
			final SimpleQueryGateway queryGateway) {
		return new ContextualizedQueryGateway() {
			
			@Override
			public <R1> R1 handle(Query<R1> query) {
				return queryGateway.handle(new RequestEnvelope<Query<R1>, R1>(query));
			}
		};
	}
	
	@Override
	protected ContextualizedCommandGateway contextualizeCommandGateway(RequestEnvelope<C, R> requestEnvelope,
			final SimpleCommandGateway commandGateway) {
		return new ContextualizedCommandGateway() {
			
			@Override
			public <R1> R1 handle(Command<R1> command) {
				return commandGateway.handle(new RequestEnvelope<Command<R1>, R1>(command));
			}
		};
	}

}
