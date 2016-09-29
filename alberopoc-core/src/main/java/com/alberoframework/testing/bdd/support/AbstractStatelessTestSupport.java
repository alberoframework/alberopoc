package com.alberoframework.testing.bdd.support;

import com.alberoframework.lang.VoidUnit;
import com.alberoframework.testing.bdd.context.StatelessTestContext;
import com.alberoframework.testing.bdd.port.PortRegistry;

public abstract class AbstractStatelessTestSupport<T extends StatelessTestContext> extends AbstractTestSupport<T, VoidUnit> {

	@Override
	protected final T context(VoidUnit initialState, PortRegistry ports) {
		return context(ports);
	}
	
}
