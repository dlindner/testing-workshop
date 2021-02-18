package com.schneide.workshop.testing.e2e.internal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Currency;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.schneide.workshop.testing.e2e.internal.ECBData.Conversion;

public class InformationExtractionTest {

	@Test void extracts_matching_conversion_rate() {
		InformationExtraction target = new InformationExtraction();
		ECBData given = ecbDataFor(
				"2021-02-18",
				conversionFor("USD", "1.3218"));
		
		Optional<BigDecimal> actual = target.extractFor(
										given,
										Currency.getInstance("USD"));
		
		assertThat(actual).contains(BigDecimal.valueOf(1.3218D));
	}

	@Test void extracts_for_every_date() {
		InformationExtraction target = new InformationExtraction();
		ECBData given = ecbDataFor(
				"2021-07-21",
				conversionFor("USD", "1.3218"));
		
		Optional<BigDecimal> actual = target.extractFor(
										given,
										Currency.getInstance("USD"));
		
		assertThat(actual).contains(BigDecimal.valueOf(1.3218D));
	}

	@Test void no_conversion_rate_if_wrong_currency() {
		InformationExtraction target = new InformationExtraction();
		ECBData given = ecbDataFor(
				"2021-02-18",
				conversionFor("JPY", "2.2107"));
		
		Optional<BigDecimal> actual = target.extractFor(
				given,
				Currency.getInstance("USD"));
		
		assertThat(actual).isEmpty();
	}
	
	private ECBData ecbDataFor(
			String date,
			Conversion... rates) {
		return new ECBData() {
			@Override
			public String timestamp() {
				return date;
			}
			@Override
			public Iterable<Conversion> conversions() {
				return Arrays.asList(rates);
			}
		};
	}
	
	private ECBData.Conversion conversionFor(String currency, String rate) {
		Conversion result = mock(Conversion.class);
		when(result.currency()).thenReturn(currency);
		when(result.rate()).thenReturn(rate);
		return result;
	}
}
