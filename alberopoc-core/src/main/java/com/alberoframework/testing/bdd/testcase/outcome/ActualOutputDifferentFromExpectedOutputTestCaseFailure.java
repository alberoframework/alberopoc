package com.alberoframework.testing.bdd.testcase.outcome;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;

public class ActualOutputDifferentFromExpectedOutputTestCaseFailure<C extends TestContext<S>, S, O extends TestOperation<C, OUT>, OUT> extends TestCaseFailureOutcome<C, S, O> {

	private OUT actualOutput;
	
	private OUT expectedOutput;
	
	public ActualOutputDifferentFromExpectedOutputTestCaseFailure(C testContext, O testOperation, OUT actualOutput, OUT expectedOutput) {
		super(testContext, testOperation);
		this.actualOutput = actualOutput;
		this.expectedOutput = expectedOutput;
	}

	@Override
	public String testCaseFailureDescription() {
		return "Actual output: " + String.valueOf(actualOutput) + " differs from expected output: " + String.valueOf(expectedOutput);
	}
	
}
