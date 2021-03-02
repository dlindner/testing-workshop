package com.schneide.workshop.testing.atdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.Currency;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.schneide.workshop.testing.atdd.internal.Conversion;
import com.schneide.workshop.testing.atdd.internal.InformationExtraction;

public class InformationExtractionTest {
	
	@Test
	void finds_Matching_Currency() {
		InformationExtraction target = new InformationExtraction(Currency.getInstance("USD"));
		Iterable<Conversion> given = Arrays.asList(
				conversionFor("USD", "1.32"));
		
		Optional<BigDecimal> actual = target.extractFor(given);
		
		assertThat(actual).contains(BigDecimal.valueOf(1.32D));
	}
	
	@Test
	void finds_Nothing_If_Currency_Not_Given() {
		InformationExtraction target = new InformationExtraction(Currency.getInstance("USD"));
		Iterable<Conversion> given = Arrays.asList(
				conversionFor("JPY", "127.81"),
				conversionFor("CHF", "1.08"),
				conversionFor("KRW", "1339.59"));
		
		Optional<BigDecimal> actual = target.extractFor(given);
		
		assertThat(actual).isEmpty();
	}
	
	@Test
	void finds_Other_Matching_Currency() {
		InformationExtraction target = new InformationExtraction(Currency.getInstance("KRW"));
		Iterable<Conversion> given = Arrays.asList(
				conversionFor("JPY", "127.81"),
				conversionFor("CHF", "1.08"),
				conversionFor("KRW", "1339.59"));
		
		Optional<BigDecimal> actual = target.extractFor(given);
		
		assertThat(actual).contains(BigDecimal.valueOf(1339.59D));
	}
	
	@Test
	void finds_Nothing_In_Empty_Dataset() {
		InformationExtraction target = new InformationExtraction(Currency.getInstance("USD"));
		
		Optional<BigDecimal> actual = target.extractFor(Collections.emptyList());
		
		assertThat(actual).isEmpty();
	}
	
	@Test
	void rejects_Null_Currency() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			new InformationExtraction(null);
		});
	}

	@Test
	void does_Nothing_On_Null_Dataset() {
		InformationExtraction target = new InformationExtraction(Currency.getInstance("USD"));
		
		Optional<BigDecimal> actual = target.extractFor(null);
		
		assertThat(actual).isEmpty();
	}

	private Conversion conversionFor(String currency, String rate) {
		Conversion result = mock(Conversion.class);
		when(result.currency()).thenReturn(currency);
		when(result.rate()).thenReturn(rate);
		return result;
	}
}
