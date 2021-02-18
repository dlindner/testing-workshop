package com.schneide.workshop.testing.e2e;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

public class ECBConversionRate implements EuroConversionRate {
	
	private final String url;

	public ECBConversionRate(String url) {
		super();
		this.url = url;
	}
	
	@Override
	public Optional<BigDecimal> currentFor(Currency target) {
		System.out.println(target.getCurrencyCode());
		// TODO: 
		return Optional.empty();
	}
}
