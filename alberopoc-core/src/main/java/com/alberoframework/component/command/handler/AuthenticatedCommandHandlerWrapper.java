package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.request.contract.AuthenticatedRequestEnvelope;
import com.alberoframework.component.request.handler.AuthenticatedRequestHandlerWrapper;

public interface AuthenticatedCommandHandlerWrapper<CH extends AuthenticatedCommandHandler<ENV, UID, C, R>, ENV extends AuthenticatedRequestEnvelope<UID, C, R>, UID, C extends Command<R>, R> extends CommandHandlerWrapper<CH, ENV, C, R>, AuthenticatedRequestHandlerWrapper<CH, ENV, UID, C, R> {

}
