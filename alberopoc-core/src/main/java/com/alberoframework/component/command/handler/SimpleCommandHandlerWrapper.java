package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.handler.SimpleRequestHandlerWrapper;

public interface SimpleCommandHandlerWrapper<CH extends SimpleCommandHandler<C, R>, C extends Command<R>, R> extends CommandHandlerWrapper<CH, RequestEnvelope<C, R>, C, R>, SimpleRequestHandlerWrapper<CH, C, R> {

}
