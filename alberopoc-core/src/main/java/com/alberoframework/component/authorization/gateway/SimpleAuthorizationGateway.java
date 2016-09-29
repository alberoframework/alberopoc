package com.alberoframework.component.authorization.gateway;

import java.util.function.Function;
import java.util.function.Predicate;

import com.alberoframework.component.query.contract.PredicateQuery;
import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;

public interface SimpleAuthorizationGateway {

	public <REQ extends Request<RES>, RES> boolean authorized(SimpleAuthenticatedRequestEnvelope<REQ, RES> requestEnvelope);
	
//	public <REQ extends Request<RES>, RES> void registerAuthorization(Class<REQ> requestType, boolean authorization);
	
	public <REQ extends Request<RES>, RES> void registerAuthorizationPredicate(Class<REQ> requestType, Predicate<SimpleAuthenticatedRequestEnvelope<REQ, RES>> authorizationPredicate);
	
	public <REQ extends Request<RES>, RES> void registerAuthorizationSpecification(Class<REQ> requestType, Function<SimpleAuthenticatedRequestEnvelope<REQ, RES>, PredicateQuery> authorizationQuery);
	
}
