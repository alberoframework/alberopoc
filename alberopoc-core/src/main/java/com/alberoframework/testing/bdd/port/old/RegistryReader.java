package com.alberoframework.testing.bdd.port.old;

public interface RegistryReader {

	public <T> T get(Class<T> clazz);

	public <T> T get(Class<T> clazz, String name);
	
	public boolean isEmpty();
}
