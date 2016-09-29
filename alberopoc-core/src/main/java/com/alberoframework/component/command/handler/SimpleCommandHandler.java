package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.handler.SimpleRequestHandler;

public interface SimpleCommandHandler<C extends Command<R>, R> extends CommandHandler<RequestEnvelope<C, R>, C, R>, SimpleRequestHandler<C, R> {

}
