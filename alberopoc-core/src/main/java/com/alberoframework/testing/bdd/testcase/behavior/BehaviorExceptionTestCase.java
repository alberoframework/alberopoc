package com.alberoframework.testing.bdd.testcase.behavior;

import java.util.Optional;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.AbstractExceptionTestCase;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsVerifier;

public class BehaviorExceptionTestCase<C extends TestContext<S>, S, B extends BehaviorTestOperation<C, ?>> extends AbstractExceptionTestCase<C, S, B> {

//	private BehaviorExceptionTestCase(B behaviorTestOperation, Class<? extends Exception> expectedExceptionType) {
//		super(behaviorTestOperation, expectedExceptionType);
//	}
//	
//	private BehaviorExceptionTestCase(B behaviorTestOperation, Class<? extends Exception> expectedExceptionType, PortRequestsVerifier portRequestsVerifier) {
//		super(behaviorTestOperation, expectedExceptionType, portRequestsVerifier);
//	}
//	
//	private BehaviorExceptionTestCase(B behaviorTestOperation, Class<? extends Exception> expectedExceptionType, S expectedState) {
//		super(behaviorTestOperation, expectedExceptionType, expectedState);
//	}
//	
//	private BehaviorExceptionTestCase(B behaviorTestOperation, Class<? extends Exception> expectedExceptionType, S expectedState, PortRequestsVerifier portRequestsVerifier) {
//		super(behaviorTestOperation, expectedExceptionType, expectedState, portRequestsVerifier);
//	}
	
	
	public BehaviorExceptionTestCase(B testOperation, Optional<S> expectedState,
			Optional<PortRequestsVerifier> portRequestsVerifier, Class<? extends Exception> expectedExceptionType) {
		super(testOperation, expectedState, portRequestsVerifier, expectedExceptionType);
	}
	
	public static <C extends TestContext<S>, S, B extends BehaviorTestOperation<C, ?>> BehaviorExceptionTestCase<C, S, B> expectedExceptionBehaviorTestCase(B behaviorTestOperation, Class<? extends Exception> expectedExceptionType) {
		return new BehaviorExceptionTestCase<C, S, B>(behaviorTestOperation, Optional.empty(), Optional.empty(), expectedExceptionType);
	}

	public static <C extends TestContext<S>, S, B extends BehaviorTestOperation<C, ?>> BehaviorExceptionTestCase<C, S, B> expectedExceptionAndVerifyPortRequestsBehaviorTestCase(B behaviorTestOperation, Class<? extends Exception> expectedExceptionType, PortRequestsVerifier portRequestsVerifier) {
		return new BehaviorExceptionTestCase<C, S, B>(behaviorTestOperation, Optional.empty(), Optional.of(portRequestsVerifier), expectedExceptionType);
	}
	
	public static <C extends TestContext<S>, S, B extends BehaviorTestOperation<C, ?>> BehaviorExceptionTestCase<C, S, B> expectedExceptionAndExpectedStateBehaviorTestCase(B behaviorTestOperation, Class<? extends Exception> expectedExceptionType, S expectedState) {
		return new BehaviorExceptionTestCase<C, S, B>(behaviorTestOperation, Optional.of(expectedState), Optional.empty(), expectedExceptionType);
	}
	
	public static <C extends TestContext<S>, S, B extends BehaviorTestOperation<C, ?>> BehaviorExceptionTestCase<C, S, B> expectedExceptionAndExpectedStateAndVerifyPortRequestsBehaviorTestCase(B behaviorTestOperation, Class<? extends Exception> expectedExceptionType, S expectedState, PortRequestsVerifier portRequestsVerifier) {
		return new BehaviorExceptionTestCase<C, S, B>(behaviorTestOperation, Optional.of(expectedState), Optional.of(portRequestsVerifier), expectedExceptionType);
	}
	
}
