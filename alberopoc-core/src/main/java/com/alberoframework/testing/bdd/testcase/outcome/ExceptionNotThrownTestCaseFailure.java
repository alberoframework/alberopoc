package com.alberoframework.testing.bdd.testcase.outcome;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;

public class ExceptionNotThrownTestCaseFailure<C extends TestContext<S>, S, O extends TestOperation<C, ?>> extends TestCaseFailureOutcome<C, S, O> {

	private Class<? extends Exception> expectedExceptionType;

	public ExceptionNotThrownTestCaseFailure(C testContext, O testOperation, Class<? extends Exception> expectedExceptionType) {
		super(testContext, testOperation);
		this.expectedExceptionType = expectedExceptionType;
	}

	@Override
	public String testCaseFailureDescription() {
		return "Expected exception " + expectedExceptionType + " was not thrown";
	}
	
}
