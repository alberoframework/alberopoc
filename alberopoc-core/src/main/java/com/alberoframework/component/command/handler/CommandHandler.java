package com.alberoframework.component.command.handler;

import com.alberoframework.component.command.contract.Command;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.handler.RequestHandler;

public interface CommandHandler<ENV extends RequestEnvelope<C, R >, C extends Command<R>, R> extends RequestHandler<ENV, C, R> {
	
}
