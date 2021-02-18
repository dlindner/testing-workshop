package com.schneide.workshop.testing.e2e;

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

import com.schneide.workshop.testing.e2e.internal.DataValidation;
import com.schneide.workshop.testing.e2e.internal.ECBData;
import com.schneide.workshop.testing.e2e.internal.ECBData.Conversion;
import com.schneide.workshop.testing.e2e.internal.InformationExtraction;

public class ECBConversionRateIntegrationTest {

	@Test
	@DisplayName("Only extracts if validation succeeds")
	void extracts_USD_conversion_rate() {
		DataValidation validation = new DataValidation(today());
		InformationExtraction extraction = new InformationExtraction();
		
		ECBData given = ecbDataFor(
				today().instant(),
				conversionFor("USD", "7.7777"));
		
		Optional<ECBData> validated = validation.validate(given);
		assertThat(validated).isNotEmpty();
		Optional<BigDecimal> actual = extraction.extractFor(
										validated.get(),
										Currency.getInstance("USD"));
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
	
	private ECBData.Conversion conversionFor(String currency, String rate) {
		Conversion result = mock(Conversion.class);
		when(result.currency()).thenReturn(currency);
		when(result.rate()).thenReturn(rate);
		return result;
	}
	
	private Clock today() {
		return Clock.fixed(
				LocalDateTime.of(2021, 2, 18, 19, 27).toInstant(ZoneOffset.UTC),
				ZoneOffset.UTC);
	}
}
