package com.alberoframework.content.conversion.converter;

import java.lang.reflect.Type;

public interface ContentToObjectConverter {

	<T> T convert(byte[] content, Type objectType);
	
}
