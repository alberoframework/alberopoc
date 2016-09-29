package com.alberoframework.component.command.testing;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.gateway.SimpleCommandGateway;
import com.alberoframework.component.command.handler.SimpleCommandHandler;
import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.gateway.SimpleQueryGateway;
import com.alberoframework.component.query.testing.SimpleQueryGatewayStub;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.testing.EntityTestingStore;

public abstract class AbstractSimpleCommandHandlerTestSupport<CH extends SimpleCommandHandler<C, R>, C extends Command<R>, R, ES extends EntityTestingStore> extends AbstractCommandHandlerTestSupport<CH, RequestEnvelope<C, R>, C, R, SimpleQueryGateway, SimpleCommandGateway, ES> {

//	public  CommandHandlerTestOperation<CH, RequestEnvelope<C, R>, C, R> sendCommand(C command) {
//		return new CommandHandlerTestOperation<CH, RequestEnvelope<C, R>, C, R>(new RequestEnvelope<C, R>(command));
//	}

	public <C1 extends Command<R1>, R1> RequestEnvelope<C1, R1> command(C1 command) {
		return new RequestEnvelope<C1, R1>(command);
	}
	
	public <Q1 extends Query<R1>, R1> RequestEnvelope<Q1, R1> query(Q1 query) {
		return new RequestEnvelope<Q1, R1>(query);
	}
	
	@Override
	protected SimpleQueryGateway queryGateway() {
		return new SimpleQueryGatewayStub();
	}
	
	@Override
	protected SimpleCommandGateway commandGateway() {
		return new SimpleCommandGatewayStub();
	}
	
}
