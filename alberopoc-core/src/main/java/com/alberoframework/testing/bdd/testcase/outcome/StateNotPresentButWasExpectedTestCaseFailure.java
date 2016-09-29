package com.alberoframework.testing.bdd.testcase.outcome;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;

public class StateNotPresentButWasExpectedTestCaseFailure<C extends TestContext<S>, S, O extends TestOperation<C, ?>> extends TestCaseFailureOutcome<C, S, O> {

	private final S expectedState;
	
	public StateNotPresentButWasExpectedTestCaseFailure(C testContext, O testOperation, S expectedState) {
		super(testContext, testOperation);
		this.expectedState = expectedState;
	}

	@Override
	public String testCaseFailureDescription() {
		return "The component didnt have a state yet the following state was expected after the operation execution:  " + String.valueOf(expectedState);
	}
	
}
