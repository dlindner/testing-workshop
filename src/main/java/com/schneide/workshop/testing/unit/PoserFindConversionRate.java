package com.schneide.workshop.testing.unit;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;
import java.util.Optional;

public class PoserFindConversionRate {

	private final String requiredCode;

	public PoserFindConversionRate(Currency target) {
		super();
		this.requiredCode = target.getCurrencyCode();
	}

	public Optional<BigDecimal> outOf(Map<String, String> data) {
		return Optional.ofNullable(data)
				.map(d -> d.get(this.requiredCode))
				.map(BigDecimal::new);
	}
}
