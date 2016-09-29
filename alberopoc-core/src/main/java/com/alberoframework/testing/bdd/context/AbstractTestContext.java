package com.alberoframework.testing.bdd.context;

import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.testing.bdd.port.PortRegistryImpl;
import com.google.common.base.Preconditions;

public abstract class AbstractTestContext<S> implements TestContext<S> {
	
	private PortRegistry portRegistry = new PortRegistryImpl();
	
	@Override
	public final PortRegistry portRegistry() {
		return portRegistry;
	}
	
	public void setPortRegistry(PortRegistry portRegistry) {
		Preconditions.checkNotNull(portRegistry);
		this.portRegistry = portRegistry;
	}
	
}
