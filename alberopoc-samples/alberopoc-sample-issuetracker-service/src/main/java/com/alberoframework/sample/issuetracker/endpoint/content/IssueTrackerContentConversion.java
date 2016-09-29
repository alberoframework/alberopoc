package com.alberoframework.sample.issuetracker.endpoint.content;

import com.alberoframework.content.constant.ContentTypeConstants;
import com.alberoframework.content.conversion.converter.freemarker.FreemarkerTemplateConverterToContent;
import com.alberoframework.content.conversion.converter.json.JacksonContentToObjectConverter;
import com.alberoframework.content.conversion.converter.json.JacksonObjectToContentConverter;
import com.alberoframework.content.conversion.converter.pdf.PdfTemplateConverterToContent;
import com.alberoframework.content.conversion.gateway.ContentConversionGateway;
import com.alberoframework.content.conversion.gateway.SimpleContentConversionGateway;
import com.alberoframework.content.template.freemarker.FreemarkerTemplateRegistry;
import com.alberoframework.content.template.pdf.PdfTemplateRegistry;
import com.alberoframework.sample.issuetracker.content.conversion.converter.json.IssueTrackerJacksonObjectMapper;

public class IssueTrackerContentConversion {
	
	public static ContentConversionGateway createConversionGatewayWithConverters() {
		ContentConversionGateway contentConversionGateway = new SimpleContentConversionGateway();
		contentConversionGateway.registerObjectToContentConverter(ContentTypeConstants.JSON, new JacksonObjectToContentConverter(new IssueTrackerJacksonObjectMapper()));
		contentConversionGateway.registerContentToObjectConverter(ContentTypeConstants.JSON, new JacksonContentToObjectConverter(new IssueTrackerJacksonObjectMapper()));
		


		return contentConversionGateway;
	}

}
