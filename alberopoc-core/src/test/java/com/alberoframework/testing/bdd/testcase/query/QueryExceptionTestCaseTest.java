package com.alberoframework.testing.bdd.testcase.query;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.alberoframework.testing.bdd.outcome.TestOutcome;
import com.alberoframework.testing.bdd.testcase.TestCase;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounter;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounterTestContext;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounterTestContextUsingValidatedQueryToReadState;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounter.CounterInIllegalStateException;
import com.alberoframework.testing.bdd.testcase.outcome.ActualExceptionDifferentFromExpectedExceptionTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.ExceptionNotThrownTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.ExceptionThrownWhileReadingStateAfterOperationExecutionTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.query.QueryExceptionTestCase;
import com.alberoframework.testing.bdd.testcase.query.QueryTestCaseTestOperations.SimpleCounterGetCounterValueQueryExecution;

public class QueryExceptionTestCaseTest {

	@Test
	public void testSuccess() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = QueryExceptionTestCase.expectedExceptionQueryTestCase(
				//when
				new SimpleCounterGetCounterValueQueryExecution(),
				//then
				CounterInIllegalStateException.class);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT + 1));
		assertTrue(result.successful());
	}
	
	
	@Test
	public void testFailureBecauseExceptionWasNotThrown() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = QueryExceptionTestCase.expectedExceptionQueryTestCase(
				//when
				new SimpleCounterGetCounterValueQueryExecution(),
				//then
				CounterInIllegalStateException.class);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT));
		
		assertTrue(result.failure());
		assertTrue(result instanceof ExceptionNotThrownTestCaseFailure);
	}
	
	
	@Test
	public void testFailureBecauseDifferentExceptionWasThrown() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = QueryExceptionTestCase.expectedExceptionQueryTestCase(
				//when
				new SimpleCounterGetCounterValueQueryExecution(),
				//then
				IllegalStateException.class); //CounterInIllegalStateException (the actual exception thrown) is actually a subclass of IllegalStateException, but the test still should fail, the expected exception should be of the exact same type as the exception that was thrown
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT + 1));
		
		assertTrue(result.failure());
		assertTrue(result instanceof ActualExceptionDifferentFromExpectedExceptionTestCaseFailure);
	}
	
	@Test
	public void testFailureBecauseOfExceptionThrownWhileReadingState() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = QueryExceptionTestCase.expectedExceptionQueryTestCase(
				//when
				new SimpleCounterGetCounterValueQueryExecution(),
				//then
				CounterInIllegalStateException.class);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContextUsingValidatedQueryToReadState(SimpleCounter.MAX_LIMIT + 1));
		
		assertTrue(result.failure());
		assertTrue(result instanceof ExceptionThrownWhileReadingStateAfterOperationExecutionTestCaseFailure);
	}
	

}
