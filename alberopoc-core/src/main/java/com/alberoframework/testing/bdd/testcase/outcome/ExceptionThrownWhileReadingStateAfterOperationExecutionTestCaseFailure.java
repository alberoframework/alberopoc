package com.alberoframework.testing.bdd.testcase.outcome;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;

public class ExceptionThrownWhileReadingStateAfterOperationExecutionTestCaseFailure<C extends TestContext<S>, S, O extends TestOperation<C, ?>> extends TestCaseFailureOutcome<C, S, O> {

	private Class<? extends Exception> exceptionType;
	
	public ExceptionThrownWhileReadingStateAfterOperationExecutionTestCaseFailure(C testContext, O testOperation, Class<? extends Exception> exceptionType) {
		super(testContext, testOperation);
		this.exceptionType = exceptionType;
	}



	@Override
	public String testCaseFailureDescription() {
		return "Exception " + exceptionType + " was thrown while reading state after the operation execution";
	}
	
}
