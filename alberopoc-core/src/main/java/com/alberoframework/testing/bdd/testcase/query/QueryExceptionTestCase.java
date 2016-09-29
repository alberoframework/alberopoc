package com.alberoframework.testing.bdd.testcase.query;

import java.util.Optional;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.AbstractExceptionTestCase;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsVerifier;

public class QueryExceptionTestCase<C extends TestContext<S>, S, Q extends QueryTestOperation<C, ?>> extends AbstractExceptionTestCase<C, S, Q> {

//	private QueryExceptionTestCase(Q queryTestOperation, Class<? extends Exception> expectedExceptionType) {
//		super(queryTestOperation, Optional.empty(), Optional.empty(), expectedExceptionType);
//	}
//	
//	private QueryExceptionTestCase(Q queryTestOperation, Class<? extends Exception> expectedExceptionType, PortRequestsVerifier portRequestsVerifier) {
//		super(queryTestOperation, Optional.empty(), Optional.of(portRequestsVerifier), expectedExceptionType);
//	}
	
	public QueryExceptionTestCase(Q testOperation, Optional<S> expectedState,
			Optional<PortRequestsVerifier> portRequestsVerifier, Class<? extends Exception> expectedExceptionType) {
		super(testOperation, expectedState, portRequestsVerifier, expectedExceptionType);
	}
	
	
	public static <C extends TestContext<S>, S, Q extends QueryTestOperation<C, ?>> QueryExceptionTestCase<C, S, Q> expectedExceptionQueryTestCase(Q queryTestOperation, Class<? extends Exception> expectedExceptionType) {
		return new QueryExceptionTestCase<C, S, Q>(queryTestOperation, Optional.empty(), Optional.empty(), expectedExceptionType);
	}
	
	public static <C extends TestContext<S>, S, Q extends QueryTestOperation<C, ?>> QueryExceptionTestCase<C, S, Q> expectedExceptionAndVerifyPortRequestsQueryTestCase(Q queryTestOperation, Class<? extends Exception> expectedExceptionType, PortRequestsVerifier portRequestsVerifier) {
		return new QueryExceptionTestCase<C, S, Q>(queryTestOperation, Optional.empty(), Optional.of(portRequestsVerifier), expectedExceptionType);
	}

}
