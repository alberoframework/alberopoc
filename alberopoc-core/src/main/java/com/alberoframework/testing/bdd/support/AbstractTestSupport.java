package com.alberoframework.testing.bdd.support;

import java.util.Arrays;
import java.util.Optional;

import org.junit.runner.RunWith;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.junit.BddJUnitTestRunner;
import com.alberoframework.testing.bdd.outcome.TestOutcome;
import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.testing.bdd.port.PortRegistryImpl;
import com.alberoframework.testing.bdd.runner.TestRunner;
import com.alberoframework.testing.bdd.testcase.behavior.BehaviorExceptionTestCase;
import com.alberoframework.testing.bdd.testcase.behavior.BehaviorHappyTestCase;
import com.alberoframework.testing.bdd.testcase.behavior.BehaviorTestOperation;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsStubber;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsStubberChain;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsVerifier;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsVerifierChain;
import com.alberoframework.testing.bdd.testcase.query.QueryExceptionTestCase;
import com.alberoframework.testing.bdd.testcase.query.QueryHappyTestCase;
import com.alberoframework.testing.bdd.testcase.query.QueryTestOperation;

@RunWith(BddJUnitTestRunner.class)
public abstract class AbstractTestSupport<C extends TestContext<S>, S>  {
	
	protected <T> Optional<T> nonEmpty(T value) {
		return Optional.ofNullable(value);
	}
	
	protected <T> Optional<T> empty() {
		return Optional.empty();
	}
	
	protected abstract C context(S initialState, PortRegistry ports);
	
	protected abstract C context(PortRegistry ports);
	
//	protected abstract C context(S initialState);
	
	protected PortRegistry ports() {
		return new PortRegistryImpl();
	}
	
	public TestSpecification given(S initialState) {
		return new TestSpecification(context(initialState, ports()));
	}
	
	public TestSpecification given(S initialState, PortRequestsStubber portRequestsStubber) {
		PortRegistry ports = ports();
		portRequestsStubber.stubPortRequests(ports);
		return new TestSpecification(context(initialState, ports));
	}
	
	public TestSpecification given() {
		return new TestSpecification(context(ports()));
	}
	
	public TestSpecification given(PortRequestsStubber portRequestsStubber) {
		PortRegistry ports = ports();
		portRequestsStubber.stubPortRequests(ports);
		return new TestSpecification(context(ports));
	}
	
	protected PortRequestsStubber portStubs(PortRequestsStubber ... stubbers) {
		return new PortRequestsStubberChain(Arrays.asList(stubbers));
	}
	
	protected PortRequestsVerifierChain portRequests(PortRequestsVerifier ... verifiers) {
		return new PortRequestsVerifierChain(Arrays.asList(verifiers));
	}
	
	public class TestSpecification {
			private TestRunner<C, S> runner;
				
			public TestSpecification(C context) {
				this.runner = new TestRunner<C, S>(context);
			}
			
			public TestSpecification(TestRunner<C, S> runner) {
				this.runner = runner;
			}

			public <OUT> BehaviorTestCaseBuilder<OUT> when(BehaviorTestOperation<C, OUT> behaviorExecution) {
				return new BehaviorTestCaseBuilder<OUT>(runner, behaviorExecution);
			}
			
			public <OUT> QueryTestCaseBuilder<OUT> when(QueryTestOperation<C, OUT> queryExecution) {
				return new QueryTestCaseBuilder<OUT>(runner, queryExecution);
			}
			
			public TestOutcome run() {
				return runner.runTest();
			}
	}
	
	public class QueryTestCaseBuilder<OUT> {
		private TestRunner<C, S> runner;
		
		QueryTestOperation<C, OUT> queryExecution;
			
		public QueryTestCaseBuilder(TestRunner<C, S> runner, QueryTestOperation<C, OUT> queryExecution) {
			this.runner = runner;
			this.queryExecution = queryExecution;
		}

//		public TestSpecification then() {
////			runner.addTestCase(new QueryHappyTestCase<C, S, QueryExecution<C, OUT>, OUT>(queryExecution));
//			QueryHappyTestCase<C, S, QueryTestOperation<C, OUT>, OUT> testCase = QueryHappyTestCase.noOutputQueryTestCase(queryExecution);
//			runner.addTestCase(testCase);
//			return new TestSpecification(runner);
//		}
		
		public TestSpecification then(PortRequestsVerifier portRequestsVerifier) {
			QueryHappyTestCase<C, S, QueryTestOperation<C, OUT>, OUT> testCase = QueryHappyTestCase.noOutputAndVerifyPortRequestsQueryTestCase(queryExecution, portRequestsVerifier);
			runner.addTestCase(testCase);
			return new TestSpecification(runner);
		}
		
		public TestSpecification then(OUT expectedOutput) {
//			if (expectedOutput == null) return then();
			QueryHappyTestCase<C, S, QueryTestOperation<C, OUT>, OUT> testCase = QueryHappyTestCase.expectedOutputQueryTestCase(queryExecution, expectedOutput);
			runner.addTestCase(testCase);
			return new TestSpecification(runner);
		}
		
		public TestSpecification then(OUT expectedOutput, PortRequestsVerifier portRequestsVerifier) {
//			if (expectedOutput == null) return then(portRequestsVerifier);
			QueryHappyTestCase<C, S, QueryTestOperation<C, OUT>, OUT> testCase = QueryHappyTestCase.expectedOutputAndVerifyPortRequestsQueryTestCase(queryExecution, expectedOutput, portRequestsVerifier);
			runner.addTestCase(testCase);
			return new TestSpecification(runner);
		}
		
