package com.alberoframework.component.command.testing;

import java.util.ArrayList;
import java.util.List;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsStubber;

public class CommandStubber implements PortRequestsStubber {

	private List<CommandStub<?>> stubs = new ArrayList<CommandStub<?>>();
	
	public CommandStubber(List<CommandStub<?>> stubs) {
		this.stubs = stubs;
	}

	@Override
	public void stubPortRequests(PortRegistry portRegistry) {
		AbstractCommandGatewayStub commandGatewayStub = (AbstractCommandGatewayStub) portRegistry.get("commandGateway");
		if (commandGatewayStub == null) {
			throw new IllegalStateException("Command Gateway not found in ports registry");
		}
		
		for (CommandStub<?> stub : stubs) {
			commandGatewayStub.stub(stub.getCommand(), stub.getResponse());
		}
	}
	
	public static class CommandStub<R> {
		
		private RequestEnvelope<? extends Command<R>, R> command;
		private R response;
		
		public CommandStub(RequestEnvelope<? extends Command<R>, R> command, R response) {
			this.command = command;
			this.response = response;
		}
		
		public CommandStub(RequestEnvelope<? extends Command<R>, R> command) {
			this.command = command;
		}

		public RequestEnvelope<? extends Command<R>, R> getCommand() {
			return command;
		}
		
		public R getResponse() {
			return response;
		}
	}
	
}
