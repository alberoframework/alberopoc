package com.alberoframework.component.query.testing;

import java.util.Arrays;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.handler.QueryHandler;
import com.alberoframework.component.query.testing.QueryHandlerTestOperations.QueryHandlerTestOperation;
import com.alberoframework.component.query.testing.QueryStubber.QueryStub;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.testing.EntityTestingStore;
import com.alberoframework.component.request.testing.RequestHandlerTestSupport;
import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.type.conversion.testing.TypeConversionGatewayStub;
import com.alberoframework.type.conversion.testing.TypeConversionRequest;
import com.alberoframework.type.conversion.testing.TypeConversionStubber;
import com.alberoframework.type.conversion.testing.TypeConversionVerifier;
import com.alberoframework.type.conversion.testing.TypeConversionStubber.TypeConversionStub;

public abstract class AbstractQueryHandlerTestSupport<QH extends QueryHandler<ENV, Q, R>, ENV extends RequestEnvelope<Q, R>, Q extends Query<R>, R, QG, ES extends EntityTestingStore> extends RequestHandlerTestSupport<QH, ENV, Q, R, ES> {

	public  QueryHandlerTestOperation<QH, ENV, Q, R> handle(ENV query) {
		return new QueryHandlerTestOperation<QH, ENV, Q, R>(query);
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
