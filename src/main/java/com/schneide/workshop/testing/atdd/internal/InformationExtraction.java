package com.schneide.workshop.testing.atdd.internal;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

public class InformationExtraction {

	private final String requiredCode;

	public InformationExtraction(Currency required) {
		this.requiredCode = required.getCurrencyCode();
	}
	
	public Optional<BigDecimal> extractFor(Iterable<Conversion> conversions) {
		if (null == conversions) {
			return Optional.empty();
		}
		for (Conversion each : conversions) {
			if (this.requiredCode.equals(each.currency())) {
				BigDecimal result = new BigDecimal(each.rate());
				return Optional.of(result);
			}
		}
		return Optional.empty();
	}
}