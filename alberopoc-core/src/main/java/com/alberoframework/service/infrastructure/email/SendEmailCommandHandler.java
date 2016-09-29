package com.alberoframework.service.infrastructure.email;

import com.alberoframework.component.command.handler.SimpleAuthenticatedCommandHandler;
import com.alberoframework.lang.VoidUnit;

public interface SendEmailCommandHandler extends SimpleAuthenticatedCommandHandler<SendEmailCommand, VoidUnit> {

}
