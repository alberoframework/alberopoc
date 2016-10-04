package com.alberoframework.sample.issuetracker.service.core.query;

import java.util.stream.Collectors;

import org.assertj.core.util.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.core.iterable.Iterables;
import com.alberoframework.sample.issuetracker.component.query.handler.AbstractIssueTrackerEnvelopeQueryHandler;
import com.alberoframework.sample.issuetracker.service.core.entity.ProjectEntity;
import com.alberoframework.sample.issuetracker.service.core.repository.ProjectRepository;

import lombok.Setter;

@Setter
@Component
public class ProjectCollectionQueryHandler extends AbstractIssueTrackerEnvelopeQueryHandler<ProjectCollectionQuery, Iterable<ProjectEntity>> {
    
    @Autowired
    private ProjectRepository projectRepository;

    @Override
    protected Iterable<ProjectEntity> doHandle(
    		SimpleAuthenticatedRequestEnvelope<ProjectCollectionQuery, Iterable<ProjectEntity>> env,
    		ContextualizedQueryGateway queryGateway) {
    	
    	return env.userId()
    			  .map(uid ->  queryGateway.handle(PredicateQueries.userIsAdmin(uid))
		    				   ? projectRepository.findAll() 
		    				   : Iterables.stream(projectRepository.findAll())
											   .filter(p -> p.userIsMember(uid))
											   .collect(Collectors.toList())
    			  )
    			  .orElse(Lists.newArrayList());
    }
    
}
