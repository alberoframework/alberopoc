package com.alberoframework.component.request.handler;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.RequestEnvelope;

public abstract class AbstractRequestHandler<ENV extends RequestEnvelope<REQ, RES>, REQ extends Request<RES>, RES> implements RequestHandler<ENV, REQ, RES> {

	
}
