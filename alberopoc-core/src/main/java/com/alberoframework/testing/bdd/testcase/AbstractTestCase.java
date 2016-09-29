package com.alberoframework.testing.bdd.testcase;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.collections.IteratorUtils;

import com.alberoframework.core.validation.Validation;
import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.outcome.TestFailureOutcome;
import com.alberoframework.testing.bdd.outcome.TestOutcome;
import com.alberoframework.testing.bdd.outcome.TestSuccessfulOutcome;
import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;
import com.alberoframework.testing.bdd.testcase.outcome.ActualStateDifferentFromExpectedStateTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.ExceptionThrownWhileReadingStateAfterOperationExecutionTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.PortRegistryEmptyButVerifierIsPresentTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.PortRequestsVerificationFailedTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsVerifier;
import com.google.common.base.Objects;

public abstract class AbstractTestCase<C extends TestContext<S>, S, O extends TestOperation<C, ?>> implements TestCase<C, S> {

	private final O testOperation; //the execution of the "operation" inside of the test case
	
	private final Optional<S> expectedState;
	
	private final Optional<PortRequestsVerifier> portRequestsVerifier;
	
	public AbstractTestCase(O testOperation, Optional<S> expectedState, Optional<PortRequestsVerifier> portRequestsVerifier) {
		Validation.validate(testOperation != null, IllegalArgumentException::new, "Test Operation cannot be null");
		Validation.validate(expectedState != null, IllegalArgumentException::new, "Expected State cannot be null, pass Optional empty if you dont want to check current state");
		Validation.validate(portRequestsVerifier != null, IllegalArgumentException::new, "Requests Verifier cannot pass Optional empty if you dont want to verify port requests");
		this.testOperation = testOperation;
		this.expectedState = expectedState;
		this.portRequestsVerifier = portRequestsVerifier;
	}
	
//	public AbstractTestCase(O testOperation) {
//		this(testOperation, Optional.empty(), Optional.empty());
//	}
//
//	public AbstractTestCase(O testOperation, S expectedState) {
//		this(testOperation, Optional.of(expectedState), Optional.empty());
//	}
//	
//	public AbstractTestCase(O testOperation, PortRequestsVerifier portRequestsVerifier) {
//		this(testOperation, Optional.empty(), Optional.of(portRequestsVerifier));
//	}
//	
//	public AbstractTestCase(O testOperation, S expectedState, PortRequestsVerifier portRequestsVerifier) {
//		this(testOperation, Optional.of(expectedState), Optional.of(portRequestsVerifier));
//	}
	

	
	protected O testOperation() {
		return testOperation;
	}
	
	
	protected abstract TestOutcome checkTestOperationExecution(C context);
	
	
	
	public final TestOutcome executeWith(C context) {
		
		TestOutcome operationCheckOutcome = checkTestOperationExecution(context);
		
		if (operationCheckOutcome.failure())
			return operationCheckOutcome;
		
		TestOutcome stateCheckOutcome = checkStateAfterTestOperationExecution(context);
		
		if (stateCheckOutcome.failure())
			return stateCheckOutcome;
		
		return checkPortRequestsAfterTestOperationExecution(context);
	}
	
	
	private TestOutcome checkStateAfterTestOperationExecution(C context) {
		try {
			 S actualState = context.currentState();
			 
//			 Validation.validate(actualState != null, IllegalArgumentException::new, "State is null, dont use null, use VoidUnit to represent stateless contexts");
			 
//			 if (!actualState.isPresent() && expectedState.isPresent()) {
//				 return new StateNotPresentButWasExpectedTestCaseFailure<C,S,O>(context, testOperation(), expectedState.get());
//			 }
			 
			 //If you have a state but didnt expect any state in your test its ok, for behaviors it may not be but their API is the one that doesnt allow to create a behavior test object without an expected state THAT IS NOT ABSENT
			 
//			 if (actualState.isPresent() && !expectedState.isPresent()) {
//				 return new StatePresentButWasNotExpectedTestCaseFailure<OUT>(actualOutput.get());
//			 }
			 
			 if (expectedState.isPresent() && !objectsEqual(actualState, expectedState.get())) {
				 return new ActualStateDifferentFromExpectedStateTestCaseFailure<C,S,O>(context, testOperation(), expectedState.get());
			 }
			 
		 }
		 catch(Exception e) {
			 return new ExceptionThrownWhileReadingStateAfterOperationExecutionTestCaseFailure<C,S,O>(context, testOperation(), e.getClass());
		 }
		
		return new TestSuccessfulOutcome();
	}
	
	private TestOutcome checkPortRequestsAfterTestOperationExecution(C context) {
		
			 PortRegistry portRegistry = context.portRegistry();
			 
			 if (portRegistry == null) {
				 throw new IllegalStateException("Test Context has an empty port registry");
			 }
			 
			 if (portRegistry.isEmpty() && portRequestsVerifier.isPresent()) {
				 return new PortRegistryEmptyButVerifierIsPresentTestCaseFailure<C,S,O>(context, testOperation(), portRequestsVerifier.get());
			 }
			 
			 
			 //If you have ports defined and not a verifier its not a problem as the particular test case may not be interested in verifying port requests
			 
			 
			 
			 //If you have both a port request verifier and a non empty port registry perform the check
			 
			 if (!portRegistry.isEmpty() && portRequestsVerifier.isPresent()) {
				 TestOutcome outcome =  portRequestsVerifier.get().verifyPortRequests(portRegistry);
				 if (outcome.failure()) {
					 return new PortRequestsVerificationFailedTestCaseFailure<C,S,O>(context, testOperation(), (TestFailureOutcome) outcome);
				 }
			 }
			 
		return new TestSuccessfulOutcome();
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected boolean objectsEqual(Object o1, Object o2) {
		if (o1 instanceof Iterable && o2 instanceof Iterable) {
			List a1 = IteratorUtils.toList(((Iterable) o1).iterator());
			List a2 = IteratorUtils.toList(((Iterable) o2).iterator());
			
			if (o1 instanceof Set && o2 instanceof Set) {
				return a1.containsAll(a2) && a2.containsAll(a1);
			}
			
			return Objects.equal(a1, a2);
		}
		
		return Objects.equal(o1, o2);
	}
	
}
