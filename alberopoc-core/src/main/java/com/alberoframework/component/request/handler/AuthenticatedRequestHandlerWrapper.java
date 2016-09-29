package com.alberoframework.component.request.handler;

import com.alberoframework.component.request.contract.AuthenticatedRequestEnvelope;
import com.alberoframework.component.request.contract.Request;

public interface AuthenticatedRequestHandlerWrapper<RH extends AuthenticatedRequestHandler<ENV, UID, REQ ,RES>, ENV extends AuthenticatedRequestEnvelope<UID, REQ, RES>, UID, REQ extends Request<RES>, RES> extends RequestHandlerWrapper<RH, ENV, REQ, RES> {

}
