package com.alberoframework.type.conversion.gateway;

import java.util.Collection;

import org.springframework.context.ApplicationContext;

import com.alberoframework.core.reflection.Reflection;
import com.alberoframework.type.conversion.converter.TypeConverter;

public class SpringTypeConverterScanner {

	private ApplicationContext applicationContext;
	
	public SpringTypeConverterScanner(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void scanAndRegisterConverters(TypeConversionGateway typeConversionGateway) {
		Collection<? extends TypeConverter> converters = applicationContext.getBeansOfType(TypeConverter.class).values();
		for (TypeConverter converter : converters) {
			Class<?>[] genericParameters = Reflection.resolveGenericParameters(converter.getClass());
			Class<?> sourceType = genericParameters[0];
			Class<?> targetType = genericParameters[1];
			typeConversionGateway.registerConverter(sourceType, targetType, converter);
		}
	}
	
	
}
