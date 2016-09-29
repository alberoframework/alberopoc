package com.alberoframework.testing.bdd.testcase.outcome;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsVerifier;

public class PortRegistryEmptyButVerifierIsPresentTestCaseFailure<C extends TestContext<S>, S, O extends TestOperation<C, ?>> extends TestCaseFailureOutcome<C, S, O> {

	private PortRequestsVerifier portRequestsVerifier;
	
	public PortRegistryEmptyButVerifierIsPresentTestCaseFailure(C testContext, O testOperation, PortRequestsVerifier portRequestsVerifier) {
		super(testContext, testOperation);
		this.portRequestsVerifier = portRequestsVerifier;
	}

	@Override
	public String testCaseFailureDescription() {
		return "Context has an empty ports registry but a verifier is present in the test case: " + portRequestsVerifier.getClass();
	}
	
}