		public TestSpecification then(OUT expectedOutput, S expectedState) {
//			if (expectedState == null) return then(expectedOutput);
			
			QueryHappyTestCase<C, S, QueryTestOperation<C, OUT>, OUT> testCase = QueryHappyTestCase.expectedOutputAndExpectedStateQueryTestCase(queryExecution, expectedOutput, expectedState);
			
			runner.addTestCase(testCase);
			return new TestSpecification(runner);
		}
		
		public TestSpecification then(OUT expectedOutput, S expectedState, PortRequestsVerifier portRequestsVerifier) {
//			if (expectedState == null) return then(expectedOutput, portRequestsVerifier);
			
			QueryHappyTestCase<C, S, QueryTestOperation<C, OUT>, OUT> testCase = QueryHappyTestCase.expectedOutputAndExpectedStateAndVerifyPortRequestsQueryTestCase(queryExecution, expectedOutput, expectedState, portRequestsVerifier);
			
			runner.addTestCase(testCase);
			return new TestSpecification(runner);
		}
		
		public TestSpecification then(Class<? extends Exception> expectedExceptionType) {
			QueryExceptionTestCase<C, S, QueryTestOperation<C, OUT>> testCase = QueryExceptionTestCase.expectedExceptionQueryTestCase(queryExecution, expectedExceptionType);
			runner.addTestCase(testCase);
			return new TestSpecification(runner);
		}
		
		public TestSpecification then(Class<? extends Exception> expectedExceptionType, PortRequestsVerifier portRequestsVerifier) {
			QueryExceptionTestCase<C, S, QueryTestOperation<C, OUT>> testCase = QueryExceptionTestCase.expectedExceptionAndVerifyPortRequestsQueryTestCase(queryExecution, expectedExceptionType, portRequestsVerifier);
			runner.addTestCase(testCase);
			return new TestSpecification(runner);
		}
	}
	
	public class BehaviorTestCaseBuilder<OUT> {
		
			private TestRunner<C, S> runner;
			
			BehaviorTestOperation<C, OUT> behaviorExecution;
				
			public BehaviorTestCaseBuilder(TestRunner<C, S> runner, BehaviorTestOperation<C, OUT> behaviorExecution) {
				this.runner = runner;
				this.behaviorExecution = behaviorExecution;
			}
	
			public TestSpecification then(S expectedState) {
//				runner.addTestCase(new BehaviorHappyTestCase<C, S, BehaviorExecution<C, OUT>, OUT>(
//						behaviorExecution, 
//						expectedState));
				
				BehaviorHappyTestCase<C, S, BehaviorTestOperation<C, OUT>, OUT> testCase = BehaviorHappyTestCase.expectedStateBehaviorTestCase(
						behaviorExecution, 
						expectedState);
				
				runner.addTestCase(testCase);
				return new TestSpecification(runner);
			}
			
			public TestSpecification then(S expectedState, PortRequestsVerifier portRequestsVerifier) {
				
				BehaviorHappyTestCase<C, S, BehaviorTestOperation<C, OUT>, OUT> testCase = BehaviorHappyTestCase.expectedStateAndVerifyPortRequestsBehaviorTestCase(
						behaviorExecution, 
						expectedState,
						portRequestsVerifier);
				
				runner.addTestCase(testCase);
				return new TestSpecification(runner);
			}
			
			public TestSpecification then(OUT expectedOutput, S expectedState) {
//				if (expectedOutput == null) return then(expectedState);
				
				BehaviorHappyTestCase<C, S, BehaviorTestOperation<C, OUT>, OUT> testCase = BehaviorHappyTestCase.expectedStateAndExpectedOutputBehaviorTestCase(
						behaviorExecution, 
						expectedOutput,
						expectedState 
						);
				
				runner.addTestCase(testCase);
				return new TestSpecification(runner);
			}
			
			public TestSpecification then(OUT expectedOutput, S expectedState, PortRequestsVerifier portRequestsVerifier) {
//				if (expectedOutput == null) return then(expectedState);
				
				BehaviorHappyTestCase<C, S, BehaviorTestOperation<C, OUT>, OUT> testCase = BehaviorHappyTestCase.expectedStateAndExpectedOutputAndVerifyPortRequestsBehaviorTestCase(
						behaviorExecution, 
						expectedOutput,
						expectedState,
						portRequestsVerifier
						);
				
				runner.addTestCase(testCase);
				return new TestSpecification(runner);
			}
			
			public TestSpecification then(Class<? extends Exception> expectedExceptionType) {
				BehaviorExceptionTestCase<C, S, BehaviorTestOperation<C, OUT>> testCase = BehaviorExceptionTestCase.expectedExceptionBehaviorTestCase(
						behaviorExecution, 
						expectedExceptionType);
				
				runner.addTestCase(testCase);
				
				return new TestSpecification(runner);
			}
			
			public TestSpecification then(Class<? extends Exception> expectedExceptionType, PortRequestsVerifier portRequestsVerifier) {
				BehaviorExceptionTestCase<C, S, BehaviorTestOperation<C, OUT>> testCase = BehaviorExceptionTestCase.expectedExceptionAndVerifyPortRequestsBehaviorTestCase(
						behaviorExecution, 
						expectedExceptionType,
						portRequestsVerifier);
				
				runner.addTestCase(testCase);
				
				return new TestSpecification(runner);
			}
		
	}
	
	
}
