package com.alberoframework.component.command.testing;

import java.util.ArrayList;
import java.util.List;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.testing.bdd.outcome.TestFailureOutcome;
import com.alberoframework.testing.bdd.outcome.TestOutcome;
import com.alberoframework.testing.bdd.outcome.TestSuccessfulOutcome;
import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsVerifier;
import com.google.common.base.Objects;

public class CommandVerifier implements PortRequestsVerifier {
	
	private List<? extends RequestEnvelope<? extends Command<?>, ?>> expectedCommands = new ArrayList<>();
	
	public CommandVerifier(List<? extends RequestEnvelope<? extends Command<?>, ?>> expectedCommands) {
		this.expectedCommands = expectedCommands;
	}

	@Override
	public TestOutcome verifyPortRequests(PortRegistry portRegistry) {
		AbstractCommandGatewayStub commandGatewayStub = (AbstractCommandGatewayStub) portRegistry.get("commandGateway");
		if (commandGatewayStub == null) {
			return new CommandGatewayNotFoundInPortsRegistryTestFailure();
		}
		
		List<? extends RequestEnvelope<? extends Command<?>, ?>> sentCommands = commandGatewayStub.commandsReceived();
		
		if (!Objects.equal(sentCommands, expectedCommands)) {
			return new SentCommandsDifferFromExpectedCommands(sentCommands, expectedCommands);
		}
		
		return new TestSuccessfulOutcome();
	}
	
	
	public static class CommandGatewayNotFoundInPortsRegistryTestFailure extends TestFailureOutcome {
		@Override
		public String description() {
			return getClass().getSimpleName();
		}
	}
	
	public static class SentCommandsDifferFromExpectedCommands extends TestFailureOutcome {
		
		private List<? extends RequestEnvelope<? extends Command<?>, ?>> sentCommands = new ArrayList<>();
		
		private List<? extends RequestEnvelope<? extends Command<?>, ?>> expectedCommands = new ArrayList<>();
		
		public SentCommandsDifferFromExpectedCommands(List<? extends RequestEnvelope<? extends Command<?>, ?>> sentCommands, List<? extends RequestEnvelope<? extends Command<?>, ?>> expectedCommands) {
			this.sentCommands = sentCommands;
			this.expectedCommands = expectedCommands;
		}


		@Override
		public String description() {
			return getClass().getSimpleName() + ": " + System.lineSeparator() + "Sent Commands: " + sentCommands.toString() + System.lineSeparator() + "Expected Commands: " + expectedCommands.toString();
		}
	}

}
