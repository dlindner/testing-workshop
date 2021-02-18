package com.schneide.workshop.testing.e2e.internal;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public class DataValidation {

	private Clock today;

	public DataValidation(Clock today) {
		super();
		this.today = today;
	}
	
	public Optional<ECBData> validate(ECBData data) {
		TemporalAccessor referenceDate = DateTimeFormatter.ISO_LOCAL_DATE.withZone(this.today.getZone()).parse(data.timestamp());
		LocalDate given = LocalDate.from(referenceDate);
		LocalDate expected = LocalDate.ofInstant(this.today.instant(), this.today.getZone());
		if (expected.isEqual(given)) {
			return Optional.of(data);
		}
		return Optional.empty();
	}
}
