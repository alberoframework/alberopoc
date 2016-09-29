package com.alberoframework.component.query.contract;

import com.alberoframework.core.validation.Validation;

public class NotPredicateQuery extends AbstractPredicateQuery {
	
	private PredicateQuery query;
	
	public NotPredicateQuery(PredicateQuery query) {
		Validation.validate(query != null, IllegalArgumentException::new, "Predicate Query cannot be null");
		this.query = query;
	}
	
	public PredicateQuery getQuery() {
		return query;
	}
	
}
