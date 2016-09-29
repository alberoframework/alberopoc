package com.alberoframework.type.conversion.testing;

import java.util.HashMap;
import java.util.Map;

import com.alberoframework.core.reflection.Reflection;
import com.alberoframework.testing.bdd.context.AbstractStatelessTestContext;
import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.type.conversion.converter.TypeConverter;

public class TypeConverterTestContext<C extends TypeConverter<S, T>, S, T> extends AbstractStatelessTestContext {

	private C typeConverter;

	public TypeConverterTestContext(Class<C> typeConverterType, PortRegistry portRegistry) {
		
		setPortRegistry(portRegistry);
		
		Map<String, Object> dependencies = new HashMap<>(portRegistry.asMap());
		
		typeConverter = createTypeConverter(typeConverterType, dependencies);
		
		Reflection.injectDependencies(typeConverter, dependencies);
	}
	
	protected C createTypeConverter(Class<C> typeConverterType, Map<String, Object> dependencies) {
		//TODO: find the best constructor and call it using the dependencies?
		return Reflection.newInstance(typeConverterType);
	}
	
	public C typeConverter() {
		return typeConverter;
	}
	
}
