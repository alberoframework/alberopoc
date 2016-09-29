package com.alberoframework.component.query.gateway;

import com.alberoframework.component.query.contract.Query;

public interface ContextualizedQueryGateway {

	<R> R handle(Query<R> query);

}
