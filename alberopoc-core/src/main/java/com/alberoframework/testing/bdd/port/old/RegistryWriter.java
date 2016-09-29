package com.alberoframework.testing.bdd.port.old;

public interface RegistryWriter {

	public <T> void put(T element);
  
	public <T> void put(String name, T element);
	
}
