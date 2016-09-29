package com.alberoframework.component.request.testing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import com.alberoframework.core.reflection.Reflection;
import com.alberoframework.core.string.Strings;
import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.CrudRepository;
import com.google.common.collect.Sets;

public class RepositoryEntityTestingStore implements EntityTestingStore {

	private Set<CrudRepository<? extends Entity<?>, ?>> repositories;

	public RepositoryEntityTestingStore(Set<CrudRepository<? extends Entity<?>, ?>> repositories) {
		this.repositories = repositories;
	}

	@Override
	public void initializeState(Set<Entity<?>> initialState) {
		//Fill repositories with entities
		for (CrudRepository<? extends Entity<?>, ?> repository : repositories) {
//					Class<?> repositoryEntityType = GenericTypeResolver.resolveTypeArgument(repository.getClass(), Repository.class);
			Class<?> repositoryEntityType = Reflection.resolveGenericParameter(repository.getClass());
			for (Entity<?> entity : initialState) { //Test if its the exact same type, not a subclass as we dont support saving subclasses in repositoties
				if (entity.getClass().equals(repositoryEntityType))
					((CrudRepository<Entity<?>, ?>) repository).save(entity);
			}
		}
		
		if (!Objects.equals(initialState, getCurrentState())) {
			throw new IllegalStateException("Error initializing RequestHandlerTestContext, there are entities who are not managed by the repositoties the command handler uses or that a repositoty is missing, initial state: " + initialState + " current state: " + getCurrentState());
		}
	}

	@Override
	public Set<Entity<?>> getCurrentState() {
		Set<Entity<?>> entities = new HashSet<Entity<?>>();
		for (CrudRepository<? extends Entity<?>, ?> repository : repositories) {
			entities.addAll(Sets.newCopyOnWriteArraySet(repository.findAll()));
		}
		return entities;
	}

	@Override
	public Map<String, Object> getDependencies() {
		Map<String, Object> dependencies = new HashMap<>();
		for (CrudRepository<? extends Entity<?>, ?> repository : repositories) {
			Class<?> entityType = Reflection.resolveGenericParameter(repository.getClass());
			String entityName = entityType.getSimpleName();
			if (entityName.endsWith("Entity")) {
				entityName = entityName.substring(0, entityName.length() - 6);
			}
			String repositoryDependencyName = Strings.lowerCaseFirstLetter(entityName) + "Repository";
			dependencies.put(repositoryDependencyName, repository);
		}
		return dependencies;
	}

	
	
}
