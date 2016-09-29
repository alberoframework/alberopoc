package com.alberoframework.testing.bdd.testcase;

import java.util.Optional;

import com.alberoframework.core.validation.Validation;
import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.outcome.TestOutcome;
import com.alberoframework.testing.bdd.outcome.TestSuccessfulOutcome;
import com.alberoframework.testing.bdd.testcase.operation.TestOperation;
import com.alberoframework.testing.bdd.testcase.outcome.ActualExceptionDifferentFromExpectedExceptionTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.outcome.ExceptionNotThrownTestCaseFailure;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsVerifier;
import com.google.common.base.Objects;

public class AbstractExceptionTestCase<C extends TestContext<S>, S, O extends TestOperation<C, ?>> extends AbstractTestCase<C, S, O> {

	private Class<? extends Exception> expectedExceptionType;
	
	
	
	public AbstractExceptionTestCase(O testOperation, Optional<S> expectedState,
			Optional<PortRequestsVerifier> portRequestsVerifier, Class<? extends Exception> expectedExceptionType) {
		super(testOperation, expectedState, portRequestsVerifier);
		Validation.validate(expectedExceptionType != null, IllegalArgumentException::new, "Expected Exception type cannot be null");
		this.expectedExceptionType = expectedExceptionType;
	}

//	public AbstractExceptionTestCase(O testOperation, Class<? extends Exception> expectedExceptionType) {
//		super(testOperation);
//		this.expectedExceptionType = expectedExceptionType;
//	}
//	
//	public AbstractExceptionTestCase(O testOperation, Class<? extends Exception> expectedExceptionType, PortRequestsVerifier portRequestsVerifier) {
//		super(testOperation, portRequestsVerifier);
//		this.expectedExceptionType = expectedExceptionType;
//	}
//	
//	public AbstractExceptionTestCase(O testOperation, Class<? extends Exception> expectedExceptionType, S expectedState) {
//		super(testOperation, expectedState);
//		this.expectedExceptionType = expectedExceptionType;
//	}
//	
//	public AbstractExceptionTestCase(O testOperation, Class<? extends Exception> expectedExceptionType, S expectedState, PortRequestsVerifier portRequestsVerifier) {
//		super(testOperation, expectedState, portRequestsVerifier);
//		this.expectedExceptionType = expectedExceptionType;
//	}
	
	
	
	protected Class<? extends Exception> expectedExceptionType() {
		return expectedExceptionType;
	}
	
	public TestOutcome checkTestOperationExecution(C context) {
		 try {
			 testOperation().execute(context);
			 return new ExceptionNotThrownTestCaseFailure<C, S, O>(context, testOperation(), expectedExceptionType);
		 }
		 catch(Exception actualException) {
			 //Assert that actualException and expectedExceptionType are the same
			 if (!Objects.equal(actualException.getClass(), expectedExceptionType)) {
				 return new ActualExceptionDifferentFromExpectedExceptionTestCaseFailure<C, S, O>(context, testOperation(), actualException.getClass(), expectedExceptionType);
			 }
		 }
		 
		 return new TestSuccessfulOutcome();
	}

}
