package com.alberoframework.sample.issuetracker.service.core.query;

import java.util.Optional;

import com.alberoframework.component.query.contract.FalsePredicateQuery;
import com.alberoframework.component.query.contract.PredicateQuery;
import com.alberoframework.component.query.contract.TruePredicateQuery;
import com.alberoframework.sample.issuetracker.service.core.value.IssueStatusValue;
import com.alberoframework.sample.issuetracker.service.core.value.ProjectMembershipTypeValue;

public class PredicateQueries {

	public static PredicateQuery userIsAdmin(String userId) {
		return new UserAdminPredicateQuery(userId);
	}

	public static PredicateQuery userIsAssigneeOfIssue(String userId, String projectId, String issueId) {
		return new UserIsAssigneeOfIssuePredicateQuery(userId, projectId, issueId);
	}

	public static PredicateQuery userIsManagerOfProject(String userId, String projectId) {
		return userIsMemberOfProject(userId, projectId, ProjectMembershipTypeValue.MANAGER);
	}

	public static PredicateQuery userIsMemberOfProject(String userId, String projectId) {
		return userIsMemberOfProject(userId, projectId, null);
	}
	public static PredicateQuery userIsMemberOfProject(String userId, String projectId, ProjectMembershipTypeValue membership) {
		return new UserIsMemberOfProjectPredicateQuery(userId, projectId, Optional.ofNullable(membership));
	}

	public static PredicateQuery issueIsInStatus(String projectId, String issueId, IssueStatusValue status) {
		return new IssueInStatusPredicateQuery(projectId, issueId, status);
	}

	public static PredicateQuery falsePredicateQuery() {
		return new FalsePredicateQuery();
	}
	
	public static PredicateQuery truePredicateQuery() {
		return new TruePredicateQuery();
	}

}
