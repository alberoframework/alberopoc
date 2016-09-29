package com.alberoframework.testing.bdd.testcase.outcome;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;

public class ActualExceptionDifferentFromExpectedExceptionTestCaseFailure<C extends TestContext<S>, S, O extends TestOperation<C, ?>> extends TestCaseFailureOutcome<C, S, O> {

	private Class<? extends Exception> actualExceptionType;
	
	private Class<? extends Exception> expectedExceptionType;

	public ActualExceptionDifferentFromExpectedExceptionTestCaseFailure(C testContext, O testOperation, Class<? extends Exception> actualExceptionType,
		Class<? extends Exception> expectedExceptionType) {
		super(testContext, testOperation);
		this.actualExceptionType = actualExceptionType;
		this.expectedExceptionType = expectedExceptionType;
	}

	@Override
	public String testCaseFailureDescription() {
		return "Actual Exception: " + actualExceptionType + " differs from expected exception: " + expectedExceptionType;
	}
	
}
