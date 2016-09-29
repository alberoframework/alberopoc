package com.alberoframework.component.request.testing;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.repository.support.MongoRepositoryFactory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.core.support.AbstractRepositoryMetadata;

import com.alberoframework.domain.entity.contract.Entity;
import com.github.fakemongo.Fongo;
import com.mongodb.Mongo;

public class MongoDBSpringDataEntityTestingStore extends SpringDataEntityTestingStore {

	
	public MongoDBSpringDataEntityTestingStore(
			Set<Class<? extends CrudRepository<? extends Entity<?>, ?>>> repositoryTypes) {
		super(repositoryTypes);
	}

	@Override
	protected Map<Class<? extends Entity<?>>, CrudRepository<? extends Entity<?>, ?>> createRepositories(Set<Class<? extends CrudRepository<? extends Entity<?>, ?>>> repositoryTypes) {
		
		String dbName="inmemory-test-db";
		
	 	Fongo fongo = new Fongo(dbName);
	 	
	 	Mongo mongo = fongo.getMongo();
	 	
		MongoTemplate template = new MongoTemplate(mongo, dbName);
		
		MongoRepositoryFactory factory = new MongoRepositoryFactory(template);
		
		Map<Class<? extends Entity<?>>, CrudRepository<? extends Entity<?>, ?>> repositories = new HashMap<>();
		
		for (Class<? extends CrudRepository<? extends Entity<?>, ?>> reposirotyType : repositoryTypes) {
			RepositoryMetadata metadata = AbstractRepositoryMetadata.getMetadata(reposirotyType);
//			repositories.put(reposirotyTypeEntry.getKey(), factory.getRepository(reposirotyTypeEntry.getValue()));
			if (Entity.class.isAssignableFrom(metadata.getDomainType())) {
				repositories.put((Class<? extends Entity<?>>) metadata.getDomainType(), factory.getRepository(reposirotyType));
			}
			
		}
		return repositories;
	}
	
	
}
