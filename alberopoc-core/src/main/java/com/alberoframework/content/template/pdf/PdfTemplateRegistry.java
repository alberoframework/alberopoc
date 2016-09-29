package com.alberoframework.content.template.pdf;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class PdfTemplateRegistry {

	private Map<Class<?>, Object> templateMap = new HashMap<>();
	
	public <T> void registerTemplate(Class<T> type, PdfTemplate<T> template) {
		templateMap.put(type, template);
	}

	@SuppressWarnings("unchecked")
	public Optional<PdfTemplate<Object>> template(Class<?> type) {
		return Optional.ofNullable((PdfTemplate<Object>) templateMap.get(type));
	}
	
}
