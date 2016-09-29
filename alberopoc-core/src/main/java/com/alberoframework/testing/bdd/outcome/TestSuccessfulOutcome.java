package com.alberoframework.testing.bdd.outcome;

public class TestSuccessfulOutcome implements TestOutcome {

	@Override
	public boolean successful() {
		return true;
	}

	@Override
	public boolean failure() {
		return false;
	}
	
	@Override
	public String toString() {
		return "Successful Test Outcome";
	}

}
