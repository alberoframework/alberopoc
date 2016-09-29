package com.alberoframework.content.conversion.gateway;

import java.lang.reflect.Type;

import com.alberoframework.content.conversion.converter.ContentToObjectConverter;
import com.alberoframework.content.conversion.converter.ObjectToContentConverter;

public class SimpleContentConversionGatewayStubs {

	public static class StringConverterToContent implements ObjectToContentConverter {
		@Override
		public byte[] convert(Object object) {
			return object.toString().getBytes();
		}
	}
	
	public static class StringWithPaddingConverterToContent implements ObjectToContentConverter {
		@Override
		public byte[] convert(Object object) {
			return object.toString().concat("PADDING").getBytes();
		}
	}
	
	public static class StringConverterToObject implements ContentToObjectConverter {
		@Override
		public <T> T convert(byte[] content, Type objectType) {
			String theString = new String(content);
			if (Integer.class.equals(objectType)) {
				return (T) new Integer(Integer.parseInt(theString));
			}
			if (Double.class.equals(objectType)) {
				return (T) new Double(Double.parseDouble(theString));
			}
			if (String.class.equals(objectType)) {
				return (T) theString;
			}
			throw new IllegalArgumentException("Cannot convert from String to " + objectType.toString() + " conversion not supported");
		}
	}
	
	public static class StringWithPaddingConverterToObject implements ContentToObjectConverter {
		
		@Override
		public <T> T convert(byte[] content, Type objectType) {
			String theString = new String(content);
			theString = theString.substring(0, theString.length() - "PADDING".length());
			if (Integer.class.equals(objectType)) {
				return (T) new Integer(Integer.parseInt(theString));
			}
			if (Double.class.equals(objectType)) {
				return (T) new Double(Double.parseDouble(theString));
			}
			if (String.class.equals(objectType)) {
				return (T) theString;
			}
			throw new IllegalArgumentException("Cannot convert from String to " + objectType.toString() + " conversion not supported");
		}
		
	}
	
}
