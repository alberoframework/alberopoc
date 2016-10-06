package com.alberoframework.domain.command;

import java.util.UUID;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.handler.SimpleAuthenticatedCommandHandler;
import com.alberoframework.component.command.handler.SimpleAuthenticatedCommandHandlerWrapper;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.component.request.handler.RequestHandler;

public class NewSimpleIdentityEnricherCommandHandlerWrapper implements SimpleAuthenticatedCommandHandlerWrapper<SimpleAuthenticatedCommandHandler<Command<Object>, Object>, Command<Object>, Object> {

	@Override
	public SimpleAuthenticatedCommandHandler<Command<Object>, Object> wrap(
			RequestHandler<? super SimpleAuthenticatedRequestEnvelope<Command<Object>, Object>, ? super Command<Object>, ? super Object> requestHandler) {
		return new SimpleAuthenticatedCommandHandler<Command<Object>, Object>() {
			
			@SuppressWarnings({ "rawtypes", "unchecked" })
			@Override
			public Object handle(SimpleAuthenticatedRequestEnvelope<Command<Object>, Object> requestEnv) {
				if ((Object) requestEnv.getRequest() instanceof SimpleCreateEntityCommand) {
					String newIdentity = UUID.randomUUID().toString();
					((SimpleCreateEntityCommand) (Object) requestEnv.getRequest()).assignIdentity(newIdentity); 
				}
				return requestHandler.handle(requestEnv);
			}
		};
	}
	
}
