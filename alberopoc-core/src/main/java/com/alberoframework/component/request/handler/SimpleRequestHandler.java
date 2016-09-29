package com.alberoframework.component.request.handler;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.RequestEnvelope;

public interface SimpleRequestHandler<REQ extends Request<RES>, RES> extends RequestHandler<RequestEnvelope<REQ, RES>, REQ, RES> {

}
