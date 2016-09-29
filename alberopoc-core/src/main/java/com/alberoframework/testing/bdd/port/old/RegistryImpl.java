package com.alberoframework.testing.bdd.port.old;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegistryImpl implements Registry {

	private final Map<RegistryElementRef<?>, Object> registry = new HashMap<RegistryElementRef<?>, Object>();
	
  @SuppressWarnings("unchecked")
	public <T> void put(T element) {
  	Class<T> clazz = (Class<T>) element.getClass();
  	set(new RegistryElementRef<T>(clazz), element);
  }
  
  @SuppressWarnings("unchecked")
	public <T> void put(String name, T element) {
  	Class<T> clazz = (Class<T>) element.getClass();
  	set(new RegistryElementRef<T>(clazz, name), element);
  }
 
	public <T> T get(Class<T> clazz) {
      return get(new RegistryElementRef<T>(clazz));
  }
  
	public <T> T get(Class<T> clazz, String name) {
      return get(new RegistryElementRef<T>(clazz, name));
  }
  
  @SuppressWarnings("unchecked")
	private <T> T get(RegistryElementRef<T> ref) {
     return (T) registry.get(ref);
  }
  
  private <T> void set(RegistryElementRef<T> ref, T element) {
    registry.put(ref, element);
  }

  @Override
  public List<Object> asList() {
  	return new ArrayList<Object>(registry.values());
  }
	
  
  public boolean isEmpty() {
  	return registry.isEmpty();
  }
	
}
