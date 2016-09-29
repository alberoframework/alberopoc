package com.alberoframework.component.request.testing;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.alberoframework.core.validation.Validation;
import com.alberoframework.domain.entity.contract.Entity;

public class EmptyEntityTestingStore implements EntityTestingStore {

	@Override
	public void initializeState(Set<Entity<?>> initialState) {
		Validation.validate(initialState.isEmpty(), IllegalArgumentException::new, "You shouldnt initialize an Empty Entity Store, if you need state use a non empty store");
	}

	@Override
	public Set<Entity<?>> getCurrentState() {
		return new HashSet<Entity<?>>();
	}

	@Override
	public Map<String, Object> getDependencies() {
		Map<String, Object> dependencies = new HashMap<>();
		return dependencies;
	}

	
	
}
