package com.alberoframework.component.authorization.testing;

import java.util.Arrays;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.testing.QueryStubber;
import com.alberoframework.component.query.testing.QueryVerifier;
import com.alberoframework.component.query.testing.QueryStubber.QueryStub;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.testing.bdd.support.AbstractStatelessTestSupport;

public abstract class AbstractAuthorizationGatewayTestSupport<AG, QG> extends AbstractStatelessTestSupport<AuthorizationGatewayTestContext<AG>> {

	@Override
	protected AuthorizationGatewayTestContext<AG> context(PortRegistry ports) {
		return new AuthorizationGatewayTestContext<AG>(authorizationGateway(), ports);
	}
	
	public QueryStubber queryStubs(QueryStub<?> ... stubs) {
		return new QueryStubber(Arrays.asList(stubs));
	}
	
	public <R1> QueryStub<R1> queryStub (RequestEnvelope<? extends Query<R1>, R1> query, R1 response) {
		return new QueryStub<R1>(query, response);
	}
	
//	public <R1> QueryStub<R1> queryStub (RequestEnvelope<? extends Query<R1>, R1> query) {
//		return new QueryStub<R1>(query);
//	}
	
	@SuppressWarnings("unchecked")
	public QueryVerifier queriesSent(RequestEnvelope<? extends Query<?>, ?> ... queries) {
		return new QueryVerifier(Arrays.asList(queries));
	}
	
	protected abstract QG queryGateway();
	
	protected abstract AG authorizationGateway();
	
	@Override
	protected PortRegistry ports() {
		PortRegistry registry = super.ports();
		registry.put("queryGateway", queryGateway());
		return registry;
	}

	
}
