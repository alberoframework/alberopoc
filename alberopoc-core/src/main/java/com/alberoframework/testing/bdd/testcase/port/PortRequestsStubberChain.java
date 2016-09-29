package com.alberoframework.testing.bdd.testcase.port;

import java.util.ArrayList;
import java.util.List;

import com.alberoframework.testing.bdd.port.PortRegistry;

public class PortRequestsStubberChain implements PortRequestsStubber {

	private List<PortRequestsStubber> stubbers = new ArrayList<PortRequestsStubber>();
	
	public PortRequestsStubberChain(List<PortRequestsStubber> stubbers) {
		this.stubbers = stubbers;
	}

	@Override
	public void stubPortRequests(PortRegistry portRegistry) {
		for (PortRequestsStubber stubber : stubbers) {
			stubber.stubPortRequests(portRegistry);
		}
	}
	
}
