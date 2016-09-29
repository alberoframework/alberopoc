package com.alberoframework.component.command.testing;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.SimpleAuthenticatedCommandGateway;
import com.alberoframework.component.command.handler.SimpleAuthenticatedCommandHandler;
import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;
import com.alberoframework.component.query.testing.SimpleAuthenticatedQueryGatewayStub;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.component.request.testing.EntityTestingStore;

public abstract class AbstractSimpleAuthenticatedCommandHandlerTestSupport<CH extends SimpleAuthenticatedCommandHandler<C, R>, C extends Command<R>, R, ES extends EntityTestingStore> extends AbstractAuthenticatedCommandHandlerTestSupport<CH, SimpleAuthenticatedRequestEnvelope<C, R>, String, C, R, SimpleAuthenticatedQueryGateway, SimpleAuthenticatedCommandGateway, ES> {

//	public CommandHandlerTestOperation<CH, SimpleAuthenticatedRequestEnvelope<C, R>, C, R> sendCommand(C command, String userId) {
//		return new CommandHandlerTestOperation<CH, SimpleAuthenticatedRequestEnvelope<C, R>, C, R>(new SimpleAuthenticatedRequestEnvelope<C, R>(userId, command));
//	}

	public <C1 extends Command<R1>, R1> SimpleAuthenticatedRequestEnvelope<C1, R1> command(C1 command, String userId) {
		return new SimpleAuthenticatedRequestEnvelope<C1, R1>(userId, command);
	}
	
	public <C1 extends Command<R1>, R1> SimpleAuthenticatedRequestEnvelope<C1, R1> command(C1 command) {
		return new SimpleAuthenticatedRequestEnvelope<C1, R1>(command);
	}
	
	public <Q1 extends Query<R1>, R1> SimpleAuthenticatedRequestEnvelope<Q1, R1> query(Q1 query, String userId) {
		return new SimpleAuthenticatedRequestEnvelope<Q1, R1>(userId, query);
	}
	
	public <Q1 extends Query<R1>, R1> SimpleAuthenticatedRequestEnvelope<Q1, R1> query(Q1 query) {
		return new SimpleAuthenticatedRequestEnvelope<Q1, R1>(query);
	}
	
	@Override
	protected SimpleAuthenticatedQueryGateway queryGateway() {
		return new SimpleAuthenticatedQueryGatewayStub();
	}
	
	@Override
	protected SimpleAuthenticatedCommandGateway commandGateway() {
		return new SimpleAuthenticatedCommandGatewayStub();
	}
	
}
