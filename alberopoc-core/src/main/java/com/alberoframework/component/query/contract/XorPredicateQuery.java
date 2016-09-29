package com.alberoframework.component.query.contract;

import com.alberoframework.core.validation.Validation;

public class XorPredicateQuery extends AbstractPredicateQuery {
	
	private PredicateQuery first;
	
	private PredicateQuery second;

	public XorPredicateQuery(PredicateQuery first, PredicateQuery second) {
		Validation.validate(first != null, IllegalArgumentException::new, "First Predicate Query cannot be null");
		Validation.validate(second != null, IllegalArgumentException::new, "Second Predicate Query cannot be null");
		this.first = first;
		this.second = second;
	}
	
	public PredicateQuery getFirst() {
		return first;
	}

	public PredicateQuery getSecond() {
		return second;
	}
}
