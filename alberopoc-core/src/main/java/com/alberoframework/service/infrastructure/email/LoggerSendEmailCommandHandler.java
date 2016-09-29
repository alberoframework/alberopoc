package com.alberoframework.service.infrastructure.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alberoframework.component.command.gateway.ContextualizedCommandGateway;
import com.alberoframework.component.command.handler.AbstractSimpleAuthenticatedVoidCommandHandler;
import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;

public class LoggerSendEmailCommandHandler extends AbstractSimpleAuthenticatedVoidCommandHandler<SendEmailCommand> implements SendEmailCommandHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(LoggerSendEmailCommandHandler.class);
	
	@Override
	protected void doHandle(SendEmailCommand command, ContextualizedQueryGateway queryGateway,
			ContextualizedCommandGateway commandGateway) {
		
		logger.info("---- SEND EMAIL REQUESTED: " + command);
        
        
	}

}
