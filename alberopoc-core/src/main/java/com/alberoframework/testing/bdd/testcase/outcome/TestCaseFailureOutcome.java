package com.alberoframework.testing.bdd.testcase.outcome;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.outcome.TestFailureOutcome;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;

public abstract class TestCaseFailureOutcome<C extends TestContext<S>, S, O extends TestOperation<C, ?>> extends TestFailureOutcome {

		private String description;

		public TestCaseFailureOutcome(C testContext, O testOperation) {
			
			try {
				this.description = "Actual State: " + testContext.currentState() + System.lineSeparator();
			}
			catch(Exception e) {
				this.description = "Context State: An exception was thrown while attempting to read the state " + e; 
			}
			
			this.description += "Test Operation: " + testOperation;
		}

		@Override
		public String description() {
			return description + System.lineSeparator() + "Test Outcome: " + testCaseFailureDescription();
		}
		
		public abstract String testCaseFailureDescription();
		
}
