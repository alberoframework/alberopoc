package com.alberoframework.content.conversion.converter;

import java.util.Optional;

import com.alberoframework.content.template.SimpleTemplateRegistry;
import com.alberoframework.core.validation.Validation;

public abstract class AbstractSimpleTemplateConverterToContent<T> implements ObjectToContentConverter {

	private SimpleTemplateRegistry<T> templateRegistry;

	public AbstractSimpleTemplateConverterToContent(SimpleTemplateRegistry<T> templateRegistry) {
		this.templateRegistry = templateRegistry;
	}
	
	@Override
	public byte[] convert(Object object) {
		Optional<T> templateOpt = templateRegistry.template(object.getClass());
		Validation.validate(templateOpt.isPresent(), IllegalArgumentException::new, "Cannot convert object, template not registered for type " + object.getClass());
		return bind(object, templateOpt.get());
	}
	
	
	protected abstract byte[] bind(Object object, T template);
	
}
