package com.alberoframework.component.authorization.gateway;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import com.alberoframework.component.query.contract.PredicateQuery;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;
import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;

public class SimpleAuthorizationGatewayImpl implements
		SimpleAuthorizationGateway {

	private SimpleAuthenticatedQueryGateway queryGateway;

	private Map<Class<?>, Predicate<?>> authorizationMap = new HashMap<>();

	public SimpleAuthorizationGatewayImpl(
			SimpleAuthenticatedQueryGateway queryGateway) {
		setQueryGateway(queryGateway);
	}

	public SimpleAuthorizationGatewayImpl() {
	}

	@Override
	public <REQ extends Request<RES>, RES> boolean authorized(
			SimpleAuthenticatedRequestEnvelope<REQ, RES> requestEnvelope) {

		// SYSTEM CAN EXECUTE ANY REQUEST
		if (requestEnvelope.authenticatedAsSystem())
			return true;

		Class<?> requestType = requestEnvelope.getRequest().getClass();

		Predicate<SimpleAuthenticatedRequestEnvelope<? extends Request<?>, ?>> authorizationPredicate = authorizationPredicate(
				requestType).orElseGet(() -> e -> true);

		boolean result = authorizationPredicate.test(requestEnvelope);

		return result;
	}

	// @Override
	// public <REQ extends Request<RES>, RES> void
	// registerAuthorization(Class<REQ> requestType,
	// boolean authorization) {
	// authorizationMap.put(requestType, r -> authorization);
	// }

	@Override
	public <REQ extends Request<RES>, RES> void registerAuthorizationPredicate(
			Class<REQ> requestType,
			Predicate<SimpleAuthenticatedRequestEnvelope<REQ, RES>> authorizationPredicate) {
		authorizationMap.put(requestType, authorizationPredicate);
	}

	@Override
	public <REQ extends Request<RES>, RES> void registerAuthorizationSpecification(
			Class<REQ> requestType,
			Function<SimpleAuthenticatedRequestEnvelope<REQ, RES>, PredicateQuery> authorizationQuery) {
		Predicate<SimpleAuthenticatedRequestEnvelope<REQ, RES>> authorizationPredicate = e -> queryGateway
				.handle(SimpleAuthenticatedRequestEnvelope
						.envelopeWithSystemUser(authorizationQuery.apply(e)));
		authorizationMap.put(requestType, authorizationPredicate);
	}

	@SuppressWarnings("unchecked")
	private Optional<Predicate<SimpleAuthenticatedRequestEnvelope<? extends Request<?>, ?>>> authorizationPredicate(
			Class<?> requestType) {
		return Optional
				.ofNullable((Predicate<SimpleAuthenticatedRequestEnvelope<? extends Request<?>, ?>>) authorizationMap
						.get(requestType));
	}

	public void setQueryGateway(SimpleAuthenticatedQueryGateway queryGateway) {
		this.queryGateway = queryGateway;
	}

}
