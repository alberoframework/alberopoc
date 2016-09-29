package com.alberoframework.component.query.testing;

import java.util.ArrayList;
import java.util.List;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsStubber;

public class QueryStubber implements PortRequestsStubber {

	private List<QueryStub<?>> stubs = new ArrayList<QueryStub<?>>();
	
	public QueryStubber(List<QueryStub<?>> stubs) {
		this.stubs = stubs;
	}

	@Override
	public void stubPortRequests(PortRegistry portRegistry) {
		AbstractQueryGatewayStub queryGatewayStub = (AbstractQueryGatewayStub) portRegistry.get("queryGateway");
		if (queryGatewayStub == null) {
			throw new IllegalStateException("Query Gateway not found in ports registry");
		}
		
		for (QueryStub<?> stub : stubs) {
			queryGatewayStub.stub(stub.getQuery(), stub.getResponse());
		}
	}
	
	public static class QueryStub<R> {
		
		private RequestEnvelope<? extends Query<R>, R> query;
		private R response;
		
		public QueryStub(RequestEnvelope<? extends Query<R>, R> query, R response) {
			this.query = query;
			this.response = response;
		}
		
		public QueryStub(RequestEnvelope<? extends Query<R>, R> query) {
			this.query = query;
		}

		public RequestEnvelope<? extends Query<R>, R> getQuery() {
			return query;
		}
		
		public R getResponse() {
			return response;
		}
	}
	
}
