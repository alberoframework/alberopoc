package com.alberoframework.testing.bdd.testcase.query;

import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounterStatelessTestContext;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounterTestContext;
import com.alberoframework.testing.bdd.testcase.query.AbstractQueryTestOperationWithNullableOutput;

public class QueryTestCaseTestOperations {
	
	public static class SimpleCounterGetCounterValueQueryExecution extends AbstractQueryTestOperationWithNullableOutput<SimpleCounterTestContext, Integer> {
		
		@Override
		public Integer doExecute(SimpleCounterTestContext context) {
			return context.simpleCounter().counter();
		}
		
	}
	
	public static class SimpleCounterGetCounterValueOfStatelessContextQueryExecution extends AbstractQueryTestOperationWithNullableOutput<SimpleCounterStatelessTestContext, Integer> {
		
		@Override
		public Integer doExecute(SimpleCounterStatelessTestContext context) {
			return context.simpleCounter().counter();
		}
		
	}
	
	public static class SimpleCounterAbsentOutputQueryExecution extends AbstractQueryTestOperationWithNullableOutput<SimpleCounterTestContext, Integer> {

		@Override
		protected Integer doExecute(SimpleCounterTestContext context) {
			return null;
		}
		
	}
	
}
