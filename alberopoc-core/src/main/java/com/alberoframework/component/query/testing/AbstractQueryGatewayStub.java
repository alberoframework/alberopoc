package com.alberoframework.component.query.testing;

import java.util.List;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.testing.AbstractRequestGatewayStub;

public abstract class AbstractQueryGatewayStub extends AbstractRequestGatewayStub {

	protected void stub(RequestEnvelope<? extends Query<?>, ?> query, Object response) {
		stubRequest(query, response);
	}
	
	@SuppressWarnings("unchecked")
	public List<? extends RequestEnvelope<? extends Query<?>, ?>> queriesReceived() {
		return (List<? extends RequestEnvelope<? extends Query<?>, ?>>) requestsReceived();
	}

}
