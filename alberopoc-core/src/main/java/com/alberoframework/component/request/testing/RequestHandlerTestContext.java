package com.alberoframework.component.request.testing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.handler.RequestHandler;
import com.alberoframework.core.reflection.Reflection;
import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.testing.bdd.context.AbstractTestContext;
import com.alberoframework.testing.bdd.port.PortRegistry;

public class RequestHandlerTestContext<RH extends RequestHandler<ENV, REQ, RES>, ENV extends RequestEnvelope<REQ, RES>, REQ extends Request<RES>, RES> extends AbstractTestContext<Set<Entity<?>>> {

	private EntityTestingStore entityStore;
	
	private RH requestHandler;
	
	public RequestHandlerTestContext(Class<RH> requestHandlerType, EntityTestingStore entityStore, PortRegistry portRegistry, Set<Entity<?>> initialState /*, boolean setterInjection*/) {
		this.entityStore = entityStore;
		setPortRegistry(portRegistry);
		
		entityStore.initializeState(initialState);
		
		Map<String, Object> dependencies = new HashMap<>(portRegistry.asMap());
		
		dependencies.putAll(entityStore.getDependencies());
		
		requestHandler = createRequestHandler(requestHandlerType, dependencies);
		
		Reflection.injectDependencies(requestHandler, dependencies);
		
	}
	
	public RH createRequestHandler(Class<RH> requestHandlerType, Map<String, Object> dependencies) {
		//TODO: find the best constructor and call it using the dependencies?
		return Reflection.newInstance(requestHandlerType);
	}
	
	public RH requestHandler() {
		return requestHandler;
	}

	@Override
	public Set<Entity<?>> currentState() {
		return entityStore.getCurrentState();
	}
	
	
}
