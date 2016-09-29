package com.alberoframework.component.request.handler;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.SimpleAuthenticatedRequestEnvelope;

public interface SimpleAuthenticatedRequestHandler<REQ extends Request<RES>, RES> extends AuthenticatedRequestHandler<SimpleAuthenticatedRequestEnvelope<REQ, RES>, String, REQ, RES> {

}
