package com.alberoframework.testing.bdd.context;

import com.alberoframework.lang.VoidUnit;

public abstract class AbstractStatelessTestContext extends AbstractTestContext<VoidUnit> implements StatelessTestContext {

	@Override
	public final VoidUnit currentState() {
		return VoidUnit.instance();
	}

}
