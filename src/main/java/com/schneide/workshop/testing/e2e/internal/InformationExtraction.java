package com.schneide.workshop.testing.e2e.internal;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

import com.schneide.workshop.testing.e2e.internal.ECBData.Conversion;

public class InformationExtraction {
	
	public InformationExtraction() {
		super();
	}

	public Optional<BigDecimal> extractFor(ECBData data, Currency target) {
		for (Conversion each : data.conversions()) {
			if (target.getCurrencyCode().equals(each.currency())) {
				BigDecimal result = new BigDecimal(each.rate());
				return Optional.of(result);
			}
		}
		return Optional.empty();
	}
}
