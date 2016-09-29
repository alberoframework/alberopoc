package com.alberoframework.service.infrastructure.date;

import java.time.LocalDateTime;

import com.alberoframework.component.query.gateway.ContextualizedQueryGateway;
import com.alberoframework.component.query.handler.AbstractSimpleAuthenticatedQueryHandler;

public class CurrentDateTimeSimpleAuthenticatedQueryHandler extends AbstractSimpleAuthenticatedQueryHandler<CurrentDateTimeQuery, LocalDateTime> {
	
	@Override
	protected LocalDateTime doHandle(CurrentDateTimeQuery query, ContextualizedQueryGateway queryGateway) {
		return LocalDateTime.now();
	}
}
