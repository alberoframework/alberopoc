package com.alberoframework.testing.bdd.testcase.query;

import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.operation.AbstractTestOperation;

public abstract class AbstractQueryTestOperation<C extends TestContext<?>, OUT> extends AbstractTestOperation<C, OUT> implements QueryTestOperation<C, OUT> {

	@Override
	public final OUT execute(C context) {
		return doExecute(context);
	}
	
	protected abstract OUT doExecute(C context);
	
}
