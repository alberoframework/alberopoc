package com.alberoframework.content.conversion.converter.freemarker;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;

import com.alberoframework.content.conversion.converter.AbstractSimpleTemplateConverterToContent;
import com.alberoframework.content.template.freemarker.FreemarkerTemplateRegistry;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreemarkerTemplateConverterToContent extends AbstractSimpleTemplateConverterToContent<String> {

	private Configuration configuration;
	
	public FreemarkerTemplateConverterToContent(FreemarkerTemplateRegistry templateRegistry, Configuration configuration) {
		super(templateRegistry);
		this.configuration = configuration;
	}

	@Override
	protected byte[] bind(Object object, String templatePath) {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            final Template template = configuration.getTemplate(templatePath);
            template.process(object, new OutputStreamWriter(baos));
        }
        catch (Exception e) {
            throw new RuntimeException("Error processing template: "+ templatePath, e);
        }
        return baos.toByteArray();
	}
	
	
}
