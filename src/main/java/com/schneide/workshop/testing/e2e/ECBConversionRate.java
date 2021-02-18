package com.schneide.workshop.testing.e2e;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.util.Currency;
import java.util.Optional;

import com.schneide.workshop.testing.e2e.internal.DataValidation;
import com.schneide.workshop.testing.e2e.internal.ECBData;
import com.schneide.workshop.testing.e2e.internal.ECBWebFetch;
import com.schneide.workshop.testing.e2e.internal.InformationExtraction;
import com.schneide.workshop.testing.e2e.internal.XMLDataParsing;

public class ECBConversionRate implements EuroConversionRate {
	
	private final String url;

	public ECBConversionRate(String url) {
		super();
		this.url = url;
	}
	
	@Override
	public Optional<BigDecimal> currentFor(Currency target) throws IOException {
		String fetched = new ECBWebFetch().conversionRateTableFrom(this.url);
		Optional<ECBData> parsed = new XMLDataParsing().parse(fetched);
		Optional<ECBData> validated = parsed.flatMap(data -> new DataValidation(Clock.systemDefaultZone()).validate(data));
		Optional<BigDecimal> result = validated.flatMap(data -> new InformationExtraction().extractFor(data, target));
		return result;
	}
}
