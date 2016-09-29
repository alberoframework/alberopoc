package com.alberoframework.type.conversion.converter;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;

public abstract class AbstractTypeConverter<S, T, QG> implements TypeConverter<S, T> {

	private QG queryGateway;
	
	@Override
	public T convert(S source) {
		return doConvert(source, contextualizeQueryGateway(queryGateway));
	}
	
	protected abstract T doConvert(S source, ContextualizedQueryGateway queryGateway);
	
	protected abstract ContextualizedQueryGateway contextualizeQueryGateway(QG queryGateway);
	
	public void setQueryGateway(QG queryGateway) {
		this.queryGateway = queryGateway;
	}
	
}
