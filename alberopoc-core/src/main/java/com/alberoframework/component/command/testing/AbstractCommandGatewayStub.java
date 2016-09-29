package com.alberoframework.component.command.testing;

import java.util.List;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.testing.AbstractRequestGatewayStub;

public abstract class AbstractCommandGatewayStub extends AbstractRequestGatewayStub {

	protected void stub(RequestEnvelope<? extends Command<?>, ?> command, Object response) {
		stubRequest(command, response);
	}
	
	@SuppressWarnings("unchecked")
	public List<? extends RequestEnvelope<? extends Command<?>, ?>> commandsReceived() {
		return (List<? extends RequestEnvelope<? extends Command<?>, ?>>) requestsReceived();
	}

}
