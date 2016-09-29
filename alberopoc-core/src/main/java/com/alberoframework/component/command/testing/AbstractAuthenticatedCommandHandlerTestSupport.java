package com.alberoframework.component.command.testing;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.command.handler.AuthenticatedCommandHandler;
import com.alberoframework.component.request.contract.AuthenticatedRequestEnvelope;
import com.alberoframework.component.request.testing.EntityTestingStore;

public abstract class AbstractAuthenticatedCommandHandlerTestSupport<CH extends AuthenticatedCommandHandler<ENV, UID, C, R>, ENV extends AuthenticatedRequestEnvelope<UID, C, R>, UID, C extends Command<R>, R, QG, CG, ES extends EntityTestingStore> extends AbstractCommandHandlerTestSupport<CH, ENV, C, R, QG, CG, ES> {

}
