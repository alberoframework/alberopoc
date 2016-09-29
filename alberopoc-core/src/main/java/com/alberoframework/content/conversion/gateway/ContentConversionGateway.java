package com.alberoframework.content.conversion.gateway;

import com.alberoframework.content.conversion.ContentConversionTargetType;
import com.alberoframework.content.conversion.converter.ContentToObjectConverter;
import com.alberoframework.content.conversion.converter.ObjectToContentConverter;

public interface ContentConversionGateway {

	//put binery contenyt value obj
	
	byte[] convertToContent(String contentType, Object object);
	
	<T> T convertToObject(String contentType, byte[] content, Class<T> objectType);
	
	<T> T convertToObject(String contentType, byte[] content, ContentConversionTargetType<T> objectType);
	
	void registerObjectToContentConverter(String contentType, ObjectToContentConverter converter);
	
	void registerContentToObjectConverter(String contentType, ContentToObjectConverter converter);
	
	
	//TODO: Add content to content conversion
	
}
