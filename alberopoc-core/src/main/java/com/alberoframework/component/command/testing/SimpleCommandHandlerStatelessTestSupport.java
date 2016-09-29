package com.alberoframework.component.command.testing;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.handler.SimpleCommandHandler;
import com.alberoframework.component.request.testing.EmptyEntityTestingStore;

public abstract class SimpleCommandHandlerStatelessTestSupport<CH extends SimpleCommandHandler<C, R>, C extends Command<R>, R> extends AbstractSimpleCommandHandlerTestSupport<CH, C, R, EmptyEntityTestingStore> {

	@Override
	protected EmptyEntityTestingStore entityStore() {
		return new EmptyEntityTestingStore();
	}
	
}
