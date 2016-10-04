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
				issueIsInStatus(env.getRequest().getProjectId(), env.getRequest().getIssueId(), IssueStatusValue.TODO)
				.and(
					userIsAdmin(userId)
					.or(userIsMemberOfProject(userId, env.getRequest().getProjectId()))
				)
			)
		);

		authorizationGateway.registerAuthorizationSpecification(
			CreateCommentCommand.class,
			env -> authenticated(env)
		);

		authorizationGateway.registerAuthorizationSpecification(
			CreateIssueCommand.class,
			env -> authenticated(env, userId -> userIsAdmin(userId)
				.or(userIsMemberOfProject(userId, env.getRequest().getProjectId()))
			)
		);

		authorizationGateway.registerAuthorizationSpecification(
			CreateUserCommand.class,
			env -> authenticated(env, userId -> userIsAdmin(userId))
		);

		authorizationGateway.registerAuthorizationSpecification(
			UpdateIssueCommand.class,
			env -> authenticated(env, userId -> userIsAdmin(userId)
				.or(userIsMemberOfProject(userId, env.getRequest().getProjectId())))
		);

		authorizationGateway.registerAuthorizationSpecification(
			CreateProjectCommand.class,
			env -> authenticated(env, PredicateQueries::userIsAdmin)
		);

		authorizationGateway.registerAuthorizationSpecification(
			CloseIssueCommand.class,
			env -> authenticated(env, userId ->
				issueIsInStatus(env.getRequest().getProjectId(), env.getRequest().getIssueId(), IssueStatusValue.IN_PROGRESS)
				.and(
					userIsAdmin(userId)
						.or(userIsAssigneeOfIssue(env.getRequest().getProjectId(), env.getRequest().getIssueId(), userId)
						.or(userIsManagerOfProject(env.getRequest().getProjectId(), userId)))
				)
			)
		);

		authorizationGateway.registerAuthorizationSpecification(
			UpdateProjectCommand.class,
			env -> authenticated(env, userId ->
				userIsAdmin(userId)
					.or(userIsManagerOfProject(userId, env.getRequest().getProjectId()))
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
