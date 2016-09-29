package com.alberoframework.component.request.handler;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;

public interface SimpleAuthenticatedRequestHandlerWrapper<RH extends SimpleAuthenticatedRequestHandler<REQ, RES>, REQ extends Request<RES>, RES> extends RequestHandlerWrapper<RH, SimpleAuthenticatedRequestEnvelope<REQ, RES>, REQ, RES> {

}
