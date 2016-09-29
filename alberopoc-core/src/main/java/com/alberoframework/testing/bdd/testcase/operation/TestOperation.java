package com.alberoframework.testing.bdd.testcase.operation;

import com.alberoframework.testing.bdd.context.TestContext;

public interface TestOperation<C extends TestContext<?>, OUT> {
	
		OUT execute(C context);
		
		String description();
	
}
