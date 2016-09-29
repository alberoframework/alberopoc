package com.alberoframework.testing.bdd.testcase.behavior;

import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounterTestContext;
import com.alberoframework.testing.bdd.testcase.behavior.AbstractBehaviorTestOperationWithNullableOutput;
import com.alberoframework.testing.bdd.testcase.behavior.AbstractBehaviorTestOperationWithoutOutput;

public class BehaviorTestCaseTestOperations {
	
	
	public static class SimpleCounterIncrementCounterBehaviorExecution extends AbstractBehaviorTestOperationWithoutOutput<SimpleCounterTestContext> {
		@Override
		public void doExecute(SimpleCounterTestContext context) {
			context.simpleCounter().incrementCounter();
		}
		
	}
	
	public static class SimpleCounterIncrementCounterWithPortCallBehaviorExecution extends AbstractBehaviorTestOperationWithoutOutput<SimpleCounterTestContext> {
		@Override
		public void doExecute(SimpleCounterTestContext context) {
			context.simpleCounter().incrementCounterWithPortCall();
		}
	}
	
	public static class SimpleCounterIncrementCounterWithOutputBehaviorExecution extends AbstractBehaviorTestOperationWithNullableOutput<SimpleCounterTestContext, Integer> {

		@Override
		public Integer doExecute(SimpleCounterTestContext context) {
			return context.simpleCounter().incrementCounterWithOutput();
		}
		
	}
	
	public static class SimpleCounterIncrementCounterWithOutputAndPortCallBehaviorExecution extends AbstractBehaviorTestOperationWithNullableOutput<SimpleCounterTestContext, Integer> {

		@Override
		public Integer doExecute(SimpleCounterTestContext context) {
			return context.simpleCounter().incrementCounterWithOutputAndPortCall();
		}
		
	}
	
	public static class SimpleCounterIncrementCounterWithAbsentOutputBehaviorExecution extends AbstractBehaviorTestOperationWithNullableOutput<SimpleCounterTestContext, Integer> {

		@Override
		public Integer doExecute(SimpleCounterTestContext context) {
			context.simpleCounter().incrementCounter();
			return null;
		}
		
	}
	
	public static class SimpleCounterBuggedIncrementCounterBehaviorExecution extends AbstractBehaviorTestOperationWithoutOutput<SimpleCounterTestContext> {
		@Override
		public void doExecute(SimpleCounterTestContext context) {
			context.simpleCounter().buggedIncrementCounter();
		}
	}
	
	public static class SimpleCounterDoNothingBehaviorExecution extends AbstractBehaviorTestOperationWithoutOutput<SimpleCounterTestContext> {
		@Override
		public void doExecute(SimpleCounterTestContext context) {
			
		}
	}
		 
}
