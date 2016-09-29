package com.alberoframework.testing.bdd.testcase;

import java.util.Optional;

import com.alberoframework.core.validation.Validation;
import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.outcome.TestOutcome;
import com.alberoframework.testing.bdd.outcome.TestSuccessfulOutcome;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;
import com.alberoframework.testing.bdd.testcase.outcome.ActualOutputDifferentFromExpectedOutputTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.ExceptionNotExpectedTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsVerifier;

public abstract class AbstractHappyTestCase<C extends TestContext<S>, S, O extends TestOperation<C, OUT>, OUT> extends AbstractTestCase<C, S, O> {

	private final Optional<OUT> expectedOutput;
	
	
	public AbstractHappyTestCase(O testOperation, Optional<S> expectedState,
			Optional<PortRequestsVerifier> portRequestsVerifier, Optional<OUT> expectedOutput) {
		super(testOperation, expectedState, portRequestsVerifier);
		Validation.validate(expectedOutput != null, IllegalArgumentException::new, "Expected Output is null, pass Optional empty if you dont want to check the output");
		this.expectedOutput = expectedOutput;
	}
	
	
	public TestOutcome checkTestOperationExecution(C context) {
		
		try {
			 OUT actualOutput = testOperation().execute(context);
			 
//			 Validation.validate(actualOutput != null, IllegalArgumentException::new, "Actual Output is null, dont use null, use optional if to represent empty outputs");
			 
//			 if (!actualOutput.isPresent() && expectedOutput.isPresent()) {
//				 return new OutputNotReturnedButWasExpectedTestCaseFailure<C, S, O, OUT>(context, testOperation(), expectedOutput.get());
//			 }
//			 
//			 if (actualOutput.isPresent() && !expectedOutput.isPresent()) {
//				 return new OutputReturnedButWasNotExpectedTestCaseFailure<C, S, O, OUT>(context, testOperation(), actualOutput.get());
//			 }
			 
			 //Assert that actualOutput and expectedOutput are the same
			 
			 if (expectedOutput.isPresent() && !objectsEqual(actualOutput, expectedOutput.get())) {
				 return new ActualOutputDifferentFromExpectedOutputTestCaseFailure<C, S, O, OUT>(context, testOperation(), actualOutput, expectedOutput.get());
			 }
			 
		}
		catch(Exception e) {
			return new ExceptionNotExpectedTestCaseFailure<C, S, O>(context, testOperation(), e);
		}
		 
		return new TestSuccessfulOutcome();
			
	}
	
}
