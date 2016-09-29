package com.alberoframework.component.request.handler;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.RequestEnvelope;

public interface RequestHandler<ENV extends RequestEnvelope<REQ, RES>, REQ extends Request<RES>, RES> {

	public RES handle(ENV requestEnvelope);
	
}
