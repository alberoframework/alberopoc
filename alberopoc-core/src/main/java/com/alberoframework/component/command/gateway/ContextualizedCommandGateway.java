package com.alberoframework.component.command.gateway;

import com.alberoframework.component.command.contract.Command;

public interface ContextualizedCommandGateway {

	<R> R handle(Command<R> command);

}
