package com.alberoframework.testing.bdd.testcase.outcome;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;

public class ExceptionNotExpectedTestCaseFailure<C extends TestContext<S>, S, O extends TestOperation<C, ?>> extends TestCaseFailureOutcome<C, S, O> {

	private Exception exception;
	
	public ExceptionNotExpectedTestCaseFailure(C testContext, O testOperation, Exception exception) {
		super(testContext, testOperation);
		this.exception = exception;
	}
	
	public Class<? extends Exception> exceptionType() {
		return exception.getClass();
	}

	@Override
	public String testCaseFailureDescription() {
		exception.printStackTrace();
		return "Thrown exception " + exceptionType() + " was not expected. ";
		
	}
	
}
