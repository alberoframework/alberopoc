package com.alberoframework.testing.bdd.testcase.outcome;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;

public class OutputReturnedButWasNotExpectedTestCaseFailure<C extends TestContext<S>, S, O extends TestOperation<C, OUT>, OUT> extends TestCaseFailureOutcome<C, S, O> {

	private OUT expectedOutput;
	
	public OutputReturnedButWasNotExpectedTestCaseFailure(C testContext, O testOperation, OUT expectedOutput) {
		super(testContext, testOperation);
		this.expectedOutput = expectedOutput;
	}

	@Override
	public String testCaseFailureDescription() {
		return "The test case did not expect any output but the following output was returned: " + String.valueOf(expectedOutput);
	}
	
}
