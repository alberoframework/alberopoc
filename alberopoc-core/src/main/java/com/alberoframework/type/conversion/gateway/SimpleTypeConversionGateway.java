package com.alberoframework.type.conversion.gateway;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.alberoframework.core.validation.Validation;
import com.alberoframework.type.conversion.converter.TypeConverter;

public class SimpleTypeConversionGateway implements TypeConversionGateway {

	private Map<TypeConversionPair<?, ?>, TypeConverter<?, ?>> typeConverterMap = new HashMap<>();
	
	@SuppressWarnings("unchecked")
	@Override
	public <S, T> T convert(S source, Class<T> targetType) {
		Optional<TypeConverter<S, T>> converterOpt = converter(new TypeConversionPair<S, T>((Class<S>) source.getClass(), targetType));
		Validation.validate(converterOpt.isPresent(), IllegalArgumentException::new, "Cannot convert type " + source.getClass() + " to type " + targetType + ", no converter is registered");
		TypeConverter<S, T> converter = converterOpt.get();
		return converter.convert(source);
	}
	
	@Override
	public <S, T> void registerConverter(Class<S> sourceType, Class<T> targetType, TypeConverter<S, T> converter) {
		typeConverterMap.put(new TypeConversionPair<>(sourceType, targetType), converter);
	}
	
	@SuppressWarnings("unchecked")
	protected <S, T> Optional<TypeConverter<S, T>> converter(TypeConversionPair<S, T> pair) {
		return Optional.ofNullable((TypeConverter<S, T>) typeConverterMap.get(pair));
	}
	
	protected boolean isEmpty() {
		return typeConverterMap.isEmpty();
	}
	
}
