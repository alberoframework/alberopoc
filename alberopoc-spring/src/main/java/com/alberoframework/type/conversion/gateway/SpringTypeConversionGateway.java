package com.alberoframework.type.conversion.gateway;

public class SpringTypeConversionGateway extends SimpleTypeConversionGateway {

	private SpringTypeConverterScanner springTypeConverterScanner;
	
	public SpringTypeConversionGateway(SpringTypeConverterScanner springTypeConverterScanner) {
		this.springTypeConverterScanner = springTypeConverterScanner;
	}

	@Override
	public <S, T> T convert(S sourceType, Class<T> targetType) {
		if (isEmpty()) {
			springTypeConverterScanner.scanAndRegisterConverters(this);
		}
		return super.convert(sourceType, targetType);
	}
	
}
