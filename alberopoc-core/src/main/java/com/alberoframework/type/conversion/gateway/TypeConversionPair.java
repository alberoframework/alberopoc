package com.alberoframework.type.conversion.gateway;

import com.alberoframework.lang.object.BaseObject;

public class TypeConversionPair<S, T> extends BaseObject {

	private Class<S> source;
	private Class<T> target;
	
	public TypeConversionPair(Class<S> source, Class<T> target) {
		this.source = source;
		this.target = target;
	}
	
	public Class<S> getSource() {
		return source;
	}
	
	public Class<T> getTarget() {
		return target;
	}
	
}
