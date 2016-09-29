package com.alberoframework.component.request.contract;

import java.util.Objects;
import java.util.Optional;

public abstract class AuthenticatedRequestEnvelope<UID, REQ extends Request<RES>, RES> extends RequestEnvelope<REQ, RES> {

	private UID userId;
	
	public AuthenticatedRequestEnvelope(UID userId, REQ request) {
		super(request);
		this.userId = userId;
	}
	
	public AuthenticatedRequestEnvelope(REQ request) {
		super(request);
	}

	public Optional<UID> userId() {
		return Optional.ofNullable(userId);
	}

	public boolean authenticated() {
		return userId().isPresent();
	}
	
	public boolean authenticatedAsSystem() {
		return userId().isPresent() && Objects.equals(userId().get(), systemUserId());
	}
	
	protected abstract UID systemUserId();

}
