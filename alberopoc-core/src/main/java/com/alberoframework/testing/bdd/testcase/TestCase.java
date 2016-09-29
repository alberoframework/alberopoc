package com.alberoframework.testing.bdd.testcase;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.outcome.TestOutcome;

public interface TestCase<C extends TestContext<S>, S> {

	public TestOutcome executeWith(C context);
	
}
