package com.alberoframework.testing.bdd.runner;

import java.util.ArrayList;
import java.util.List;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.outcome.TestOutcome;
import com.alberoframework.testing.bdd.outcome.TestSuccessfulOutcome;
import com.alberoframework.testing.bdd.testcase.TestCase;
import com.alberoframework.testing.bdd.testcase.outcome.TestCaseFailureOutcome;

public class TestRunner<C extends TestContext<S>, S> {
		
		private C context;
		
		private List<TestCase<C, S>> testCases;
		
		public TestRunner(C context) {
			this.context = context;
			this.testCases = new ArrayList<TestCase<C, S>>();
		}
		
		public void addTestCase(TestCase<C, S> testCase) {
			testCases.add(testCase);
		}
		
		@SuppressWarnings("unchecked")
		public TestOutcome runTest() {
			if (testCases.isEmpty()) {
				throw new IllegalStateException("The Test must have at least one test case to be executed");
			}
			int i = 1;
			for (TestCase<C, S> testCase : testCases) {
				TestOutcome testCaseOutcome = testCase.executeWith(context);
				if (testCaseOutcome.failure()) {
					return (testCases.size() == 1) ? new TestRunnerSingleTestCaseFailure<C, S>((TestCaseFailureOutcome<C, S, ?>) testCaseOutcome) : new TestRunnerSingleTestCaseFailure<C, S>(i, (TestCaseFailureOutcome<C, S, ?>) testCaseOutcome);
				}
				i++;
			}
			return new TestSuccessfulOutcome();
		}
		
		
}
