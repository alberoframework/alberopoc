package com.alberoframework.component.query.testing;

import com.alberoframework.component.query.contract.Query;
import com.alberoframework.component.query.handler.QueryHandler;
import com.alberoframework.component.request.contract.RequestEnvelope;
import com.alberoframework.component.request.testing.RequestHandlerTestContext;
import com.alberoframework.testing.bdd.testcase.query.AbstractQueryTestOperation;

public class QueryHandlerTestOperations {

	public static class QueryHandlerTestOperation<QH extends QueryHandler<ENV, Q, R>, ENV extends RequestEnvelope<Q, R>, Q extends Query<R>, R> extends AbstractQueryTestOperation<RequestHandlerTestContext<QH, ENV, Q, R>, R> {
		
		private ENV query;

		public QueryHandlerTestOperation(ENV query) {
			this.query = query;
		}
		
		@SuppressWarnings("unchecked")
		@Override
		public R doExecute(RequestHandlerTestContext<QH, ENV, Q, R> context) {
			QH handler = context.requestHandler();
			R response = handler.handle(query);
			return response;
//			return (response instanceof Optional) ? (Optional<R>) response : Optional.ofNullable(response);
		}

	}
	
}
