package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.handler.RequestHandlerWrapper;

public interface CommandHandlerWrapper<CH extends CommandHandler<ENV, C, R>, ENV extends RequestEnvelope<C, R>, C extends Command<R>, R> extends RequestHandlerWrapper<CH, ENV, C, R> {

}
