package com.alberoframework.testing.bdd.port.old;

import java.util.List;

public interface Registry extends RegistryReader, RegistryWriter {

	public List<Object> asList();
	
}
