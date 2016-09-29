package com.alberoframework.content.template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

import com.alberoframework.content.template.SimpleTemplateRegistry;

public class SimpleTemplateRegistryTest {

	@Test
	public void testRegisterAndFind() {
		SimpleTemplateRegistry<String> templateRegistry = new SimpleTemplateRegistry<>();
		
		String integerTemplate = "template for an integer";
		String doubleTemplate = "template for a double";
		
		templateRegistry.registerTemplate(Integer.class, integerTemplate);
		templateRegistry.registerTemplate(Double.class, doubleTemplate);
		
		assertEquals(integerTemplate, templateRegistry.template(Integer.class).get());
		assertEquals(doubleTemplate, templateRegistry.template(Double.class).get());
		
		assertFalse(templateRegistry.template(Number.class).isPresent());
	}
	
	
	

}
