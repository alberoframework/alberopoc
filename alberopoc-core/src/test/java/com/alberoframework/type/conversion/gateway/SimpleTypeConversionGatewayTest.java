package com.alberoframework.type.conversion.gateway;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.alberoframework.type.conversion.TypeConversionStubs;
import com.alberoframework.type.conversion.gateway.SimpleTypeConversionGateway;

public class SimpleTypeConversionGatewayTest {

	@Test
	public void testSuccessfulConversions() {
		SimpleTypeConversionGateway typeConversionGateway = typeConversionGateway();
		assertEquals((Object) 42, typeConversionGateway.convert("42", Integer.class));
		assertEquals("42", typeConversionGateway.convert(42, String.class));
		
		assertEquals((Object) 42L, typeConversionGateway.convert("42", Long.class));
		assertEquals("42", typeConversionGateway.convert(42L, String.class));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNotRegisteredConversion() {
		SimpleTypeConversionGateway typeConversionGateway = typeConversionGateway();
		typeConversionGateway.convert(23.0, String.class); //double to string is not registered
	}
	
	protected SimpleTypeConversionGateway typeConversionGateway() {
		
		SimpleTypeConversionGateway simpleTypeConversionGateway = new SimpleTypeConversionGateway();
		simpleTypeConversionGateway.registerConverter(String.class, Integer.class, new TypeConversionStubs.StringToIntegerTypeConverter());
		simpleTypeConversionGateway.registerConverter(Integer.class, String.class, new TypeConversionStubs.IntegerToStringTypeConverter());
		simpleTypeConversionGateway.registerConverter(String.class, Long.class, new TypeConversionStubs.StringToLongTypeConverter());
		simpleTypeConversionGateway.registerConverter(Long.class, String.class, new TypeConversionStubs.LongToStringTypeConverter());
		
		return simpleTypeConversionGateway;
	}

}
