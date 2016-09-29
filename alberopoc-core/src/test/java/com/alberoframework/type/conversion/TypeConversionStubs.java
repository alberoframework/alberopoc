package com.alberoframework.type.conversion;

import java.util.Optional;

import com.alberoframework.component.query.contract.AbstractQuery;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.type.conversion.converter.AbstractSimpleAuthenticatedTypeConverter;
import com.alberoframework.type.conversion.converter.AbstractSimpleTypeConverter;
import com.alberoframework.type.conversion.converter.TypeConverter;
import com.alberoframework.type.conversion.gateway.TypeConversionGateway;

public class TypeConversionStubs {

	public static class StringToIntegerTypeConverter extends AbstractSimpleTypeConverter<String, Integer> {
		
		@Override
		protected Integer doConvert(String sourceType, ContextualizedQueryGateway queryGateway) {
			return Integer.parseInt(sourceType);
		}
	}
	
	public static class IntegerToStringTypeConverter implements TypeConverter<Integer, String> {
		@Override
		public String convert(Integer source) {
			return source.toString();
		}
	}
	
	public static class StringToLongTypeConverter implements TypeConverter<String, Long> {
		@Override
		public Long convert(String source) {
			return Long.parseLong(source);
		}
	}
	
	public static class LongToStringTypeConverter implements TypeConverter<Long, String> {
		@Override
		public String convert(Long source) {
			return source.toString();
		}
	}
	
	public static class IntegerToStringConverterWithQueryAndConversionRequests extends AbstractSimpleAuthenticatedTypeConverter<Integer, String> {
		
		private TypeConversionGateway typeConversionGateway;
		
		@Override
		protected String doConvert(Integer source, ContextualizedQueryGateway queryGateway) {
			Long aLong = typeConversionGateway.convert(source, Long.class);
			aLong += source;
			Optional<String> aString = queryGateway.handle(new GetStringForConversionQuery(source));
			if (aString.isPresent())
				return aString.get() + aLong;
			return aLong.toString();
		}
		
		public void setTypeConversionGateway(TypeConversionGateway typeConversionGateway) {
			this.typeConversionGateway = typeConversionGateway;
		}
	}
	
	
	public static class GetStringForConversionQuery extends AbstractQuery<Optional<String>> {
		
		private Integer param;

		public GetStringForConversionQuery(Integer param) {
			this.param = param;
		}
		
		public Integer getParam() {
			return param;
		}
		
	}
	
}
