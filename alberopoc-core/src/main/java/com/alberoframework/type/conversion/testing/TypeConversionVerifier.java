package com.alberoframework.type.conversion.testing;

import java.util.ArrayList;
import java.util.List;

import com.alberoframework.testing.bdd.outcome.TestFailureOutcome;
import com.alberoframework.testing.bdd.outcome.TestOutcome;
import com.alberoframework.testing.bdd.outcome.TestSuccessfulOutcome;
import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsVerifier;
import com.google.common.base.Objects;

public class TypeConversionVerifier implements PortRequestsVerifier {
	
	private List<TypeConversionRequest<?, ?>> expectedConversionRequests = new ArrayList<>();
	
	public TypeConversionVerifier(List<TypeConversionRequest<?, ?>> expectedConversionRequests) {
		this.expectedConversionRequests = expectedConversionRequests;
	}

	@Override
	public TestOutcome verifyPortRequests(PortRegistry portRegistry) {
		TypeConversionGatewayStub typeConversionGatewayStub = (TypeConversionGatewayStub) portRegistry.get("typeConversionGateway");
		if (typeConversionGatewayStub == null) {
			return new TypeConversionGatewayNotFoundInPortsRegistryTestFailure();
		}
		
		List<TypeConversionRequest<?, ?>> sentConversionRequests = typeConversionGatewayStub.conversionRequestsReceived();
		
		if (!Objects.equal(sentConversionRequests, expectedConversionRequests)) {
			return new SentConversionRequestsDifferFromExpectedConversionRequests(sentConversionRequests, expectedConversionRequests);
		}
		
		return new TestSuccessfulOutcome();
	}
	
	
	public static class TypeConversionGatewayNotFoundInPortsRegistryTestFailure extends TestFailureOutcome {
		@Override
		public String description() {
			return getClass().getSimpleName();
		}
	}
	
	public static class SentConversionRequestsDifferFromExpectedConversionRequests extends TestFailureOutcome {
		
		private List<TypeConversionRequest<?, ?>> sentConversionRequests = new ArrayList<>();
		
		private List<TypeConversionRequest<?, ?>> expectedConversionRequests = new ArrayList<>();
		
		
		public SentConversionRequestsDifferFromExpectedConversionRequests(List<TypeConversionRequest<?, ?>> sentConversionRequests,
				List<TypeConversionRequest<?, ?>> expectedConversionRequests) {
			this.sentConversionRequests = sentConversionRequests;
			this.expectedConversionRequests = expectedConversionRequests;
		}

		@Override
		public String description() {
			return getClass().getSimpleName() + ": " + System.lineSeparator() + "Sent Conversion Requests: " + sentConversionRequests.toString() + System.lineSeparator() + "Expected Conversion Requests: " + expectedConversionRequests.toString();
		}
	}

}
