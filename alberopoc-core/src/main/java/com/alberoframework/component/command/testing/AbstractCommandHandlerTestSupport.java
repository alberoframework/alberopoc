package com.alberoframework.component.command.testing;

import java.util.Arrays;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.handler.CommandHandler;
import com.alberoframework.component.command.testing.CommandHandlerTestOperations.CommandHandlerTestOperation;
import com.alberoframework.component.command.testing.CommandStubber.CommandStub;
import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.testing.QueryStubber;
import com.alberoframework.component.query.testing.QueryVerifier;
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

public abstract class AbstractCommandHandlerTestSupport<CH extends CommandHandler<ENV, C, R>, ENV extends RequestEnvelope<C, R>, C extends Command<R>, R, QG, CG, ES extends EntityTestingStore> extends RequestHandlerTestSupport<CH, ENV, C, R, ES> {

	public  CommandHandlerTestOperation<CH, ENV, C, R> handle(ENV command) {
		return new CommandHandlerTestOperation<CH, ENV, C, R>(command);
	}
	
	public CommandStubber commandStubs(CommandStub<?> ... stubs) {
		return new CommandStubber(Arrays.asList(stubs));
	}
	
	public <R1> CommandStub<R1> commandStub (RequestEnvelope<? extends Command<R1>, R1> command, R1 response) {
		return new CommandStub<R1>(command, response);
	}
	
//	public <R1> CommandStub<R1> commandStub (RequestEnvelope<? extends Command<R1>, R1> command) {
//		return new CommandStub<R1>(command);
//	}
	
	public CommandVerifier commandsSent(RequestEnvelope<? extends Command<?>, ?> ... commands) {
		return new CommandVerifier(Arrays.asList(commands));
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
	
	protected abstract CG commandGateway();
	
	@Override
	protected PortRegistry ports() {
		PortRegistry registry = super.ports();
		registry.put("queryGateway", queryGateway());
		registry.put("commandGateway", commandGateway());
		registry.put("typeConversionGateway", new TypeConversionGatewayStub());
		return registry;
	}
	
	
		
	
}
