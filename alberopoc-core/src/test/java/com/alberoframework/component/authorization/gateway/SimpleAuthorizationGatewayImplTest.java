package com.alberoframework.component.authorization.gateway;

import static com.alberoframework.component.authorization.gateway.SimpleAuthorizationGatewayImplStubs.*;

import org.junit.Test;

import com.alberoframework.component.authorization.gateway.SimpleAuthorizationGateway;
import com.alberoframework.component.authorization.gateway.SimpleAuthorizationGatewayImpl;
import com.alberoframework.component.authorization.testing.AbstractSimpleAuthorizationGatewayTestSupport;
import com.alberoframework.component.query.contract.FalsePredicateQuery;
import com.alberoframework.component.query.contract.PredicateQuery;

public class SimpleAuthorizationGatewayImplTest extends AbstractSimpleAuthorizationGatewayTestSupport {

	@Test
	public TestSpecification testAuthorized() {
		
		return   given(
					queryStubs(
						//User 3 is admin, user 2 has write permissions, user 1 has neither and something is in approval
						queryStub(queryWithSystemUser(new SomethingIsInApprovalAndCannotBeModifiedPredicateQuery()), true),
						queryStub(queryWithSystemUser(new IsAdminPredicateQuery(USER_ID_1)), false),
						queryStub(queryWithSystemUser(new IsAdminPredicateQuery(USER_ID_2)), false),
						queryStub(queryWithSystemUser(new IsAdminPredicateQuery(USER_ID_3)), true),
						queryStub(queryWithSystemUser(new HasWritePermissionsPredicateQuery(USER_ID_1)), false),
						queryStub(queryWithSystemUser(new HasWritePermissionsPredicateQuery(USER_ID_2)), true),
						queryStub(queryWithSystemUser(new HasWritePermissionsPredicateQuery(USER_ID_3)), false)
					)
				 )
				.when(authorized(command(new MyCommand1()))).then(false) //denied because its unauthenticated
				.when(authorized(command(new MyCommand1(), USER_ID_1))).then(true) //ok, any authenticated request is allowed
				.when(authorized(command(new MyCommand2()))).then(false) //denied because its unauthenticated
				.when(authorized(command(new MyCommand2(), USER_ID_1))).then(false) //deined: neither admin nor right permission
				.when(authorized(command(new MyCommand2(), USER_ID_2))).then(false) //denied: something is in approval
				.when(authorized(command(new MyCommand2(), USER_ID_3))).then(true)  //ok, is admin
				.when(authorized(query(new MyQuery1(), USER_ID_2))).then(false) //denied even with write permissions
				.when(authorized(query(new MyQuery1(), USER_ID_3))).then(false) //denied even for admin
				.when(authorized(queryWithSystemUser(new MyQuery1()))).then(true) //ok for system
				.when(authorized(query(new MyQuery2(), USER_ID_3))).then(true) //ok for any user
				.when(authorized(query(new MyQuery2()))).then(true); //ok even for non authenticated users
	}
	
	
	@Override
	protected SimpleAuthorizationGateway authorizationGateway() {
		SimpleAuthorizationGateway authorizationGateway = new SimpleAuthorizationGatewayImpl();
		
		//only authenticated requests can execute this command
		authorizationGateway.registerAuthorizationPredicate(MyCommand1.class, env -> env.authenticated()); 
		
		//MyCommand2 can be send only if nothing is in approval and the user has right permissions, otherwise only admin can send it, admin can always send it
		authorizationGateway.registerAuthorizationSpecification(
				MyCommand2.class, env -> env.userId()
										.map(uid -> (PredicateQuery) new IsAdminPredicateQuery(uid).or(new SomethingIsInApprovalAndCannotBeModifiedPredicateQuery().not().and(new HasWritePermissionsPredicateQuery(uid))))
										.orElseGet(() -> new FalsePredicateQuery())); //denied if not authenticated
		
		
		//always denied, only system can execute this
		authorizationGateway.registerAuthorizationPredicate(MyQuery1.class, env -> false); 
		
		//MyQuery2 is not registered, default is true even for unauthenticated requests
		
		return authorizationGateway;
	}

}
