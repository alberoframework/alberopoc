package com.alberoframework.content.conversion.converter.json;

import java.io.IOException;
import java.lang.reflect.Type;

import com.alberoframework.content.conversion.converter.ContentToObjectConverter;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonContentToObjectConverter implements ContentToObjectConverter {

	private ObjectMapper objectMapper;

	public JacksonContentToObjectConverter(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	@Override
	public <T> T convert(byte[] content, Type objectType) {
		T object;
		try {
		    object = objectMapper.readValue(content, objectMapper.getTypeFactory().constructType(objectType));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Error converting JSON content: " + content + " to type: " + objectType, e);
		}
		return object;
	}
	
}
