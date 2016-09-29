package com.alberoframework.sample.issuetracker.service.app.query;

import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.hypermedia.HypermediaObjectResource;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerEnvelopeQueryHandler;
import com.alberoframework.sample.issuetracker.service.app.entity.RootAppEntity;
import com.alberoframework.sample.issuetracker.service.core.command.CreateIssueCategoryCommand;
import com.alberoframework.sample.issuetracker.service.core.command.CreateProjectCommand;
import com.alberoframework.sample.issuetracker.service.core.command.CreateUserCommand;
import com.alberoframework.sample.issuetracker.service.core.entity.UserEntity;
import com.alberoframework.sample.issuetracker.service.core.query.UserQuery;

import java.util.Optional;

@Component
public class RootAppQueryHandler extends AbstractIssueTrackerEnvelopeQueryHandler<RootAppQuery, HypermediaObjectResource<RootAppEntity>> {

	@Override
	protected HypermediaObjectResource<RootAppEntity> doHandle(
			SimpleAuthenticatedRequestEnvelope<RootAppQuery, HypermediaObjectResource<RootAppEntity>> requestEnvelope,
			ContextualizedQueryGateway queryGateway) {
		
		HypermediaObjectResource<RootAppEntity> resource = requestEnvelope.userId()
			.flatMap(uid -> queryGateway.handle(new UserQuery(uid)))
			.map(u -> new HypermediaObjectResource<>(new RootAppEntity(u.getUsername())))
			.orElse(new HypermediaObjectResource<>(new RootAppEntity()));

		return resource
			.withLink("create-category", new CreateIssueCategoryCommand())
			.withLink("projects", new ProjectCollectionAppQuery())
			.withLink("create-user", new CreateUserCommand())
			.withLink("dashboard", new UserProjectCollectionAppQuery());
	}
	
}
