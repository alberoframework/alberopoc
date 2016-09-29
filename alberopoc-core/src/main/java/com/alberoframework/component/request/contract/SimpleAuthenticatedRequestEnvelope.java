package com.alberoframework.component.request.contract;

public class SimpleAuthenticatedRequestEnvelope<REQ extends Request<RES>, RES> extends AuthenticatedRequestEnvelope<String, REQ, RES> {

	private static String SYSTEM_USER_ID = "SYSTEM";
	
	public SimpleAuthenticatedRequestEnvelope(String userId, REQ request) {
		super(userId, request);
	}
	
	public SimpleAuthenticatedRequestEnvelope(REQ request) {
		super(request);
	}

	public static <REQ1 extends Request<RES1>, RES1> SimpleAuthenticatedRequestEnvelope<REQ1, RES1> envelopeWithSystemUser(REQ1 request) {
		return new SimpleAuthenticatedRequestEnvelope<REQ1, RES1>(SYSTEM_USER_ID, request);
	}
	
	public <REQ1 extends Request<RES1>, RES1> SimpleAuthenticatedRequestEnvelope<REQ1, RES1> withNewRequest(REQ1 request) {
		if (userId().isPresent())
			return new SimpleAuthenticatedRequestEnvelope<REQ1, RES1>(userId().get(), request);
		else
			return new SimpleAuthenticatedRequestEnvelope<REQ1, RES1>(request);
	}
	
	@Override
	protected String systemUserId() {
		return "SYSTEM";
	}
}
