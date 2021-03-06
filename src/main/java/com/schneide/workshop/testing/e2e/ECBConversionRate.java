package com.schneide.workshop.testing.e2e;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Clock;
import java.util.Currency;
import java.util.Optional;

import com.schneide.workshop.testing.e2e.internal.Conversion;
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
		final Clock clock = Clock.systemDefaultZone();
		XMLDataParsing parsing = new XMLDataParsing();
		DataValidation validation = new DataValidation(clock);
		InformationExtraction extraction = new InformationExtraction(target);
		
		String fetched = new ECBWebFetch().conversionRateTableFrom(this.url);
		
		Optional<ECBData> parsed = parsing.parse(fetched);
		Optional<Iterable<Conversion>> validated = parsed.flatMap(validation::validate);
		Optional<BigDecimal> result = validated.flatMap(extraction::extractFor);
		return result;
	}
}
