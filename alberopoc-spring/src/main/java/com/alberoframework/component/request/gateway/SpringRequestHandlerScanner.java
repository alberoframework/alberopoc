package com.alberoframework.component.request.gateway;

import java.util.Collection;
import java.util.List;

import org.springframework.context.ApplicationContext;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.handler.RequestHandler;
import com.alberoframework.component.request.handler.RequestHandlerWrapper;
import com.alberoframework.core.reflection.Reflection;
import com.google.common.collect.Lists;

public class SpringRequestHandlerScanner {

	private ApplicationContext applicationContext;
	
	public SpringRequestHandlerScanner(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void scanAndRegisterHandlers(Class<? extends RequestHandler> handlerType, List<Class<? extends RequestHandlerWrapper>> handlerWrapperTypes, AbstractRequestGateway requestGateway) {
		Collection<? extends RequestHandler> handlers = applicationContext.getBeansOfType(handlerType).values();
		for (RequestHandler handler : handlers) {
			Class<? extends Request> requestType = Reflection.resolveFirstGenericParameterOfType(handler.getClass(), Request.class);
			requestGateway.registerHandler(requestType, wrapHandler(handlerWrapperTypes, handler));
		}
	}
	
	
	private RequestHandler wrapHandler(List<Class<? extends RequestHandlerWrapper>> handlerWrapperTypes, RequestHandler handler) {
		handlerWrapperTypes = Lists.reverse(handlerWrapperTypes);
		for (Class<? extends RequestHandlerWrapper> handlerWrapperType : handlerWrapperTypes) {
			RequestHandlerWrapper handlerWrapper = applicationContext.getBean(handlerWrapperType);
			handler = handlerWrapper.wrap(handler);
		}
		return handler;
	}
	
	
}
