package com.alberoframework.testing.bdd.testcase.port;

import java.util.ArrayList;
import java.util.List;

import com.alberoframework.testing.bdd.outcome.TestOutcome;
import com.alberoframework.testing.bdd.outcome.TestSuccessfulOutcome;
import com.alberoframework.testing.bdd.port.PortRegistry;

public class PortRequestsVerifierChain implements PortRequestsVerifier {

	private List<PortRequestsVerifier> verifiers = new ArrayList<PortRequestsVerifier>();
	
	public PortRequestsVerifierChain(List<PortRequestsVerifier> verifiers) {
		this.verifiers = verifiers;
	}
	
	@Override
	public TestOutcome verifyPortRequests(PortRegistry portRegistry) {
		for (PortRequestsVerifier verifier : verifiers) {
			TestOutcome verifierOutcome = verifier.verifyPortRequests(portRegistry);
			if (verifierOutcome.failure())
				return verifierOutcome;
		}
		return new TestSuccessfulOutcome();
	}
	
}
