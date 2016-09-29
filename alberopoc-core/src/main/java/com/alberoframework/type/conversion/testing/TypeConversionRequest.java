package com.alberoframework.type.conversion.testing;

import com.alberoframework.lang.object.BaseObject;

public class TypeConversionRequest<S, T> extends BaseObject {

	private S source;
	private Class<T> target;
	
	public TypeConversionRequest(S source, Class<T> target) {
		this.source = source;
		this.target = target;
	}
	
	public S getSource() {
		return source;
	}
	
	public Class<T> getTarget() {
		return target;
	}
	
}
