package com.alberoframework.component.request.handler;

import com.alberoframework.component.request.contract.AuthenticatedRequestEnvelope;
import com.alberoframework.component.request.contract.Request;

public interface AuthenticatedRequestHandler<ENV extends AuthenticatedRequestEnvelope<UID, REQ, RES>, UID, REQ extends Request<RES>, RES> extends RequestHandler<ENV, REQ, RES> {

}
