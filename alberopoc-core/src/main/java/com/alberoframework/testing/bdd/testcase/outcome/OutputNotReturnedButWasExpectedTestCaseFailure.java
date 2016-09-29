package com.alberoframework.testing.bdd.testcase.outcome;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;

public class OutputNotReturnedButWasExpectedTestCaseFailure<C extends TestContext<S>, S, O extends TestOperation<C, OUT>, OUT> extends TestCaseFailureOutcome<C, S, O> {

	private final OUT actualOutput;
	

	public OutputNotReturnedButWasExpectedTestCaseFailure(C testContext, O testOperation, OUT actualOutput) {
		super(testContext, testOperation);
		this.actualOutput = actualOutput;
	}


	@Override
	public String testCaseFailureDescription() {
		return "Test case did not return any output, but the following output was expected: " + String.valueOf(actualOutput);
	}
	
}
