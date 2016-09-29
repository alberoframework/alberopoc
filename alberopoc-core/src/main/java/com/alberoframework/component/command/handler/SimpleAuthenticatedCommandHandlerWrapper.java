package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.component.request.handler.SimpleAuthenticatedRequestHandlerWrapper;

public interface SimpleAuthenticatedCommandHandlerWrapper<CH extends SimpleAuthenticatedCommandHandler<C, R>, C extends Command<R>, R> extends AuthenticatedCommandHandlerWrapper<CH, SimpleAuthenticatedRequestEnvelope<C, R>, String, C, R>, SimpleAuthenticatedRequestHandlerWrapper<CH, C, R> {

}
