package com.alberoframework.content.conversion.gateway;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.alberoframework.content.conversion.ContentConversionTargetType;
import com.alberoframework.content.conversion.converter.ContentToObjectConverter;
import com.alberoframework.content.conversion.converter.ObjectToContentConverter;
import com.alberoframework.content.conversion.gateway.ContentConversionGateway;
import com.alberoframework.content.conversion.gateway.SimpleContentConversionGateway;

public class SimpleContentConversionGatewayTest {

	private static final String STRING_CONTENT_TYPE = "string";
	private static final String STRING_WITH_PADDING_CONTENT_TYPE = "stringWithPadding";
	private static final String STRING_WITH_PREFIX_CONTENT_TYPE = "stringWithPrefix";
	
	@Test
	public void testSuccessfulConversionsToContent() {
		Integer anInteger = 778;
		String aString = "String  ConTenT";
		
		ContentConversionGateway conversionGateway = simpleConversionGateway();
		
		assertEquals((Object)"778", new String(conversionGateway.convertToContent(STRING_CONTENT_TYPE, anInteger)));
		assertEquals(aString, new String(conversionGateway.convertToContent(STRING_CONTENT_TYPE, aString)));
		
		assertEquals("778PADDING", new String(conversionGateway.convertToContent(STRING_WITH_PADDING_CONTENT_TYPE, anInteger)));
		assertEquals(aString.concat("PADDING"), new String(conversionGateway.convertToContent(STRING_WITH_PADDING_CONTENT_TYPE, aString)));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testNotRegisteredContentTypeWhenConvertingToContent() {
		ContentConversionGateway conversionGateway = simpleConversionGateway();
		conversionGateway.convertToContent(STRING_WITH_PREFIX_CONTENT_TYPE, "test");
	}
	
	@Test
	public void testSuccessfulConversionsToObject() {
		ContentConversionGateway conversionGateway = simpleConversionGateway();
		
		String anIntegerString = "778";
		String aString = "String  ConTenT";
		
		assertEquals((Object)778, conversionGateway.convertToObject(STRING_CONTENT_TYPE, anIntegerString.getBytes(), Integer.class));
		assertEquals("String  ConTenT", conversionGateway.convertToObject(STRING_CONTENT_TYPE, aString.getBytes(), String.class));
		
		String anIntegerStringWithPadding = "778PADDING";
		String aStringWithPadding = "String  ConTenTPADDING";
		
		assertEquals((Object)778, conversionGateway.convertToObject(STRING_WITH_PADDING_CONTENT_TYPE, anIntegerStringWithPadding.getBytes(), Integer.class));
		assertEquals("String  ConTenT", conversionGateway.convertToObject(STRING_WITH_PADDING_CONTENT_TYPE, aStringWithPadding.getBytes(), String.class));
	}
	
	@Test
	public void testSuccessfulConversionsToObjectFromConversionsToContent() {
		Integer anInteger = 778;
		String aString = "String  ConTenT";
		
		ContentConversionGateway conversionGateway = simpleConversionGateway();
		
		String convertedStringInteger = new String(conversionGateway.convertToContent(STRING_CONTENT_TYPE, anInteger));
		String convertedString = new String(conversionGateway.convertToContent(STRING_CONTENT_TYPE, aString));
		
		assertEquals(anInteger, conversionGateway.convertToObject(STRING_CONTENT_TYPE, convertedStringInteger.getBytes(), Integer.class));
		assertEquals(aString, conversionGateway.convertToObject(STRING_CONTENT_TYPE, convertedString.getBytes(), String.class));
		
		String convertedStringWithPaddingInteger = new String(conversionGateway.convertToContent(STRING_WITH_PADDING_CONTENT_TYPE, anInteger));
		String convertedStringWithPadding = new String(conversionGateway.convertToContent(STRING_WITH_PADDING_CONTENT_TYPE, aString));
		
		assertEquals(anInteger, conversionGateway.convertToObject(STRING_WITH_PADDING_CONTENT_TYPE, convertedStringWithPaddingInteger.getBytes(), new ContentConversionTargetType<Integer>() { }));
		assertEquals(aString, conversionGateway.convertToObject(STRING_WITH_PADDING_CONTENT_TYPE, convertedStringWithPadding.getBytes(), new ContentConversionTargetType<String>() { }));
	}
	
	@Test
	public void testSuccessfulConversionsToContentFromConversionsToObject() {
		String anIntegerStringWithPadding = "778PADDING";
		String aStringWithPadding = "String  ConTenTPADDING";
		
		ContentConversionGateway conversionGateway = simpleConversionGateway();
		
		Integer convertedInteger = conversionGateway.convertToObject(STRING_WITH_PADDING_CONTENT_TYPE, anIntegerStringWithPadding.getBytes(), Integer.class);
		String convertedString = conversionGateway.convertToObject(STRING_WITH_PADDING_CONTENT_TYPE, aStringWithPadding.getBytes(), String.class);
		
		assertEquals(anIntegerStringWithPadding, new String(conversionGateway.convertToContent(STRING_WITH_PADDING_CONTENT_TYPE, convertedInteger)));
		assertEquals(aStringWithPadding, new String(conversionGateway.convertToContent(STRING_WITH_PADDING_CONTENT_TYPE, convertedString)));
	}
	
	
	@Test(expected=IllegalArgumentException.class)
	public void testConversionsToObjectWithNotSupportedObjectType() {
		ContentConversionGateway conversionGateway = simpleConversionGateway();
		conversionGateway.convertToObject(STRING_CONTENT_TYPE, "test".getBytes(), Number.class);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testNotRegisteredContentTypeWhenConvertingToObject() {
		ContentConversionGateway conversionGateway = simpleConversionGateway();
		conversionGateway.convertToObject(STRING_WITH_PREFIX_CONTENT_TYPE, "test".getBytes(), String.class);
	}
	
	protected ContentConversionGateway simpleConversionGateway() {
		ObjectToContentConverter stringConverter = new SimpleContentConversionGatewayStubs.StringConverterToContent();
		ObjectToContentConverter stringWithPaddingConverter = new SimpleContentConversionGatewayStubs.StringWithPaddingConverterToContent();
		
		ContentToObjectConverter stringConverterToObject = new SimpleContentConversionGatewayStubs.StringConverterToObject();
		ContentToObjectConverter stringWithPaddingConverterToObject = new SimpleContentConversionGatewayStubs.StringWithPaddingConverterToObject();
		
		
		ContentConversionGateway conversionGateway = new SimpleContentConversionGateway();
		conversionGateway.registerObjectToContentConverter(STRING_CONTENT_TYPE, stringConverter);
		conversionGateway.registerObjectToContentConverter(STRING_WITH_PADDING_CONTENT_TYPE, stringWithPaddingConverter);
		
		conversionGateway.registerContentToObjectConverter(STRING_CONTENT_TYPE, stringConverterToObject);
		conversionGateway.registerContentToObjectConverter(STRING_WITH_PADDING_CONTENT_TYPE, stringWithPaddingConverterToObject);
		
		return conversionGateway;
	}

}
