package com.schneide.workshop.testing.e2e.internal;

import java.time.Clock;
import java.util.Optional;

public class DataValidation {

	private Clock today;

	public DataValidation(Clock today) {
		super();
		this.today = today;
	}
	
	public Optional<ECBData> validate(ECBData data) {
		return Optional.empty();
	}

}
