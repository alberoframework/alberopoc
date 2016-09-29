package com.alberoframework.component.command.testing;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.handler.SimpleAuthenticatedCommandHandler;
import com.alberoframework.component.request.testing.EmptyEntityTestingStore;

public abstract class SimpleAuthenticatedCommandHandlerStatelessTestSupport<CH extends SimpleAuthenticatedCommandHandler<C, R>, C extends Command<R>, R> extends AbstractSimpleAuthenticatedCommandHandlerTestSupport<CH, C, R, EmptyEntityTestingStore> {

	@Override
	protected EmptyEntityTestingStore entityStore() {
		return new EmptyEntityTestingStore();
	}
	
}
