package com.alberoframework.testing.bdd.port;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class PortRegistryImpl implements PortRegistry {

	private final Map<String, Object> registry = new HashMap<String, Object>();
	
	@Override
	public Object get(String name) {
		return registry.get(name);
	}
  
	@Override
	public void put(String name, Object element) {
		registry.put(name, element);
	}
	
	@Override
	public List<Object> asList() {
		return new ArrayList<Object>(registry.values());
	}
	
	@Override
	public Map<String, Object> asMap() {
		return ImmutableMap.copyOf(registry);
	}
	
	@Override	
	public boolean isEmpty() {
		return registry.isEmpty();
	}
	
}
