package com.schneide.workshop.testing.atdd.internal;

import java.time.Clock;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.Optional;

public class DataValidation {

	private final Clock today;

	public DataValidation(Clock today) {
		this.today = today;
	}

	public Optional<Iterable<Conversion>> validate(ECBData data) {
		TemporalAccessor referenceDate = DateTimeFormatter.ISO_LOCAL_DATE.withZone(this.today.getZone()).parse(data.timestamp());
		LocalDate given = LocalDate.from(referenceDate);
		LocalDate expected = LocalDate.ofInstant(this.today.instant(), this.today.getZone());
		if (expected.isEqual(given)) {
			return Optional.of(data.conversions());
		}
		return Optional.empty();
	}
}
