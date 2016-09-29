package com.alberoframework.testing.bdd.testcase.behavior;

import com.alberoframework.lang.VoidUnit;
import com.alberoframework.testing.bdd.context.TestContext;
import com.alberoframework.testing.bdd.testcase.operation.AbstractTestOperation;

public abstract class AbstractBehaviorTestOperationWithoutOutput<C extends TestContext<?>>
		extends AbstractTestOperation<C, VoidUnit> implements BehaviorTestOperation<C, VoidUnit> {

	@Override
	public final VoidUnit execute(C context) {
		doExecute(context);
		return VoidUnit.instance();
	}

	protected abstract void doExecute(C context);

}
