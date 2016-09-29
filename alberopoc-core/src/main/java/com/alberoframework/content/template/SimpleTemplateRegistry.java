package com.alberoframework.content.template;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class SimpleTemplateRegistry<T> {

	private Map<Class<?>, T> templateMap = new HashMap<>();
	
	public void registerTemplate(Class<?> type, T template) {
		templateMap.put(type, template);
	}

	public Optional<T> template(Class<?> type) {
		return Optional.ofNullable(templateMap.get(type));
	}
}
