package com.alberoframework.type.conversion.converter;

public interface TypeConverter<S, T> {
	
	T convert(S source);

}
