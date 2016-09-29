package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;
import com.alberoframework.component.request.handler.SimpleAuthenticatedRequestHandler;

public interface SimpleAuthenticatedCommandHandler<C extends Command<R>, R> extends AuthenticatedCommandHandler<SimpleAuthenticatedRequestEnvelope<C, R>, String, C, R>, SimpleAuthenticatedRequestHandler<C, R> {

}
