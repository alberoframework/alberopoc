package com.alberoframework.sample.issuetracker.service.core.query;

import java.util.Optional;

import com.alberoframework.component.query.contract.FalsePredicateQuery;
import com.alberoframework.component.query.contract.PredicateQuery;
import com.alberoframework.component.query.contract.TruePredicateQuery;
import com.alberoframework.sample.issuetracker.service.core.value.IssueStatusValue;
import com.alberoframework.sample.issuetracker.service.core.value.MembershipTypeValue;

public class PredicateQueries {

	public static PredicateQuery isAdmin(String userId) {
		return new UserAdminPredicateQuery(userId);
	}

	public static PredicateQuery isAssignee(String userId, String issueId) {
		return new UserIsAssigneePredicateQuery(userId, issueId);
	}

	public static PredicateQuery isManager(String userId, String projectId) {
		return isMemberOfProject(userId, MembershipTypeValue.MANAGER, projectId);
	}

	public static PredicateQuery isMemberOfProject(String userId, String projectId) {
		return isMemberOfProject(userId, null, projectId);
	}
	public static PredicateQuery isMemberOfProject(String userId, MembershipTypeValue membership, String projectId) {
		return new UserIsMemberOfIssueProjectPredicateQuery(userId, Optional.ofNullable(membership), projectId);
	}

	public static PredicateQuery isIssueInStatus(String issueId, IssueStatusValue status) {
		return new IssueInStatusPredicateQuery(issueId, status);
	}

	public static PredicateQuery falsePredicateQuery() {
		return new FalsePredicateQuery();
	}
	
	public static PredicateQuery truePredicateQuery() {
		return new TruePredicateQuery();
	}

}
