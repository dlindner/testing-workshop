package com.schneide.workshop.testing.atdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Currency;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.schneide.workshop.testing.atdd.internal.Conversion;
import com.schneide.workshop.testing.atdd.internal.DataValidation;
import com.schneide.workshop.testing.atdd.internal.ECBData;
import com.schneide.workshop.testing.atdd.internal.InformationExtraction;

public class DataValidationAndExtractionIntegrationTest {

	@Test
	@DisplayName("Only extracts if validation succeeds")
	void extracts_USD_conversion_rate() {
		DataValidation validation = new DataValidation(today());
		InformationExtraction extraction = new InformationExtraction(
				Currency.getInstance("USD"));
		
		ECBData given = ecbDataFor(
				today().instant(),
				conversionFor("USD", "7.7777"));
		
		Optional<Iterable<Conversion>> validated = validation.validate(given);
		assertThat(validated).isNotEmpty();
		assertThat(validated.get()).hasAtLeastOneElementOfType(Conversion.class);
		
		Optional<BigDecimal> actual = extraction.extractFor(
										validated.get());
		assertThat(actual).contains(BigDecimal.valueOf(7.7777D));
	}
	
	private ECBData ecbDataFor(
			Instant date,
			Conversion... rates) {
		return new ECBData() {
			@Override
			public String timestamp() {
				return DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneOffset.UTC).format(date);
			}
			@Override
			public Iterable<Conversion> conversions() {
				return Arrays.asList(rates);
			}
		};
	}
	
	private Conversion conversionFor(String currency, String rate) {
		return new Conversion() {
			@Override
			public String rate() {
				return rate;
			}
			@Override
			public String currency() {
				return currency;
			}
		};
//		Conversion result = mock(Conversion.class);
//		when(result.currency()).thenReturn(currency);
//		when(result.rate()).thenReturn(rate);
//		return result;
	}
	
	private Clock today() {
		return Clock.fixed(
				LocalDateTime.of(2021, 2, 18, 19, 27).toInstant(ZoneOffset.UTC),
				ZoneOffset.UTC);
	}
}