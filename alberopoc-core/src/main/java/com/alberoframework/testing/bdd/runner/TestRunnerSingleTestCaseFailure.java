package com.alberoframework.testing.bdd.runner;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.outcome.TestFailureOutcome;
import com.alberoframework.testing.bdd.testcase.outcome.TestCaseFailureOutcome;

public class TestRunnerSingleTestCaseFailure<C extends TestContext<S>, S> extends TestFailureOutcome {

	private Integer testCaseNumber;
	
	private TestCaseFailureOutcome<C, S, ?> testCaseFailure;
	
	
	public TestRunnerSingleTestCaseFailure(Integer testCaseNumber, TestCaseFailureOutcome<C, S, ?> testCaseFailure) {
		this.testCaseNumber = testCaseNumber;
		this.testCaseFailure = testCaseFailure;
	}
	
	public TestRunnerSingleTestCaseFailure(TestCaseFailureOutcome<C, S, ?> testCaseFailure) {
		this.testCaseFailure = testCaseFailure;
	}


	@Override
	public String description() {
		return "Test Case" + (testCaseNumber != null ? " " + testCaseNumber : "" ) + " failed: " + System.lineSeparator() + testCaseFailure.description();
	}
	
	
}
