package com.alberoframework.domain.repository.persistence.memory;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.alberoframework.domain.entity.contract.Entity;
import com.alberoframework.domain.repository.contract.ReadRepository;
import com.alberoframework.lang.object.BaseObject;
import com.google.common.collect.ImmutableSet;

public abstract class AbstractInMemoryReadRepository<E extends Entity<ID>, ID> implements ReadRepository<E, ID> {

	private Map<ID, E> db = new ConcurrentHashMap<ID, E>();
	
	@Override
	public E findById(ID id) {
		return db.get(id);
	}

	@Override
	public Iterable<E> findAll() {
		return ImmutableSet.copyOf(db.values());
	}

	protected <S extends E> S persist(S entity) {
		db.put(entity.identity(), entity);
		return entity;
	}
	
	public void remove(E entity) {
		remove(entity.identity());
	}
	
	public void remove(ID id) {
		db.remove(id);
	}

	protected abstract E copy(E from);

	protected E findOneByProperty(String propertyName, Object propertyValue) {
		return findOne(findByProperty(propertyName, propertyValue));
	}
	
	protected Iterable<E> findByProperty(String propertyName, Object propertyValue) {
		return findByFilterMatchAll(new EntityPropertyFilter(propertyName, propertyValue));
	}
	
	protected E findOneByFilterMatchAll(EntityPropertyFilter ... filters) {
		return findOne(findByFilters(true, filters));
	}
	
	protected Iterable<E> findByFilterMatchAll(EntityPropertyFilter ... filters) {
		return findByFilters(true, filters);
	}
	
	protected E findByOneFilterMatchOne(EntityPropertyFilter ... filters) {
		return findOne(findByFilters(false, filters));
	}
	
	protected Iterable<E> findByFilterMatchOne(EntityPropertyFilter ... filters) {
		return findByFilters(false, filters);
	}
	
	private E findOne(Iterable<E> results) {
		Iterator<E> iterator = results.iterator();
		if (!iterator.hasNext())
			return null;
		E first = iterator.next();
		if (iterator.hasNext()) 
			return null; //throw exception??
		return first;
	}
	
	private Iterable<E> findByFilters(boolean matchesAll, EntityPropertyFilter ... filters) {
		List<E> results = new ArrayList<E>();
		if (filters.length == 0) return results;
		for (E entity : findAll()) {
			boolean matchesFilters = matchesAll;
			for (EntityPropertyFilter filter : filters) {
				if (matchesAll && !matchesFilter(entity, filter)) {
					matchesFilters = false;
					break;
				}
				else if (!matchesAll && matchesFilter(entity, filter)) {
					matchesFilters = true;
					break;
				}
			}
			if (matchesFilters)
				results.add(entity);
		}
		return results;
	}
	
	private boolean matchesFilter(E entity, EntityPropertyFilter filter) {
		Object fieldValue = null;
		try {
			fieldValue = FieldUtils.readField(entity, filter.getPropertyName(), true);
		}
		catch (Exception e) {
			//eat exception
		}
		return Objects.equals(fieldValue, filter.getPropertyValue());
	}
	
	public static class EntityPropertyFilter extends BaseObject {
		private String propertyName;
		private Object propertyValue;
		
		public EntityPropertyFilter(String propertyName, Object propertyValue) {
			this.propertyName = propertyName;
			this.propertyValue = propertyValue;
		}

		public String getPropertyName() {
			return propertyName;
		}
	
		public Object getPropertyValue() {
			return propertyValue;
		}
		
	}
	
}
