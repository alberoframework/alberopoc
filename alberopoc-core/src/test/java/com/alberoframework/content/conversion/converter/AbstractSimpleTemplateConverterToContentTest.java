package com.alberoframework.content.conversion.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.alberoframework.content.conversion.converter.AbstractSimpleTemplateConverterToContent;
import com.alberoframework.content.conversion.converter.ObjectToContentConverter;
import com.alberoframework.content.template.SimpleTemplateRegistry;

public class AbstractSimpleTemplateConverterToContentTest {

	@Test
	public void testSuccessfulConversions() {
		ObjectToContentConverter converter = templateBasedconverter();
		
		assertEquals("The template for the value 77, an integer", new String(converter.convert(new Integer(77))));
		assertEquals("The template for 143, a long", new String(converter.convert(new Long(143))));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testNotRegisteredTemplate() {
		ObjectToContentConverter converter = templateBasedconverter();
		converter.convert("77");
	}
	
	public ObjectToContentConverter templateBasedconverter() {
		
		SimpleTemplateRegistry<String> templateRegistry = new SimpleTemplateRegistry<>();
		
		templateRegistry.registerTemplate(Integer.class, "The template for the value <token>, an integer");
		templateRegistry.registerTemplate(Long.class, "The template for <token>, a long");
		
		ObjectToContentConverter converter = new AbstractSimpleTemplateConverterToContent<String>(templateRegistry) {
			
			protected byte[] bind(Object object, String template) {
				return template.replace("<token>", object.toString()).getBytes();
			};
		};
		
		return converter;
	}

}
