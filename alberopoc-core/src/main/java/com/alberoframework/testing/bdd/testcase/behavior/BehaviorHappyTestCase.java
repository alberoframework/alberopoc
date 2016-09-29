package com.alberoframework.testing.bdd.testcase.behavior;

import java.util.Optional;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.AbstractHappyTestCase;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsVerifier;

public class BehaviorHappyTestCase<C extends TestContext<S>, S, B extends BehaviorTestOperation<C, OUT>, OUT> extends AbstractHappyTestCase<C, S, B, OUT> {

//	private BehaviorHappyTestCase(B behaviorTestOperation, S expectedState) {
//		super(behaviorTestOperation, null, expectedState); //AbstractHappyTestCase cannot have a constructor with just the operation and the expected state (there is already one with the operation and the expected output)
//	}
//	
//	private BehaviorHappyTestCase(B behaviorTestOperation, S expectedState, PortRequestsVerifier portRequestsVerifier) {
//		super(behaviorTestOperation, null, expectedState, portRequestsVerifier); //AbstractHappyTestCase cannot have a constructor with just the operation and the expected state (there is already one with the operation and the expected output)
//	}
//	
//	private BehaviorHappyTestCase(B behaviorTestOperation, OUT expectedOutput, S expectedState) {
//		super(behaviorTestOperation, expectedOutput, expectedState);
//	}
//	
//	private BehaviorHappyTestCase(B behaviorTestOperation, OUT expectedOutput, S expectedState, PortRequestsVerifier portRequestsVerifier) {
//		super(behaviorTestOperation, expectedOutput, expectedState, portRequestsVerifier);
//	}
	
	private BehaviorHappyTestCase(B testOperation, Optional<S> expectedState,
			Optional<PortRequestsVerifier> portRequestsVerifier, Optional<OUT> expectedOutput) {
		super(testOperation, expectedState, portRequestsVerifier, expectedOutput);
	}
	
	public static <C extends TestContext<S>, S, B extends BehaviorTestOperation<C, OUT>, OUT> BehaviorHappyTestCase<C, S, B, OUT> expectedStateBehaviorTestCase(B behaviorTestOperation, S expectedState) {
		return new BehaviorHappyTestCase<C, S, B, OUT>(behaviorTestOperation, Optional.of(expectedState), Optional.empty(), Optional.empty());
	}

	public static <C extends TestContext<S>, S, B extends BehaviorTestOperation<C, OUT>, OUT> BehaviorHappyTestCase<C, S, B, OUT> expectedStateAndVerifyPortRequestsBehaviorTestCase(B behaviorTestOperation, S expectedState, PortRequestsVerifier portRequestsVerifier) {
		return new BehaviorHappyTestCase<C, S, B, OUT>(behaviorTestOperation, Optional.of(expectedState), Optional.of(portRequestsVerifier), Optional.empty());
	}
	
	public static <C extends TestContext<S>, S, B extends BehaviorTestOperation<C, OUT>, OUT> BehaviorHappyTestCase<C, S, B, OUT> expectedStateAndExpectedOutputBehaviorTestCase(B behaviorTestOperation, OUT expectedOutput, S expectedState) {
		return new BehaviorHappyTestCase<C, S, B, OUT>(behaviorTestOperation, Optional.of(expectedState), Optional.empty(), Optional.of(expectedOutput));
	} 
	
	public static <C extends TestContext<S>, S, B extends BehaviorTestOperation<C, OUT>, OUT> BehaviorHappyTestCase<C, S, B, OUT> expectedStateAndExpectedOutputAndVerifyPortRequestsBehaviorTestCase(B behaviorTestOperation, OUT expectedOutput, S expectedState, PortRequestsVerifier portRequestsVerifier) {
		return new BehaviorHappyTestCase<C, S, B, OUT>(behaviorTestOperation, Optional.of(expectedState), Optional.of(portRequestsVerifier), Optional.of(expectedOutput));
	} 
	
}
