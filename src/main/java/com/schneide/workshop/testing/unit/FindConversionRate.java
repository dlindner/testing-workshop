package com.schneide.workshop.testing.unit;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.function.Function;

public class FindConversionRate {
	
	private final String requiredCode;
	private Function<String, BigDecimal> converter;

	public FindConversionRate(
			Currency target) {
		this(target, BigDecimal::new);
	}
	
	public FindConversionRate(
			Currency target,
			Function<String, BigDecimal> converter) {
		super();
		this.converter = converter;
		this.requiredCode = target.getCurrencyCode();
	}

	public Optional<BigDecimal> outOf(Map<String, String> data) {
		if (null == data) {
			return Optional.empty();
		}
		for (Entry<String, String> each : data.entrySet()) {
			if (this.requiredCode.equals(each.getKey())) {
				try {
					BigDecimal result = this.converter.apply(each.getValue());
					return Optional.of(result);
				} catch (NumberFormatException e) {
					return Optional.empty();
				}
			}
		}
		return Optional.empty();
	}
}
