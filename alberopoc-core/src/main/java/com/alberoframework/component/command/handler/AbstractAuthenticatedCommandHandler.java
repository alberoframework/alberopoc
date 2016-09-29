package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.request.contract.AuthenticatedRequestEnvelope;

public abstract class AbstractAuthenticatedCommandHandler<ENV extends AuthenticatedRequestEnvelope<UID, C, R>, UID, C extends Command<R>, R, QG, CG> extends AbstractCommandHandler<ENV, C, R, QG, CG> implements AuthenticatedCommandHandler<ENV, UID, C, R> {

}
