package com.alberoframework.component.authorization.testing;

import java.util.HashMap;
import java.util.Map;

import com.alberoframework.core.reflection.Reflection;
import com.alberoframework.testing.bdd.context.AbstractStatelessTestContext;
import com.alberoframework.testing.bdd.port.PortRegistry;

public class AuthorizationGatewayTestContext<AG> extends AbstractStatelessTestContext {

	private AG authorizationGateway;

	public AuthorizationGatewayTestContext(AG authorizationGateway, PortRegistry portRegistry) {
		
		this.authorizationGateway = authorizationGateway;
		
		setPortRegistry(portRegistry);
		
		Map<String, Object> dependencies = new HashMap<>(portRegistry.asMap());
		
//		authorizationGateway = createAuthorizationGateway(authorizationGatewayType, dependencies);
		
		Reflection.injectDependencies(authorizationGateway, dependencies);
	}
	
	public AG authorizationGateway() {
		return authorizationGateway;
	}
	
}
