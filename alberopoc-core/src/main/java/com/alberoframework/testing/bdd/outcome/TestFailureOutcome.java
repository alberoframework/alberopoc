package com.alberoframework.testing.bdd.outcome;

public abstract class TestFailureOutcome implements TestOutcome {

	@Override
	public boolean successful() {
		return false;
	}

	@Override
	public boolean failure() {
		return true;
	}
	
	public abstract String description();
	
	@Override
	public String toString() {
		return description();
	}
	
}
