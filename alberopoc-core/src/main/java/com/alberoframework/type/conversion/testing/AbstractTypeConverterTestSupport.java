package com.alberoframework.type.conversion.testing;

import java.util.Arrays;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.testing.QueryStubber;
import com.alberoframework.component.query.testing.QueryVerifier;
import com.alberoframework.component.query.testing.QueryStubber.QueryStub;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.core.reflection.Reflection;
import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.testing.bdd.support.AbstractStatelessTestSupport;
import com.alberoframework.type.conversion.converter.TypeConverter;
import com.alberoframework.type.conversion.testing.TypeConversionStubber.TypeConversionStub;
import com.alberoframework.type.conversion.testing.TypeConverterTestOperations.TypeConverterTestOperation;

public abstract class AbstractTypeConverterTestSupport<C extends TypeConverter<S, T>, S, T, QG> extends AbstractStatelessTestSupport<TypeConverterTestContext<C, S, T>> {

	@SuppressWarnings("unchecked")
	@Override
	protected TypeConverterTestContext<C, S, T> context(PortRegistry ports) {
		return new TypeConverterTestContext<C, S, T>((Class<C>) Reflection.resolveFirstGenericParameterOfType(this.getClass(), TypeConverter.class), ports);
	}
	
	public TypeConverterTestOperation<C, S, T> convert(S source) {
		return new TypeConverterTestOperation<C, S, T>(source);
	}
	
	public QueryStubber queryStubs(QueryStub<?> ... stubs) {
		return new QueryStubber(Arrays.asList(stubs));
	}
	
	public <R1> QueryStub<R1> queryStub (RequestEnvelope<? extends Query<R1>, R1> query, R1 response) {
		return new QueryStub<R1>(query, response);
	}
	
//	public <R1> QueryStub<R1> queryStub (RequestEnvelope<? extends Query<R1>, R1> query) {
//		return new QueryStub<R1>(query);
//	}
	
	public QueryVerifier queriesSent(RequestEnvelope<? extends Query<?>, ?> ... queries) {
		return new QueryVerifier(Arrays.asList(queries));
	}
	
	public TypeConversionStubber typeConversionStubs(TypeConversionStub<?, ?> ... stubs) {
		return new TypeConversionStubber(Arrays.asList(stubs));
	}
	
	public <S1, T1> TypeConversionStub<S1, T1> typeConversionStub (TypeConversionRequest<S1, T1> typeConversionRequest, T1 target) {
		return new TypeConversionStub<S1, T1>(typeConversionRequest, target);
	}
	
	public TypeConversionVerifier typeConversionRequestsSent(TypeConversionRequest<?, ?> ... typeConversionRequests) {
		return new TypeConversionVerifier(Arrays.asList(typeConversionRequests));
	}
	
	public <S1, T1> TypeConversionRequest<S1, T1> typeConversionRequest (S1 source, Class<T1> targetType) {
		return new TypeConversionRequest<S1, T1>(source, targetType);
	}
	
	
	protected abstract QG queryGateway();
	
	@Override
	protected PortRegistry ports() {
		PortRegistry registry = super.ports();
		registry.put("queryGateway", queryGateway());
		registry.put("typeConversionGateway", new TypeConversionGatewayStub());
		return registry;
	}

	
}
