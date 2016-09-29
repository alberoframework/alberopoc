package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.command.gateway.SimpleAuthenticatedCommandGateway;
import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;

public abstract class AbstractSimpleAuthenticatedContextualizedCommandHandler<C extends Command<R>, R> extends AbstractAuthenticatedCommandHandler<SimpleAuthenticatedRequestEnvelope<C, R>, String, C, R, SimpleAuthenticatedQueryGateway, SimpleAuthenticatedCommandGateway> implements SimpleAuthenticatedCommandHandler<C, R> {

	@Override
	protected ContextualizedQueryGateway contextualizeQueryGateway(final SimpleAuthenticatedRequestEnvelope<C, R> requestEnvelope,
			final SimpleAuthenticatedQueryGateway queryGateway) {
		return new ContextualizedQueryGateway() {
			
			@Override
			public <R1> R1 handle(Query<R1> query) {
				if (requestEnvelope.userId().isPresent())
					return queryGateway.handle(new SimpleAuthenticatedRequestEnvelope<Query<R1>, R1>(requestEnvelope.userId().get(), query));
				return queryGateway.handle(new SimpleAuthenticatedRequestEnvelope<Query<R1>, R1>(query));
			}
			
		};
	}
	
	@Override
	protected ContextualizedCommandGateway contextualizeCommandGateway(final SimpleAuthenticatedRequestEnvelope<C, R> requestEnvelope,
			final SimpleAuthenticatedCommandGateway commandGateway) {
		return new ContextualizedCommandGateway() {
			
			@Override
			public <R1> R1 handle(Command<R1> command) {
				if (requestEnvelope.userId().isPresent())
					return commandGateway.handle(new SimpleAuthenticatedRequestEnvelope<Command<R1>, R1>(requestEnvelope.userId().get(), command));
				return commandGateway.handle(new SimpleAuthenticatedRequestEnvelope<Command<R1>, R1>(command));
			}
		};
	}
	
}
