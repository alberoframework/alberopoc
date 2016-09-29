package com.alberoframework.testing.bdd.testcase.behavior;

import static org.junit.Assert.assertTrue;

import java.util.Optional;

import org.junit.Test;

import com.alberoframework.testing.bdd.outcome.TestOutcome;
import com.alberoframework.testing.bdd.testcase.TestCase;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounter;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounterTestContext;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounterTestContextUsingValidatedQueryToReadState;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounterVerifyNoPortRequestCallsHappened;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounterVerifyOnePortRequestCallHappened;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimplePort;
import com.alberoframework.testing.bdd.testcase.TestCaseTestStubs.SimpleCounter.CounterCannotPassTheMaxLimitException;
import com.alberoframework.testing.bdd.testcase.behavior.BehaviorHappyTestCase;
import com.alberoframework.testing.bdd.testcase.behavior.BehaviorTestCaseTestOperations.SimpleCounterDoNothingBehaviorExecution;
import com.alberoframework.testing.bdd.testcase.behavior.BehaviorTestCaseTestOperations.SimpleCounterIncrementCounterBehaviorExecution;
import com.alberoframework.testing.bdd.testcase.behavior.BehaviorTestCaseTestOperations.SimpleCounterIncrementCounterWithAbsentOutputBehaviorExecution;
import com.alberoframework.testing.bdd.testcase.behavior.BehaviorTestCaseTestOperations.SimpleCounterIncrementCounterWithOutputAndPortCallBehaviorExecution;
import com.alberoframework.testing.bdd.testcase.behavior.BehaviorTestCaseTestOperations.SimpleCounterIncrementCounterWithOutputBehaviorExecution;
import com.alberoframework.testing.bdd.testcase.behavior.BehaviorTestCaseTestOperations.SimpleCounterIncrementCounterWithPortCallBehaviorExecution;
import com.alberoframework.testing.bdd.testcase.outcome.ActualOutputDifferentFromExpectedOutputTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.ActualStateDifferentFromExpectedStateTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.ExceptionNotExpectedTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.ExceptionThrownWhileReadingStateAfterOperationExecutionTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.PortRegistryEmptyButVerifierIsPresentTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.PortRequestsVerificationFailedTestCaseFailure;

public class BehaviorHappyTestCaseTest {

	
	@Test
	public void testSuccessWithOutput() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorHappyTestCase.expectedStateAndExpectedOutputBehaviorTestCase(
				//when
				new SimpleCounterIncrementCounterWithOutputBehaviorExecution(),
				//then (output), the operation returns how many times you can increment the counter before reaching the limit, since we started with a context that had a state of max-1, after the operation you cannot increment the counter anymore
				Optional.of(0),
				//then (state)
				SimpleCounter.MAX_LIMIT);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT - 1));
		assertTrue(result.successful());
	}
	
	@Test
	public void testSuccessWithoutOutput() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorHappyTestCase.expectedStateBehaviorTestCase(
				//when
				new SimpleCounterIncrementCounterBehaviorExecution(),
				//then
				SimpleCounter.MAX_LIMIT);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT - 1));
		assertTrue(result.successful());
	}
	
	@Test
	public void testSuccessWithoutOutputAndVerifyingPortRequests() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorHappyTestCase.expectedStateAndVerifyPortRequestsBehaviorTestCase(
				//when
				new SimpleCounterIncrementCounterWithPortCallBehaviorExecution(),
				//then
				SimpleCounter.MAX_LIMIT,
				//then verify calls
				new SimpleCounterVerifyOnePortRequestCallHappened());
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT - 1, new SimplePort()));
		assertTrue(result.successful());
	}
	
	@Test
	public void testSuccessWithOutputAndVerifyingPortRequests() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorHappyTestCase.expectedStateAndExpectedOutputAndVerifyPortRequestsBehaviorTestCase(
				//when
				new SimpleCounterIncrementCounterWithOutputAndPortCallBehaviorExecution(),
				//then output
				Optional.of(0),
				//then
				SimpleCounter.MAX_LIMIT,
				//then verify calls
				new SimpleCounterVerifyOnePortRequestCallHappened());
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT - 1, new SimplePort()));
		assertTrue(result.successful());
	}
	
	@Test
	public void testFailureBecausePortWasCalledButCallWasNotExpected() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorHappyTestCase.expectedStateAndVerifyPortRequestsBehaviorTestCase(
				//when
				new SimpleCounterIncrementCounterWithPortCallBehaviorExecution(),
				//then
				SimpleCounter.MAX_LIMIT,
				//then verify calls
				new SimpleCounterVerifyNoPortRequestCallsHappened());
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT - 1, new SimplePort()));
		assertTrue(result.failure());
		assertTrue(result instanceof PortRequestsVerificationFailedTestCaseFailure);
	}
	
	@Test
	public void testFailureBecausePortWasNotCalled() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorHappyTestCase.expectedStateAndVerifyPortRequestsBehaviorTestCase(
				//when
				new SimpleCounterIncrementCounterBehaviorExecution(),
				//then
				SimpleCounter.MAX_LIMIT,
				//then verify calls
				new SimpleCounterVerifyOnePortRequestCallHappened());
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT - 1, new SimplePort()));
		assertTrue(result.failure());
		assertTrue(result instanceof PortRequestsVerificationFailedTestCaseFailure);
	}
	
	@Test
	public void testFailureBecauseRegistryIsEmptyButVerifierIsPresent() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorHappyTestCase.expectedStateAndVerifyPortRequestsBehaviorTestCase(
				//when
				new SimpleCounterIncrementCounterBehaviorExecution(),
				//then
				SimpleCounter.MAX_LIMIT,
				//then verify calls
				new SimpleCounterVerifyOnePortRequestCallHappened());
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT - 1));
		assertTrue(result.failure());
		assertTrue(result instanceof PortRegistryEmptyButVerifierIsPresentTestCaseFailure);
	}
	
	@Test
	public void testFailureBecauseOfActualOutputDifferentThanExpectedOutput() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorHappyTestCase.expectedStateAndExpectedOutputBehaviorTestCase(
				//when
				new SimpleCounterIncrementCounterWithOutputBehaviorExecution(),
				//then (output), the operation returns how manu times you can increment the counter before reaching the limit, since we started with a context that had a state of max-1, after the operation you cannot increment the counter anymore
				Optional.of(1),
				//then (state)
				SimpleCounter.MAX_LIMIT);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT - 1));
		assertTrue(result.failure());
		assertTrue(result instanceof ActualOutputDifferentFromExpectedOutputTestCaseFailure);
	}
	
	@Test
	public void testFailureBecauseOutputExpectedYetNotReturned() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorHappyTestCase.expectedStateAndExpectedOutputBehaviorTestCase(
				//when
				new SimpleCounterIncrementCounterWithAbsentOutputBehaviorExecution(),
				//then (output)
				Optional.of(0),
				//then (state)
				SimpleCounter.MAX_LIMIT);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT - 1));
		assertTrue(result.failure());
