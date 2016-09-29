package com.alberoframework.component.authorization.testing;

import com.alberoframework.component.authorization.gateway.SimpleAuthorizationGateway;
import com.alberoframework.component.authorization.testing.SimpleAuthorizationGatewayTestOperations.SimpleAuthorizationGatewayTestOperation;
import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.query.contract.AndPredicateQuery;
import com.alberoframework.component.query.contract.FalsePredicateQuery;
import com.alberoframework.component.query.contract.NotPredicateQuery;
import com.alberoframework.component.query.contract.OrPredicateQuery;
import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.contract.TruePredicateQuery;
import com.alberoframework.component.query.contract.XorPredicateQuery;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;
import com.alberoframework.component.query.handler.AndSimpleAuthenticatedPredicateQueryHandler;
import com.alberoframework.component.query.handler.FalseSimpleAuthenticatedPredicateQueryHandler;
import com.alberoframework.component.query.handler.NotSimpleAuthenticatedPredicateQueryHandler;
import com.alberoframework.component.query.handler.OrSimpleAuthenticatedPredicateQueryHandler;
import com.alberoframework.component.query.handler.TrueSimpleAuthenticatedPredicateQueryHandler;
import com.alberoframework.component.query.handler.XorSimpleAuthenticatedPredicateQueryHandler;
import com.alberoframework.component.query.testing.SimpleAuthenticatedQueryGatewayStub;
import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;

public abstract class AbstractSimpleAuthorizationGatewayTestSupport extends AbstractAuthorizationGatewayTestSupport<SimpleAuthorizationGateway, SimpleAuthenticatedQueryGateway> {

	public <Q1 extends Query<R1>, R1> SimpleAuthenticatedRequestEnvelope<Q1, R1> query(Q1 query, String userId) {
		return new SimpleAuthenticatedRequestEnvelope<Q1, R1>(userId, query);
	}
	
	public <Q1 extends Query<R1>, R1> SimpleAuthenticatedRequestEnvelope<Q1, R1> query(Q1 query) {
		return new SimpleAuthenticatedRequestEnvelope<Q1, R1>(query);
	}
	
	public <Q1 extends Query<R1>, R1> SimpleAuthenticatedRequestEnvelope<Q1, R1> queryWithSystemUser(Q1 query) {
		return SimpleAuthenticatedRequestEnvelope.envelopeWithSystemUser(query);
	}
	
	public <C1 extends Command<R1>, R1> SimpleAuthenticatedRequestEnvelope<C1, R1> command(C1 command, String userId) {
		return new SimpleAuthenticatedRequestEnvelope<C1, R1>(userId, command);
	}
	
	public <C1 extends Command<R1>, R1> SimpleAuthenticatedRequestEnvelope<C1, R1> command(C1 command) {
		return new SimpleAuthenticatedRequestEnvelope<C1, R1>(command);
	}
	
	public <REQ extends Request<RES>, RES> SimpleAuthorizationGatewayTestOperation<REQ, RES> authorized(SimpleAuthenticatedRequestEnvelope<REQ, RES> requestEnvelope) {
		return new SimpleAuthorizationGatewayTestOperation<REQ, RES>(requestEnvelope);
	}
	
	@Override
	protected SimpleAuthenticatedQueryGateway queryGateway() {
		SimpleAuthenticatedQueryGatewayStub queryGateway = new SimpleAuthenticatedQueryGatewayStub();
		queryGateway.registerHandler(AndPredicateQuery.class, new AndSimpleAuthenticatedPredicateQueryHandler(queryGateway));
		queryGateway.registerHandler(OrPredicateQuery.class, new OrSimpleAuthenticatedPredicateQueryHandler(queryGateway));
		queryGateway.registerHandler(XorPredicateQuery.class, new XorSimpleAuthenticatedPredicateQueryHandler(queryGateway));
		queryGateway.registerHandler(NotPredicateQuery.class, new NotSimpleAuthenticatedPredicateQueryHandler(queryGateway));
		queryGateway.registerHandler(TruePredicateQuery.class, new TrueSimpleAuthenticatedPredicateQueryHandler(queryGateway));
		queryGateway.registerHandler(FalsePredicateQuery.class, new FalseSimpleAuthenticatedPredicateQueryHandler(queryGateway));
		return queryGateway;
	}
	
}
