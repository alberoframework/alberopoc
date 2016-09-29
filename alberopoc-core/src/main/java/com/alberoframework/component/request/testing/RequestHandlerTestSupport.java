package com.alberoframework.component.request.testing;

import java.util.Set;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.handler.RequestHandler;
import com.alberoframework.core.reflection.Reflection;
import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.testing.bdd.support.AbstractTestSupport;
import com.google.common.collect.ImmutableSet;

public abstract class RequestHandlerTestSupport<RH extends RequestHandler<ENV, REQ, RES>, ENV extends RequestEnvelope<REQ, RES>, REQ extends Request<RES>, RES, ES extends EntityTestingStore> extends AbstractTestSupport<RequestHandlerTestContext<RH, ENV, REQ, RES>, Set<Entity<?>>> {

	protected abstract ES entityStore();
	
	@SuppressWarnings("unchecked")
	@Override
	protected RequestHandlerTestContext<RH, ENV, REQ, RES> context(Set<Entity<?>> initialState, PortRegistry ports) {
		return new RequestHandlerTestContext<RH, ENV, REQ, RES>((Class<RH>) Reflection.resolveFirstGenericParameterOfType(this.getClass(), RequestHandler.class), entityStore(), ports, initialState);
	}
	
	@Override
	protected RequestHandlerTestContext<RH, ENV, REQ, RES> context(PortRegistry ports) {
		return context(entities(), ports);
	}
	
	public Set<Entity<?>> entities(Entity<?> ... entities) {
		Set<Entity<?>> initialState = ImmutableSet.of();
		if (entities != null) 
			initialState = ImmutableSet.copyOf(entities);
		return initialState;
	}

	
}