//		assertTrue(result instanceof OutputNotReturnedButWasExpectedTestCaseFailure);
	}
	
	@Test
	public void testFailureBecauseOutputNotExpectedButWasReturned() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorHappyTestCase.expectedStateAndExpectedOutputBehaviorTestCase(
				//when
				new SimpleCounterIncrementCounterWithOutputBehaviorExecution(),
				//then (output)
				Optional.empty(),
				//then (state)
				SimpleCounter.MAX_LIMIT);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT - 1));
		assertTrue(result.failure());
//		assertTrue(result instanceof OutputReturnedButWasNotExpectedTestCaseFailure);
	}
	
	@Test
	public void testFailureBecauseOfActualStateDifferentThanExpectedStateWithoutOutput() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorHappyTestCase.expectedStateBehaviorTestCase(
				//when
				new SimpleCounterIncrementCounterBehaviorExecution(),
				//then
				SimpleCounter.MAX_LIMIT);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT - 2));
		
		assertTrue(result.failure());
		assertTrue(result instanceof ActualStateDifferentFromExpectedStateTestCaseFailure);
	}
	
	
	@Test
	public void testFailureBecauseOfUnexpectedException() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorHappyTestCase.expectedStateBehaviorTestCase(
				//when
				new SimpleCounterIncrementCounterBehaviorExecution(),
				//then
				SimpleCounter.MAX_LIMIT + 1);
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContext(SimpleCounter.MAX_LIMIT));
		
		assertTrue(result.failure());
		assertTrue(result instanceof ExceptionNotExpectedTestCaseFailure);
		assertTrue(((ExceptionNotExpectedTestCaseFailure) result).exceptionType().equals(CounterCannotPassTheMaxLimitException.class));
		
	}
	
	@Test
	public void testFailureBecauseOfExceptionThrownWhileReadingState() {
		
		TestCase<SimpleCounterTestContext, Integer> testCase = BehaviorHappyTestCase.expectedStateBehaviorTestCase(
					//when
					new SimpleCounterDoNothingBehaviorExecution(),
					//then
					SimpleCounter.MAX_LIMIT + 1
			);
		
		TestOutcome result = testCase.executeWith(new SimpleCounterTestContextUsingValidatedQueryToReadState(SimpleCounter.MAX_LIMIT + 1));
		
		assertTrue(result.failure());
		assertTrue(result instanceof ExceptionThrownWhileReadingStateAfterOperationExecutionTestCaseFailure);
	}
	

}
