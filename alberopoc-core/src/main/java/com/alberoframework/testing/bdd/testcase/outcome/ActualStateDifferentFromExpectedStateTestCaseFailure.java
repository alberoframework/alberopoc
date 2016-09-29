package com.alberoframework.testing.bdd.testcase.outcome;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;

public class ActualStateDifferentFromExpectedStateTestCaseFailure<C extends TestContext<S>, S, O extends TestOperation<C, ?>> extends TestCaseFailureOutcome<C, S, O> {

	
	private S expectedState;
	
	public ActualStateDifferentFromExpectedStateTestCaseFailure(C testContext, O testOperation, S expectedState) {
		super(testContext, testOperation);
		this.expectedState = expectedState;
	}


	@Override
	public String testCaseFailureDescription() {
		return "Actual state differs from Expected state: " + String.valueOf(expectedState);
	}
	
}
