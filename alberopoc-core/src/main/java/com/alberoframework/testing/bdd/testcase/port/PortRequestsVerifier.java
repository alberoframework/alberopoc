package com.alberoframework.testing.bdd.testcase.port;

import com.alberoframework.testing.bdd.outcome.TestOutcome;
import com.alberoframework.testing.bdd.port.PortRegistry;

public interface PortRequestsVerifier {

	public TestOutcome verifyPortRequests(PortRegistry portRegistry);
	
}
