package com.alberoframework.component.query.contract;

public interface PredicateQuery extends Query<Boolean> {

	default PredicateQuery and (PredicateQuery query) {
		return new AndPredicateQuery(this, query);
	}
	
	default PredicateQuery or (PredicateQuery query) {
		return new OrPredicateQuery(this, query);
	}
	
	default PredicateQuery xor (PredicateQuery query) {
		return new XorPredicateQuery(this, query);
	}
	
	default PredicateQuery not () {
		return new NotPredicateQuery(this);
	}
	
}
