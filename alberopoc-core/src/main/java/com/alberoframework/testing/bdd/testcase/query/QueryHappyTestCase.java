package com.alberoframework.testing.bdd.testcase.query;

import java.util.Optional;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.AbstractHappyTestCase;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsVerifier;

public class QueryHappyTestCase<C extends TestContext<S>, S, Q extends QueryTestOperation<C, OUT>, OUT> extends AbstractHappyTestCase<C, S, Q, OUT> {
	
	
//	private QueryHappyTestCase(Q queryTestOperation) {
//		super(queryTestOperation);
//	}
	
//	private QueryHappyTestCase(Q queryTestOperation, PortRequestsVerifier portRequestsVerifier) {
//		super(queryTestOperation, portRequestsVerifier);
//	}
//	
//	private QueryHappyTestCase(Q queryTestOperation, OUT expectedOutput) {
//		super(queryTestOperation, expectedOutput);
//	}
//	
//	private QueryHappyTestCase(Q queryTestOperation, OUT expectedOutput, PortRequestsVerifier portRequestsVerifier) {
//		super(queryTestOperation, expectedOutput, portRequestsVerifier);
//	}
//	
//	//this is used if you want to check the state after the query
//	private QueryHappyTestCase(Q queryTestOperation, OUT expectedOutput, S expectedState) {
//		super(queryTestOperation, expectedOutput, expectedState);
//	}
//	
//	//this is used if you want to check the state AND port requests after the query
//	private QueryHappyTestCase(Q queryTestOperation, OUT expectedOutput, S expectedState, PortRequestsVerifier portRequestsVerifier) {
//		super(queryTestOperation, expectedOutput, expectedState, portRequestsVerifier);
//	}
	
	
//	public static <C extends TestContext<S>, S, Q extends QueryTestOperation<C, OUT>, OUT> QueryHappyTestCase<C, S, Q, OUT> noOutputQueryTestCase(Q queryTestOperation) {
//		return new QueryHappyTestCase<C, S, Q, OUT>(queryTestOperation);
//	}
	
	
	
	private QueryHappyTestCase(Q testOperation, Optional<S> expectedState,
			Optional<PortRequestsVerifier> portRequestsVerifier, Optional<OUT> expectedOutput) {
		super(testOperation, expectedState, portRequestsVerifier, expectedOutput);
	}

	public static <C extends TestContext<S>, S, Q extends QueryTestOperation<C, OUT>, OUT> QueryHappyTestCase<C, S, Q, OUT> noOutputAndVerifyPortRequestsQueryTestCase(Q queryTestOperation, PortRequestsVerifier portRequestsVerifier) {
		return new QueryHappyTestCase<C, S, Q, OUT>(queryTestOperation, Optional.empty(), Optional.of(portRequestsVerifier), Optional.empty());
	}
	
	public static <C extends TestContext<S>, S, Q extends QueryTestOperation<C, OUT>, OUT> QueryHappyTestCase<C, S, Q, OUT> expectedOutputQueryTestCase(Q queryTestOperation, OUT expectedOutput) {
		return new QueryHappyTestCase<C, S, Q, OUT>(queryTestOperation, Optional.empty(), Optional.empty(), Optional.of(expectedOutput));
	}
	
	public static <C extends TestContext<S>, S, Q extends QueryTestOperation<C, OUT>, OUT> QueryHappyTestCase<C, S, Q, OUT> expectedOutputAndVerifyPortRequestsQueryTestCase(Q queryTestOperation, OUT expectedOutput, PortRequestsVerifier portRequestsVerifier) {
		return new QueryHappyTestCase<C, S, Q, OUT>(queryTestOperation, Optional.empty(), Optional.of(portRequestsVerifier), Optional.of(expectedOutput));
	}
	
	public static <C extends TestContext<S>, S, Q extends QueryTestOperation<C, OUT>, OUT> QueryHappyTestCase<C, S, Q, OUT> noOutputAndExpectedStateQueryTestCase(Q queryTestOperation, S expectedState) {
		return new QueryHappyTestCase<C, S, Q, OUT>(queryTestOperation, Optional.of(expectedState), Optional.empty(), Optional.empty());
	}
	
	public static <C extends TestContext<S>, S, Q extends QueryTestOperation<C, OUT>, OUT> QueryHappyTestCase<C, S, Q, OUT> noOutputAndExpectedStateAndVerifyPortRequestsQueryTestCase(Q queryTestOperation, S expectedState, PortRequestsVerifier portRequestsVerifier) {
		return new QueryHappyTestCase<C, S, Q, OUT>(queryTestOperation, Optional.of(expectedState), Optional.of(portRequestsVerifier), Optional.empty());
	}
	
	public static <C extends TestContext<S>, S, Q extends QueryTestOperation<C, OUT>, OUT> QueryHappyTestCase<C, S, Q, OUT> expectedOutputAndExpectedStateQueryTestCase(Q queryTestOperation, OUT expectedOutput, S expectedState) {
		return new QueryHappyTestCase<C, S, Q, OUT>(queryTestOperation, Optional.of(expectedState), Optional.empty(), Optional.of(expectedOutput));
	}
	
	public static <C extends TestContext<S>, S, Q extends QueryTestOperation<C, OUT>, OUT> QueryHappyTestCase<C, S, Q, OUT> expectedOutputAndExpectedStateAndVerifyPortRequestsQueryTestCase(Q queryTestOperation, OUT expectedOutput, S expectedState, PortRequestsVerifier portRequestsVerifier) {
		return new QueryHappyTestCase<C, S, Q, OUT>(queryTestOperation, Optional.of(expectedState), Optional.of(portRequestsVerifier), Optional.of(expectedOutput));
	}
	

	
}
