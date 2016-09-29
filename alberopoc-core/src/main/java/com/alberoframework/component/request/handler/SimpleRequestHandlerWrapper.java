package com.alberoframework.component.request.handler;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.RequestEnvelope;

public interface SimpleRequestHandlerWrapper<RH extends SimpleRequestHandler<REQ, RES>, REQ extends Request<RES>, RES> extends RequestHandlerWrapper<RH, RequestEnvelope<REQ, RES>, REQ, RES>{

}
