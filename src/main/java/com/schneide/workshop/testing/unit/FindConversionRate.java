package com.schneide.workshop.testing.unit;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

public class FindConversionRate {
	
	private final String requiredCode;

	public FindConversionRate(Currency target) {
		super();
		this.requiredCode = target.getCurrencyCode();
	}

	public Optional<BigDecimal> outOf(Map<String, String> data) {
		if (null == data) {
			return Optional.empty();
		}
		for (Entry<String, String> each : data.entrySet()) {
			if (this.requiredCode.equals(each.getKey())) {
				BigDecimal result = new BigDecimal(each.getValue());
				return Optional.of(result);
			}
		}
		return Optional.empty();
	}
}
