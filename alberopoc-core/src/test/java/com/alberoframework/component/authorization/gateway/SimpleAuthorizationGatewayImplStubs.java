package com.alberoframework.component.authorization.gateway;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.query.contract.AbstractPredicateQuery;
import com.alberoframework.component.query.contract.Query;
import com.alberoframework.lang.VoidUnit;

public class SimpleAuthorizationGatewayImplStubs {

	public static String USER_ID_1 = "user1";
	public static String USER_ID_2 = "user2";
	public static String USER_ID_3 = "user3";
	
	public static class MyCommand1 implements Command<VoidUnit> {
		
	}
	
	public static class MyCommand2 implements Command<String> {
		
	}
	
	public static class MyQuery1 implements Query<String> {
		
	}
	
	public static class MyQuery2 implements Query<String> {
		
	}
	
	
	public static class IsAdminPredicateQuery extends AbstractPredicateQuery {
		private String userId;

		public IsAdminPredicateQuery(String userId) {
			this.userId = userId;
		}
		
		public String getUserId() {
			return userId;
		}
	}
	
	public static class HasWritePermissionsPredicateQuery extends AbstractPredicateQuery {
		private String userId;

		public HasWritePermissionsPredicateQuery(String userId) {
			this.userId = userId;
		}
		
		public String getUserId() {
			return userId;
		}
	}
	
	public static class SomethingIsInApprovalAndCannotBeModifiedPredicateQuery extends AbstractPredicateQuery {
		
	}
	
}
