package com.alberoframework.type.conversion.testing;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alberoframework.type.conversion.converter.TypeConverter;
import com.alberoframework.type.conversion.gateway.TypeConversionGateway;

public class TypeConversionGatewayStub implements TypeConversionGateway {
	
	private Map<TypeConversionRequest<?, ?>, Object> conversionRequestStubs = new HashMap<>();
	
	private List<TypeConversionRequest<?, ?>> conversionRequestsReceived = new ArrayList<>();
	
	@SuppressWarnings("unchecked")
	@Override
	public <S, T> T convert(S source, Class<T> targetType) {
		conversionRequestsReceived.add(new TypeConversionRequest<S, T>(source, targetType));
		return (T) conversionRequestStubs.get(new TypeConversionRequest<S, T>(source, targetType));
	}
	
	public <S, T> void stubConversionRequest(TypeConversionRequest<S, T> typeConversionRequest, T target) {
		conversionRequestStubs.put(typeConversionRequest, target);
	}
	
	public List<TypeConversionRequest<?, ?>> conversionRequestsReceived() {
		return conversionRequestsReceived;
	}

	@Override
	public <S, T> void registerConverter(Class<S> source, Class<T> targetType, TypeConverter<S, T> converter) {
		throw new UnsupportedOperationException("This is a stub gateway, you cant register converters");
	}
	
	
}
