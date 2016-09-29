package com.alberoframework.component.request.handler;

import com.alberoframework.component.request.contract.Request;
import com.alberoframework.component.request.contract.RequestEnvelope;

public interface RequestHandlerWrapper<RH extends RequestHandler<ENV, REQ, RES>, ENV extends RequestEnvelope<REQ, RES>, REQ extends Request<RES>, RES> {

	public RH wrap(RequestHandler<? super ENV, ? super REQ, ? super RES> requestHandler);
	
}
