package com.alberoframework.component.request.testing;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.reflections.Reflections;
import org.springframework.data.repository.CrudRepository;

import com.alberoframework.core.string.Strings;
import com.alberoframework.domain.entity.contract.Entity;
import com.google.common.collect.Sets;

public abstract class SpringDataEntityTestingStore implements EntityTestingStore {

	public static <ES extends SpringDataEntityTestingStore> ES createStore(Class<ES> entityStoreType, Set<Class<? extends CrudRepository<? extends Entity<?>, ?>>> repositoryTypes) {
		ES entityStore;
		try {
			entityStore = (ES) entityStoreType.getConstructor(Set.class).newInstance(repositoryTypes);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			throw new IllegalArgumentException("Error creating Spring Data Entity Store ", e);
		}
		return entityStore;
	}
	
	//TODO: Recheck generics, try to remove the cast in the first line 
	public static Set<Class<? extends CrudRepository<? extends Entity<?>, ?>>> scanRepositories(String basePackage, Class<? extends CrudRepository> repositorySuperType) {
		Class<? extends CrudRepository<? extends Entity<?>, ?>> repositoryEntitySuperType = (Class<? extends CrudRepository<? extends Entity<?>, ?>>) repositorySuperType;
		Set<Class<? extends CrudRepository<? extends Entity<?>, ?>>> repositoryTypesSet = new HashSet<>();
		final Reflections reflections = new Reflections(basePackage);
		repositoryTypesSet.addAll(reflections.getSubTypesOf(repositoryEntitySuperType));
		return repositoryTypesSet;
	}
	
	
	private Map<Class<? extends Entity<?>>, CrudRepository<? extends Entity<?>, ?>> repositories;

	public <E extends Entity<ID>, ID extends Serializable> SpringDataEntityTestingStore(Set<Class<? extends CrudRepository<? extends Entity<?>, ?>>> repositoryTypes) {
		this.repositories = createRepositories(repositoryTypes);
	}
	
	protected abstract Map<Class<? extends Entity<?>>, CrudRepository<? extends Entity<?>, ?>> createRepositories(Set<Class<? extends CrudRepository<? extends Entity<?>, ?>>> repositoryTypes);

	@Override
	public void initializeState(Set<Entity<?>> initialState) {
		//Fill repositories with entities
		for (Map.Entry<Class<? extends Entity<?>>, CrudRepository<? extends Entity<?>, ?>> repositoryEntry : repositories.entrySet()) {
//					Class<?> repositoryEntityType = GenericTypeResolver.resolveTypeArgument(repository.getClass(), Repository.class);
//			Class<?> repositoryEntityType = Reflection.resolveGenericParameter(repository.getClass());
			for (Entity<?> entity : initialState) { //Test if its the exact same type, not a subclass as we dont support saving subclasses in repositoties
				if (entity.getClass().equals(repositoryEntry.getKey()))
					((CrudRepository<Entity<?>, ?>) repositoryEntry.getValue()).save(entity);
			}
		}
		
		if (!Objects.equals(initialState, getCurrentState())) {
			throw new IllegalStateException("Error initializing RequestHandlerTestContext, there are entities who are not managed by the repositoties the command handler uses or that a repositoty is missing, initial state: " + initialState + " current state: " + getCurrentState());
		}
	}

	@Override
	public Set<Entity<?>> getCurrentState() {
		Set<Entity<?>> entities = new HashSet<Entity<?>>();
		for (CrudRepository<? extends Entity<?>, ?> repository : repositories.values()) {
			entities.addAll(Sets.newCopyOnWriteArraySet(repository.findAll()));
		}
		return entities;
	}

	@Override
	public Map<String, Object> getDependencies() {
		Map<String, Object> dependencies = new HashMap<>();
		for (Map.Entry<Class<? extends Entity<?>>, CrudRepository<? extends Entity<?>, ?>> repositoryEntry : repositories.entrySet()) {
			Class<?> entityType = repositoryEntry.getKey();
			String entityName = entityType.getSimpleName();
			if (entityName.endsWith("Entity")) {
				entityName = entityName.substring(0, entityName.length() - 6);
			}
			String repositoryDependencyName = Strings.lowerCaseFirstLetter(entityName) + "Repository";
			dependencies.put(repositoryDependencyName, repositoryEntry.getValue());
		}
		return dependencies;
	}

	
	
}
