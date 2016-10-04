package com.alberoframework.sample.issuetracker.component.query.testing;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.testing.SimpleAuthenticatedQueryHandlerSpringDataTestSupport;
import com.alberoframework.component.request.testing.MongoDBSpringDataEntityTestingStore;
import com.alberoframework.component.request.testing.SpringDataEntityTestingStore;
import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.sample.issuetracker.component.query.handler.IssueTrackerQueryHandler;
import com.alberoframework.sample.issuetracker.domain.repository.IssueTrackerEntityRepository;

public abstract class IssueTrackerQueryHandlerTestSupport<QH extends IssueTrackerQueryHandler<Q, R>, Q extends Query<R>, R> extends SimpleAuthenticatedQueryHandlerSpringDataTestSupport<QH, Q, R, MongoDBSpringDataEntityTestingStore> {

	@Override
	protected Class<? extends MongoDBSpringDataEntityTestingStore> entityStoreType() {
		return MongoDBSpringDataEntityTestingStore.class;
	}
	
	@Override
	protected Set<Class<? extends CrudRepository<? extends Entity<?>, ?>>> repositoryTypes() {
		return SpringDataEntityTestingStore.scanRepositories("com.alberoframework.sample.issuetracker", IssueTrackerEntityRepository.class);
	}
	
}
