package com.alberoframework.content.conversion.converter.pdf;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

import com.alberoframework.content.conversion.converter.ObjectToContentConverter;
import com.alberoframework.content.template.pdf.PdfTemplate;
import com.alberoframework.content.template.pdf.PdfTemplateRegistry;
import com.alberoframework.core.validation.Validation;

public class PdfTemplateConverterToContent implements ObjectToContentConverter {

	private PdfTemplateRegistry templateRegistry;

	public PdfTemplateConverterToContent(PdfTemplateRegistry templateRegistry) {
		this.templateRegistry = templateRegistry;
	}
	
	@Override
	public byte[] convert(Object object) {
		Optional<PdfTemplate<Object>> templateOpt = templateRegistry.template(object.getClass());
		Validation.validate(templateOpt.isPresent(), IllegalArgumentException::new, "Cannot convert object, template not registered for type " + object.getClass());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		try {
			templateOpt.get().bind(object, bos);
		}
		finally {
			try {
				bos.close();
			}
			catch(Exception e) {
				throw new RuntimeException("WFT", e);
			}
		}
		return bos.toByteArray();
	}
	
	
}
