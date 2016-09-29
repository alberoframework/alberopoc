package com.alberoframework.content.conversion.converter.json;

import com.alberoframework.content.conversion.converter.ObjectToContentConverter;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonObjectToContentConverter implements ObjectToContentConverter {

	private ObjectMapper objectMapper;

	public JacksonObjectToContentConverter(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}
	
	@Override
	public byte[] convert(Object object) {
		byte[] jsonBytes;
		try {
			jsonBytes = objectMapper.writeValueAsBytes(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			throw new RuntimeException("Error converting object " + object + " to JSON ", e);
		}
		return jsonBytes;
	}
	
}
