package com.alberoframework.testing.bdd.testcase;

import com.alberoframework.testing.bdd.context.AbstractStatelessTestContext;
import com.alberoframework.testing.bdd.context.AbstractTestContext;
import com.alberoframework.testing.bdd.outcome.TestFailureOutcome;
import com.alberoframework.testing.bdd.outcome.TestOutcome;
import com.alberoframework.testing.bdd.outcome.TestSuccessfulOutcome;
import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.testing.bdd.port.PortRegistryImpl;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsVerifier;

public class TestCaseTestStubs {
	
	public static class SimpleCounterStatelessTestContext extends AbstractStatelessTestContext {
		private SimpleCounter simpleCounter;
		
		public SimpleCounterStatelessTestContext(Integer counter) {
			this.simpleCounter = new SimpleCounter(counter);
		}
		
		public SimpleCounter simpleCounter() {
			return simpleCounter;
		}
	}
	
	public static class SimpleCounterTestContext extends AbstractTestContext<Integer> {

		private SimpleCounter simpleCounter;
		
		public SimpleCounterTestContext(Integer counter) {
			this.simpleCounter = new SimpleCounter(counter);
		}
		
		public SimpleCounterTestContext(Integer counter, SimplePort simplePort) {
			this.simpleCounter = new SimpleCounter(counter, simplePort);
			PortRegistry registry = new PortRegistryImpl();
			registry.put("simplePort", simplePort);
			setPortRegistry(registry);
		}
		
		public SimpleCounter simpleCounter() {
			return simpleCounter;
		}
		
		@Override
		public Integer currentState() {
			return simpleCounter.counter;  // this context uses the field directly to bypass the counter() getter that has validation and let behavior tests read the state and be certain that no exception will be thrown
		}
	}
	
	public static class SimpleCounterTestContextUsingValidatedQueryToReadState extends SimpleCounterTestContext {

		public SimpleCounterTestContextUsingValidatedQueryToReadState(Integer counter) {
				super(counter);
		}
		
		@Override
		public Integer currentState() {
			return simpleCounter().counter(); //this context uses a getter that has validation, to test exceptions while reading state, which should not be confused with exceptions thrown while executing the operation.
		}
	}
	
	
	//Bugged because a context is either stateless of stateful, if you declare Integer as your state IT CANNOT BE NULL, but there are plenty of stateful
	//implementations with null as a possible state, this is a simulation of such a context
	
	//however its main purpose is to respond with the wrong state, but responding with null as the wrong
	//value we also test that the test case handles nulls gracefully
	public static class SimpleCounterBuggedStatefulContext extends SimpleCounterTestContext {

		public SimpleCounterBuggedStatefulContext(Integer counter) {
				super(counter);
		}
		
		@Override
		public Integer currentState() {
			return null;
		}
	}
	
	
	public static class SimplePortWasCalledButNotExpectedPortRequestsVerificationFailure extends TestFailureOutcome {
		@Override
		public String description() {
			return getClass().getSimpleName();
		}
	}
	
	public static class SimplePortWasNotCalledButExpectedPortRequestsVerificationFailure extends TestFailureOutcome {
		@Override
		public String description() {
			return getClass().getSimpleName();
		}
	}
	
	public static class SimplePortWasMoreThanOncePortRequestsVerificationFailure extends TestFailureOutcome {
		@Override
		public String description() {
			return getClass().getSimpleName();
		}
	}
	
  public static class SimpleCounterVerifyOnePortRequestCallHappened implements PortRequestsVerifier {
		
		@Override
		public TestOutcome verifyPortRequests(PortRegistry portRegistry) {
			
			SimplePort port = (SimplePort) portRegistry.get("simplePort");
			
			if (port.portCounter() > 1)
				return new SimplePortWasMoreThanOncePortRequestsVerificationFailure();
			else if (port.portCounter() == 0) {
				return new SimplePortWasNotCalledButExpectedPortRequestsVerificationFailure();
			}
			
			return new TestSuccessfulOutcome();
		}
		
	}
	
	public static class SimpleCounterVerifyNoPortRequestCallsHappened implements PortRequestsVerifier {
		
		@Override
		public TestOutcome verifyPortRequests(PortRegistry portRegistry) {
			SimplePort port = (SimplePort) portRegistry.get("simplePort");
			
			if (port.portCounter() > 0)
				return new SimplePortWasCalledButNotExpectedPortRequestsVerificationFailure();
			
			return new TestSuccessfulOutcome();
		}
	}
	
	
	
	
	public static class SimplePort {
		private Integer portCounter = 0;
		
		public Integer portCounter() {
			return portCounter;
		}
		
		public void call() {
			portCounter++;
		}
	}
	
	public static class SimpleCounter {
		
		public static final Integer MAX_LIMIT = 7;
		
		private Integer counter;
		
		private SimplePort simplePort;

		public SimpleCounter(Integer counter) {
			this.counter = counter;
		}
		
		public SimpleCounter(Integer counter, SimplePort simplePort) {
			this.counter = counter;
			this.simplePort = simplePort;
		}
		 
		public Integer counter() {
			if (counter > MAX_LIMIT) {
				throw new CounterInIllegalStateException();  //counter is in an illegalstate, the query throws an exception in this case (like a findOne in a repository that finds two entities for example)
			}
			return counter;
		}
		
		public void incrementCounter() {
			if (counter >= MAX_LIMIT) {
				throw new CounterCannotPassTheMaxLimitException();
			}
			counter++;
		}
		
		public void incrementCounterWithPortCall() {
			if (counter >= MAX_LIMIT) {
				throw new CounterCannotPassTheMaxLimitException();
			}
			counter++;
			simplePort.call();
		}
		
		//Returns how many times you can still increment the counter before reaching the limit
		public Integer incrementCounterWithOutput() {
			incrementCounter();
			return MAX_LIMIT - counter;
		}
		
		//Returns how many times you can still increment the counter before reaching the limit
			public Integer incrementCounterWithOutputAndPortCall() {
				incrementCounterWithPortCall();
				return MAX_LIMIT - counter;
			}
		
		//changes state and then checks for invariants, should be the other way around, because of this "bug" if an exception is thrown, its state will have changed anyway. 
		public void buggedIncrementCounter() {
			counter++;
			if (counter > MAX_LIMIT) {
				throw new CounterCannotPassTheMaxLimitException();
			}
		}
		
		public static class CounterCannotPassTheMaxLimitException extends RuntimeException {

			private static final long serialVersionUID = 1L;
			
		}
		
		public static class CounterInIllegalStateException extends IllegalStateException {
			private static final long serialVersionUID = 1L;
		}
		 
	}

}
