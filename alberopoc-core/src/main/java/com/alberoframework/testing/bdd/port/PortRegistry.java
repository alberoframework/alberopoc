package com.alberoframework.testing.bdd.port;

import java.util.List;
import java.util.Map;


public interface PortRegistry {

	public Object get(String name);
	
	public void put(String name, Object element);
	
	public List<Object> asList();
	
	public Map<String, Object> asMap();
	
	public boolean isEmpty();
	
}
