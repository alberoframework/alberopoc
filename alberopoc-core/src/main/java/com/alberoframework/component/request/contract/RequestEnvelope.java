package com.alberoframework.component.request.contract;

import com.alberoframework.lang.object.BaseObject;

public class RequestEnvelope<REQ extends Request<RES>, RES> extends BaseObject {

	private REQ request;

	public RequestEnvelope(REQ request) {
		this.request = request;
	}

	public REQ getRequest() {
		return request;
	}
	
}
