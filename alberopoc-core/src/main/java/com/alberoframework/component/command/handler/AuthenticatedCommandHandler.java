package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.request.contract.AuthenticatedRequestEnvelope;
import com.alberoframework.component.request.handler.AuthenticatedRequestHandler;

public interface AuthenticatedCommandHandler<ENV extends AuthenticatedRequestEnvelope<UID, C, R>, UID, C extends Command<R>, R> extends AuthenticatedRequestHandler<ENV, UID, C, R>, CommandHandler<ENV, C, R> {

}
