package com.alberoframework.testing.bdd.testcase.behavior;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.alberoframework.testing.bdd.outcome.TestOutcome;
import com.alberoframework.testing.bdd.testcase.TestCase;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounter;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounterTestContext;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounterTestContextUsingValidatedQueryToReadState;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounter.CounterCannotPassTheMaxLimitException;
import com.alberoframework.testing.bdd.testcase.behavior.BehaviorExceptionTestCase;
import com.alberoframework.testing.bdd.testcase.behavior.BehaviorTestCaseTestOperations.SimpleCounterBuggedIncrementCounterBehaviorExecution;
import com.alberoframework.testing.bdd.testcase.behavior.BehaviorTestCaseTestOperations.SimpleCounterIncrementCounterBehaviorExecution;
import com.alberoframework.testing.bdd.testcase.outcome.ActualExceptionDifferentFromExpectedExceptionTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.ActualStateDifferentFromExpectedStateTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.ExceptionNotThrownTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.ExceptionThrownWhileReadingStateAfterOperationExecutionTestCaseFailure;

public class BehaviorExceptionTestCaseTest {

	@Test
	public void testSuccess() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorExceptionTestCase.expectedExceptionBehaviorTestCase(
				//when
				new SimpleCounterIncrementCounterBehaviorExecution(),
				//thenException
				CounterCannotPassTheMaxLimitException.class
		);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT));
		assertTrue(result.successful());
	}
	
	
	@Test
	public void testFailureBecauseExceptionWasNotThrown() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorExceptionTestCase.expectedExceptionBehaviorTestCase(
					//when
					new SimpleCounterIncrementCounterBehaviorExecution(),
					//thenException
					CounterCannotPassTheMaxLimitException.class
			);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT - 1));
		
		assertTrue(result.failure());
		assertTrue(result instanceof ExceptionNotThrownTestCaseFailure);
	}
	
	
	@Test
	public void testFailureBecauseDifferentExceptionWasThrown() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorExceptionTestCase.expectedExceptionBehaviorTestCase(
					//when
					new SimpleCounterIncrementCounterBehaviorExecution(),
					//thenException
					IllegalStateException.class
			);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT));
		
		assertTrue(result.failure());
		assertTrue(result instanceof ActualExceptionDifferentFromExpectedExceptionTestCaseFailure);
	}
	
	@Test
	public void testFailureBecauseStateChangedAfterException() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorExceptionTestCase.expectedExceptionAndExpectedStateBehaviorTestCase(
					//when
					new SimpleCounterBuggedIncrementCounterBehaviorExecution(),
					//thenException
					CounterCannotPassTheMaxLimitException.class,
					//expect the same state
					SimpleCounter.MAX_LIMIT
			);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT));
		
		assertTrue(result.failure());
		assertTrue(result instanceof ActualStateDifferentFromExpectedStateTestCaseFailure);
	}
	
	@Test
	public void testFailureOfExceptionThrownWhileReadingState() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorExceptionTestCase.expectedExceptionAndExpectedStateBehaviorTestCase(
					//when
					new SimpleCounterBuggedIncrementCounterBehaviorExecution(),
					//thenException
					CounterCannotPassTheMaxLimitException.class,
					//expect the same state
					SimpleCounter.MAX_LIMIT
			);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContextUsingValidatedQueryToReadState(SimpleCounter.MAX_LIMIT));
		
		assertTrue(result.failure());
		assertTrue(result instanceof ExceptionThrownWhileReadingStateAfterOperationExecutionTestCaseFailure);
	}
	

}
