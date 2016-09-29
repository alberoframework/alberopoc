package com.alberoframework.testing.bdd.context;

import com.alberoframework.testing.bdd.port.PortRegistry;

public interface TestContext<S> {

		S currentState();
		
		PortRegistry portRegistry();
	
}
