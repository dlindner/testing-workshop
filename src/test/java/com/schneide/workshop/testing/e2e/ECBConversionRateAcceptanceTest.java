package com.schneide.workshop.testing.e2e;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ECBConversionRateAcceptanceTest {

	@Test
	@DisplayName("ECB call yield € to $ conversion rate")
	void fetches_Euro_To_Dollar_Rate() throws IOException {
		ECBConversionRate target = new ECBConversionRate(
				"https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
		
		Optional<BigDecimal> actual = target.currentFor(
										Currency.getInstance("USD"));
		
		assertThat(actual).isNotEmpty();
	}
}
