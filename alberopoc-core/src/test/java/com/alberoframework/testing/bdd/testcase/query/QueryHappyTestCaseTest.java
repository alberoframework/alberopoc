package com.alberoframework.testing.bdd.testcase.query;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.alberoframework.lang.VoidUnit;
import com.alberoframework.testing.bdd.outcome.TestOutcome;
import com.alberoframework.testing.bdd.testcase.TestCase;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounter;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounterBuggedStatefulContext;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounterStatelessTestContext;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounterTestContext;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounterTestContextUsingValidatedQueryToReadState;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounter.CounterInIllegalStateException;
import com.alberoframework.testing.bdd.testcase.outcome.ActualOutputDifferentFromExpectedOutputTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.ActualStateDifferentFromExpectedStateTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.ExceptionNotExpectedTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.ExceptionThrownWhileReadingStateAfterOperationExecutionTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.query.QueryHappyTestCase;
import com.alberoframework.testing.bdd.testcase.query.QueryTestCaseTestOperations.SimpleCounterAbsentOutputQueryExecution;
import com.alberoframework.testing.bdd.testcase.query.QueryTestCaseTestOperations.SimpleCounterGetCounterValueOfStatelessContextQueryExecution;
import com.alberoframework.testing.bdd.testcase.query.QueryTestCaseTestOperations.SimpleCounterGetCounterValueQueryExecution;

public class QueryHappyTestCaseTest {

	@Test
	public void testSuccess() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = QueryHappyTestCase.expectedOutputQueryTestCase(
				//when
				new SimpleCounterGetCounterValueQueryExecution(),
				//then
				Optional.of(SimpleCounter.MAX_LIMIT));
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT));
		assertTrue(result.successful());
	}
	
	@Test
	public void testSuccessWithStatelessContext() {
		
		TestCase<SimpleCounterStatelessTestContext, VoidUnit> testCase = QueryHappyTestCase.expectedOutputQueryTestCase(
				//when
				new SimpleCounterGetCounterValueOfStatelessContextQueryExecution(),
				//then
				Optional.of(SimpleCounter.MAX_LIMIT));
		
		TestOutcome result = testCase.executeWith(new SimpleCounterStatelessTestContext(SimpleCounter.MAX_LIMIT));
		assertTrue(result.successful());
	}
	
	@Test
	public void testSuccessWithNoOutput() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = QueryHappyTestCase.expectedOutputQueryTestCase(
				//when
				new SimpleCounterAbsentOutputQueryExecution(),
				//then
				Optional.empty());
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT));
		assertTrue(result.successful());
	}
	
	@Test
	public void testSuccessCheckingStateAsWellAsOutput() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = QueryHappyTestCase.expectedOutputAndExpectedStateQueryTestCase(
				//when
				new SimpleCounterGetCounterValueQueryExecution(),
				//then (output)
				Optional.of(SimpleCounter.MAX_LIMIT),
				//then 
				SimpleCounter.MAX_LIMIT);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT));
		assertTrue(result.successful());
	}
	
	@Test
	public void testSuccessCheckingStateAndNoOutput() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = QueryHappyTestCase.noOutputAndExpectedStateQueryTestCase(
				//when
				new SimpleCounterAbsentOutputQueryExecution(),
				//then (state)
				SimpleCounter.MAX_LIMIT);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT));
		assertTrue(result.successful());
	}
	
	@Test
	public void testFailureBecauseOfActualOutputIsNotPresentButExpected() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = QueryHappyTestCase.expectedOutputQueryTestCase(
				//when
				new SimpleCounterAbsentOutputQueryExecution(),
				//then
				Optional.of(SimpleCounter.MAX_LIMIT));
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT));
		assertTrue(result.failure());
//		assertTrue(result instanceof OutputNotReturnedButWasExpectedTestCaseFailure);
	}
	
	@Test
	public void testFailureBecauseOfActualOutputIsPresentButExpected() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = QueryHappyTestCase.expectedOutputQueryTestCase(
				//when
				new SimpleCounterGetCounterValueQueryExecution(),
				//then
				Optional.empty());
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT));
		assertTrue(result.failure());
//		assertTrue(result instanceof OutputReturnedButWasNotExpectedTestCaseFailure);
	}
	
	@Test
	public void testFailureBecauseOfActualOutputDifferentThanExpectedOutput() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = QueryHappyTestCase.expectedOutputQueryTestCase(
				//when
				new SimpleCounterGetCounterValueQueryExecution(),
				//then
				Optional.of(SimpleCounter.MAX_LIMIT - 1));
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT));
		assertTrue(result.failure());
		assertTrue(result instanceof ActualOutputDifferentFromExpectedOutputTestCaseFailure);
	}
	
	@Test
	public void testFailureBecauseOfActualStateDifferentThanExpectedState() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = QueryHappyTestCase.expectedOutputAndExpectedStateQueryTestCase(
				//when
				new SimpleCounterGetCounterValueQueryExecution(),
				//then (output)
				Optional.of(SimpleCounter.MAX_LIMIT),
				//then 
				SimpleCounter.MAX_LIMIT -1);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT));
		assertTrue(result.failure());
		assertTrue(result instanceof ActualStateDifferentFromExpectedStateTestCaseFailure);
	}
	
	@Test
	public void testFailureBecauseOfActualStateDifferentThanExpectedStateWithNoOutput() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = QueryHappyTestCase.noOutputAndExpectedStateQueryTestCase(
				//when
				new SimpleCounterAbsentOutputQueryExecution(),
				//then (state)
				SimpleCounter.MAX_LIMIT -1);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT));
		assertTrue(result.failure());
		assertTrue(result instanceof ActualStateDifferentFromExpectedStateTestCaseFailure);
	}
	
	
	//Null is not acceptable as state
	@Test
	public void testFailureBecauseStateExpectedYetContextReturnsNull() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = QueryHappyTestCase.expectedOutputAndExpectedStateQueryTestCase(
				//when
				new SimpleCounterGetCounterValueQueryExecution(),
				//then
				Optional.of(SimpleCounter.MAX_LIMIT),
				//then state
				SimpleCounter.MAX_LIMIT);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterBuggedStatefulContext(SimpleCounter.MAX_LIMIT));
		assertTrue(result.failure());
		assertTrue(result instanceof ActualStateDifferentFromExpectedStateTestCaseFailure);
	}
	
	@Test
	public void testFailureBecauseOfUnexpectedException() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = QueryHappyTestCase.expectedOutputQueryTestCase(
				//when
				new SimpleCounterGetCounterValueQueryExecution(),
				//then
				Optional.of(SimpleCounter.MAX_LIMIT + 1));
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT + 1));
		
		assertTrue(result.failure());
		assertTrue(result instanceof ExceptionNotExpectedTestCaseFailure);
		assertTrue(((ExceptionNotExpectedTestCaseFailure) result).exceptionType().equals(CounterInIllegalStateException.class));
		
	}
	
	@Test
	public void testFailureBecauseOfExceptionThrownWhileReadingState() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = QueryHappyTestCase.expectedOutputQueryTestCase(
				//when
				new SimpleCounterAbsentOutputQueryExecution(),
				Optional.empty());
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContextUsingValidatedQueryToReadState(SimpleCounter.MAX_LIMIT + 1));
		
		assertTrue(result.failure());
		assertTrue(result instanceof ExceptionThrownWhileReadingStateAfterOperationExecutionTestCaseFailure);
	}
	

}
