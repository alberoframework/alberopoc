package com.alberoframework.testing.bdd.testcase.operation;

import com.alberoframework.lang.object.BaseObject;
import com.alberoframework.testing.bdd.context.TestContext;

public abstract class AbstractTestOperation<C extends TestContext<?>, OUT> extends BaseObject implements TestOperation<C, OUT> {

	@Override
	public String description() {
		return toString();
	}
	
}
