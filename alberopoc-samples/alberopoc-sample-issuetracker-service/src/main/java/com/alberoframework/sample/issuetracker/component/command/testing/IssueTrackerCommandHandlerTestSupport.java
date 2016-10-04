package com.alberoframework.sample.issuetracker.component.command.testing;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.testing.SimpleAuthenticatedCommandHandlerSpringDataTestSupport;
import com.alberoframework.component.request.testing.MongoDBSpringDataEntityTestingStore;
import com.alberoframework.component.request.testing.SpringDataEntityTestingStore;
import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.sample.issuetracker.component.command.handler.IssueTrackerCommandHandler;
import com.alberoframework.sample.issuetracker.domain.repository.IssueTrackerEntityRepository;

public abstract class IssueTrackerCommandHandlerTestSupport<CH extends IssueTrackerCommandHandler<C, R>, C extends Command<R>, R> extends SimpleAuthenticatedCommandHandlerSpringDataTestSupport<CH, C, R, MongoDBSpringDataEntityTestingStore> {

	@Override
	protected Class<? extends MongoDBSpringDataEntityTestingStore> entityStoreType() {
		return MongoDBSpringDataEntityTestingStore.class;
	}
	
	@Override
	protected Set<Class<? extends CrudRepository<? extends Entity<?>, ?>>> repositoryTypes() {
		return SpringDataEntityTestingStore.scanRepositories("com.alberoframework.sample.issuetracker", IssueTrackerEntityRepository.class);
	}
	
}
