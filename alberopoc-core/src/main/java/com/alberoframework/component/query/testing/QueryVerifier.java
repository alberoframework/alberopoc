package com.alberoframework.component.query.testing;

import java.util.ArrayList;
import java.util.List;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.testing.bdd.outcome.TestFailureOutcome;
import com.alberoframework.testing.bdd.outcome.TestOutcome;
import com.alberoframework.testing.bdd.outcome.TestSuccessfulOutcome;
import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsVerifier;
import com.google.common.base.Objects;

public class QueryVerifier implements PortRequestsVerifier {
	
	private List<? extends RequestEnvelope<? extends Query<?>, ?>> expectedQueries = new ArrayList<>();
	
	public QueryVerifier(List<? extends RequestEnvelope<? extends Query<?>, ?>> expectedQueries) {
		this.expectedQueries = expectedQueries;
	}

	@Override
	public TestOutcome verifyPortRequests(PortRegistry portRegistry) {
		AbstractQueryGatewayStub queryGatewayStub = (AbstractQueryGatewayStub) portRegistry.get("queryGateway");
		if (queryGatewayStub == null) {
			return new QueryGatewayNotFoundInPortsRegistryTestFailure();
		}
		
		List<? extends RequestEnvelope<? extends Query<?>, ?>> sentQueries = queryGatewayStub.queriesReceived();
		
		if (!Objects.equal(sentQueries, expectedQueries)) {
			return new SentQueriesDifferFromExpectedQueries(sentQueries, expectedQueries);
		}
		
		return new TestSuccessfulOutcome();
	}
	
	
	public static class QueryGatewayNotFoundInPortsRegistryTestFailure extends TestFailureOutcome {
		@Override
		public String description() {
			return getClass().getSimpleName();
		}
	}
	
	public static class SentQueriesDifferFromExpectedQueries extends TestFailureOutcome {
		
		private List<? extends RequestEnvelope<? extends Query<?>, ?>> sentQueries = new ArrayList<>();
		
		private List<? extends RequestEnvelope<? extends Query<?>, ?>> expectedQueries = new ArrayList<>();
		
		public SentQueriesDifferFromExpectedQueries(List<? extends RequestEnvelope<? extends Query<?>, ?>> sentQueries, List<? extends RequestEnvelope<? extends Query<?>, ?>> expectedQueries) {
			this.sentQueries = sentQueries;
			this.expectedQueries = expectedQueries;
		}


		@Override
		public String description() {
			return getClass().getSimpleName() + ": " + System.lineSeparator() + "Sent Queries: " + sentQueries.toString() + System.lineSeparator() + "Expected Queries: " + expectedQueries.toString();
		}
	}

}
