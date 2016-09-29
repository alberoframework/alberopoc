package com.alberoframework.content.conversion.gateway;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.alberoframework.content.conversion.ContentConversionTargetType;
import com.alberoframework.content.conversion.converter.ContentToObjectConverter;
import com.alberoframework.content.conversion.converter.ObjectToContentConverter;
import com.alberoframework.core.validation.Validation;

public class SimpleContentConversionGateway implements ContentConversionGateway {

	private Map<String, ObjectToContentConverter> converterToContentMap = new HashMap<>();
	
	private Map<String, ContentToObjectConverter> converterToObjectMap = new HashMap<>();
	
	@Override
	public byte[] convertToContent(String contentType, Object object) {
		Optional<ObjectToContentConverter> converterOpt = converterToContent(contentType);
		Validation.validate(converterOpt.isPresent(), IllegalArgumentException::new, "Cannot convert object to content, no converter registered for content type: " + contentType);
		ObjectToContentConverter converter = converterOpt.get();
		return converter.convert(object);
	}
	
	@Override
	public <T> T convertToObject(String contentType, byte[] content, Class<T> objectType) {
//		Optional<ContentToObjectConverter> converterOpt = converterToObject(contentType);
//		Validation.validate(converterOpt.isPresent(), IllegalArgumentException::new, "Cannot convert content to object, no converter registered for content type: " + contentType);
//		ContentToObjectConverter converter = converterOpt.get();
		return convertToObjectInternal(contentType, content, objectType);
	}
	
	@Override
	public <T> T convertToObject(String contentType, byte[] content, ContentConversionTargetType<T> objectType) {
		return convertToObjectInternal(contentType, content, objectType.getType());
	}
	
	protected <T> T convertToObjectInternal(String contentType, byte[] content, Type objectType) {
		Optional<ContentToObjectConverter> converterOpt = converterToObject(contentType);
		Validation.validate(converterOpt.isPresent(), IllegalArgumentException::new, "Cannot convert content to object, no converter registered for content type: " + contentType);
		ContentToObjectConverter converter = converterOpt.get();
		return converter.convert(content, objectType);
	}
	
	@Override
	public void registerObjectToContentConverter(String contentType, ObjectToContentConverter converter) {
		converterToContentMap.put(contentType, converter);
	}
	
	@Override
	public void registerContentToObjectConverter(String contentType, ContentToObjectConverter converter) {
		converterToObjectMap.put(contentType, converter);
	}
	
	protected Optional<ObjectToContentConverter> converterToContent(String contentType) {
		return Optional.ofNullable(converterToContentMap.get(contentType));
	}
	
	protected Optional<ContentToObjectConverter> converterToObject(String contentType) {
		return Optional.ofNullable(converterToObjectMap.get(contentType));
	}
}
