package com.alberoframework.sample.issuetracker.endpoint.authorization;

import static com.alberoframework.sample.issuetracker.service.core.query.PredicateQueries.*;

import java.util.function.Function;

import com.alberoframework.component.authorization.gateway.SimpleAuthorizationGateway;
import com.alberoframework.component.authorization.gateway.SimpleAuthorizationGatewayImpl;
import com.alberoframework.component.query.contract.PredicateQuery;
import com.alberoframework.component.query.gateway.SimpleAuthenticatedQueryGateway;
import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.sample.issuetracker.service.core.command.*;
import com.alberoframework.sample.issuetracker.service.core.query.PredicateQueries;
import com.alberoframework.sample.issuetracker.service.core.value.IssueStatusValue;

public class IssueTrackerAuthorization {

	public static SimpleAuthorizationGateway createAuthorizationGatewayWithSpecifications(
			SimpleAuthenticatedQueryGateway queryGateway) {
		SimpleAuthorizationGateway authorizationGateway = new SimpleAuthorizationGatewayImpl(
				queryGateway);

		authorizationGateway.registerAuthorizationSpecification(
			AssignIssueCommand.class,
			env -> authenticated(env, userId ->
				isIssueInStatus(env.getRequest().getIssueId(), IssueStatusValue.TODO)
				.and(
					isAdmin(userId)
					.or(isMemberOfProject(userId, env.getRequest().getProjectId()))
				)
			)
		);

		authorizationGateway.registerAuthorizationSpecification(
			CreateCommentCommand.class,
			env -> authenticated(env)
		);

		authorizationGateway.registerAuthorizationSpecification(
			CreateIssueCategoryCommand.class,
			env -> authenticated(env, userId -> isAdmin(userId))
		);

		authorizationGateway.registerAuthorizationSpecification(
			CreateIssueCommand.class,
			env -> authenticated(env, userId -> isAdmin(userId)
				.or(isMemberOfProject(userId, env.getRequest().getProjectId()))
			)
		);

		authorizationGateway.registerAuthorizationSpecification(
			CreateUserCommand.class,
			env -> authenticated(env, userId -> isAdmin(userId))
		);

		authorizationGateway.registerAuthorizationSpecification(
			UpdateIssueCommand.class,
			env -> authenticated(env, userId -> isAdmin(userId)
				.or(isMemberOfProject(userId, env.getRequest().getProjectId())))
		);

		authorizationGateway.registerAuthorizationSpecification(
			CreateProjectCommand.class,
			env -> authenticated(env, PredicateQueries::isAdmin)
		);

		authorizationGateway.registerAuthorizationSpecification(
			CloseIssueCommand.class,
			env -> authenticated(env, userId ->
				isIssueInStatus(env.getRequest().getIssueId(), IssueStatusValue.IN_PROGRESS)
				.and(
					isAdmin(userId)
						.or(isAssignee(userId, env.getRequest().getIssueId())
						.or(isManager(userId, env.getRequest().getProjectId())))
				)
			)
		);

		authorizationGateway.registerAuthorizationSpecification(
			UpdateProjectCommand.class,
			env -> authenticated(env, userId ->
				isAdmin(userId)
					.or(isManager(userId, env.getRequest().getProjectId()))
			)
		);

		return authorizationGateway;
	}

	public static <REQ extends Request<RES>, RES> PredicateQuery authenticated(SimpleAuthenticatedRequestEnvelope<REQ, RES> env) {
		return authenticated(env, x -> PredicateQueries.truePredicateQuery());
	}
	public static <REQ extends Request<RES>, RES> PredicateQuery authenticated(SimpleAuthenticatedRequestEnvelope<REQ, RES> env, Function<String, PredicateQuery> authorizations) {
		return env.userId()
			.map(authorizations::apply)
			.orElse(PredicateQueries.falsePredicateQuery());
	}
}
