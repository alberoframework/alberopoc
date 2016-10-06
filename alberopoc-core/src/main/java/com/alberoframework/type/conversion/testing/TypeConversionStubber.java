package com.alberoframework.type.conversion.testing;

import java.util.ArrayList;
import java.util.List;

import com.alberoframework.lang.object.BaseObject;
import com.alberoframework.testing.bdd.port.PortRegistry;
import com.alberoframework.testing.bdd.testcase.port.PortRequestsStubber;

public class TypeConversionStubber implements PortRequestsStubber {

	private List<TypeConversionStub<?, ?>> stubs = new ArrayList<>();
	
	public TypeConversionStubber(List<TypeConversionStub<?, ?>> stubs) {
		this.stubs = stubs;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void stubPortRequests(PortRegistry portRegistry) {
		TypeConversionGatewayStub typeConversionGatewayStub = (TypeConversionGatewayStub) portRegistry.get("typeConversionGateway");
		if (typeConversionGatewayStub == null) {
			throw new IllegalStateException("Type Conversion Gateway not found in ports registry");
		}
		
		for (TypeConversionStub stub : stubs) {
			typeConversionGatewayStub.stubConversionRequest(stub.getTypeConversionRequest(), stub.getTarget());
		}
	}
	
	public static class TypeConversionStub<S, T> extends BaseObject {
		
		private TypeConversionRequest<S, T> typeConversionRequest;
		private T target;
		
		public TypeConversionStub(TypeConversionRequest<S, T> typeConversionRequest, T target) {
			this.typeConversionRequest = typeConversionRequest;
			this.target = target;
		}
		
		public TypeConversionRequest<S, T> getTypeConversionRequest() {
			return typeConversionRequest;
		}
		
		public T getTarget() {
			return target;
		}
		
	}
	
}
