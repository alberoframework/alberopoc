package com.alberoframework.component.authorization.testing;

import com.alberoframework.component.authorization.gateway.SimpleAuthorizationGateway;
import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.testing.bdd.testcase.query.AbstractQueryTestOperation;

public class SimpleAuthorizationGatewayTestOperations {

	public static class SimpleAuthorizationGatewayTestOperation<REQ extends Request<RES>, RES> extends AbstractQueryTestOperation<AuthorizationGatewayTestContext<SimpleAuthorizationGateway>, Boolean> {
		
		private SimpleAuthenticatedRequestEnvelope<REQ, RES> requestEnvelope;

		public SimpleAuthorizationGatewayTestOperation(SimpleAuthenticatedRequestEnvelope<REQ, RES> requestEnvelope) {
			this.requestEnvelope = requestEnvelope;
		}

		@Override
		protected Boolean doExecute(AuthorizationGatewayTestContext<SimpleAuthorizationGateway> context) {
			SimpleAuthorizationGateway gateway = context.authorizationGateway();
			return gateway.authorized(requestEnvelope);
		}
		
	}
	
}
