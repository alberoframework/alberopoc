package com.alberoframework.sample.issuetracker.service.app.query;

import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.hypermedia.HypermediaObjectResource;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerEnvelopeQueryHandler;
import com.alberoframework.sample.issuetracker.service.app.entity.RootAppEntity;
import com.alberoframework.sample.issuetracker.service.core.query.UserQuery;

@Component
public class RootAppQueryHandler extends AbstractIssueTrackerEnvelopeQueryHandler<RootAppQuery, HypermediaObjectResource<RootAppEntity>> {

	@Override
	protected HypermediaObjectResource<RootAppEntity> doHandle(
			SimpleAuthenticatedRequestEnvelope<RootAppQuery, HypermediaObjectResource<RootAppEntity>> requestEnvelope,
			ContextualizedQueryGateway queryGateway) {
		
		HypermediaObjectResource<RootAppEntity> resource = 
				 requestEnvelope.userId()
								.flatMap(uid -> queryGateway.handle(new UserQuery(uid)))
								.map(u -> new HypermediaObjectResource<>(new RootAppEntity(u.getUsername())))
								.orElse(new HypermediaObjectResource<>(new RootAppEntity()))
								.withLink("projectCollection", new ProjectAppCollectionQuery());

		return resource;
	}
	
}
