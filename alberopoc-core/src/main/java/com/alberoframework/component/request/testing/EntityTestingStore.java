package com.alberoframework.component.request.testing;

import java.util.Map;
import java.util.Set;

import com.alberoframework.domain.entity.contract.Entity;

public interface EntityTestingStore {

	void initializeState(Set<Entity<?>> initialState);
	
	Set<Entity<?>> getCurrentState();
	
	Map<String, Object> getDependencies();
}
