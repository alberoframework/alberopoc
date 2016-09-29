package com.alberoframework.type.conversion.converter;

import org.junit.Test;

import com.alberoframework.type.conversion.TypeConversionStubs.StringToIntegerTypeConverter;
import com.alberoframework.type.conversion.testing.SimpleTypeConverterTestSupport;

public class StringToIntegerTypeConverterTest extends SimpleTypeConverterTestSupport<StringToIntegerTypeConverter, String, Integer> {

	@Test
	public TestSpecification testSuccessfulConversions() {
		return   given()
				.when(convert("42")).then(42)
				.when(convert("03")).then(3)
				.when(convert("-2")).then(-2)
				.when(convert("0")).then(0);
	}
	
}
