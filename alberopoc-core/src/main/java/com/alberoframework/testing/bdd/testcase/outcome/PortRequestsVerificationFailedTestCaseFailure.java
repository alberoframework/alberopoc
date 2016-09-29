package com.alberoframework.testing.bdd.testcase.outcome;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.outcome.TestFailureOutcome;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;

public class PortRequestsVerificationFailedTestCaseFailure<C extends TestContext<S>, S, O extends TestOperation<C, ?>> extends TestCaseFailureOutcome<C, S, O> {

	private String verificationFailureDescrition;
	
	public PortRequestsVerificationFailedTestCaseFailure(C testContext, O testOperation, TestFailureOutcome verificationOutcome) {
		super(testContext, testOperation);
		this.verificationFailureDescrition = verificationOutcome.description();
	}

	@Override
	public String testCaseFailureDescription() {
		return "Port Requests Verification Failed: " + verificationFailureDescrition;
	}
	
}
