package com.alberoframework.type.conversion.gateway;

import com.alberoframework.type.conversion.converter.TypeConverter;

public interface TypeConversionGateway {

	<S, T> T convert(S source, Class<T> targetType);
	
	<S, T> void registerConverter(Class<S> source, Class<T> targetType, TypeConverter<S, T> converter);
	
}
