package com.schneide.workshop.testing.unit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class FindConversionRateTest {

	@Test
	void what_Happens_With_Weird_Data2() {
		Function<String, BigDecimal> mockedConverter = Mockito.mock(Function.class);
		when(mockedConverter.apply(eq("XYZ")))
			.thenReturn(BigDecimal.TEN);
		
		FindConversionRate target = new FindConversionRate(
				Currency.getInstance("CHF"),
				mockedConverter);
		Map<String, String> rates = new HashMap<>();
		rates.put("CHF", "XYZ");
		
		Optional<BigDecimal> actual = target.outOf(rates);
		
		assertThat(actual).contains(BigDecimal.TEN);
		//assertThat(actual).isEmpty();
	}
	
	@Test
	void what_Happens_With_Weird_Data() {
		// Arrange
		FindConversionRate target = new FindConversionRate(
				Currency.getInstance("CHF"));
		Map<String, String> rates = new HashMap<>();
		rates.put("CHF", "XYZ");
		
		// Act
		Optional<BigDecimal> actual = target.outOf(rates);
		
		// Assert
		assertThat(actual).isEmpty();
	}

	@Test
	void finds_Matching_Currency2() {
		// Arrange
		FindConversionRate target = new FindConversionRate(
				Currency.getInstance("CHF"));
		Map<String, String> rates = new HashMap<>();
		rates.put("CHF", "33333.333");
		
		// Act
		Optional<BigDecimal> actual = target.outOf(rates);
		
		// Assert
		assertThat(actual).contains(
				BigDecimal.valueOf(33333.333D));
	}

	@Test
	void finds_Matching_Currency3() {
		// Arrange
		FindConversionRate target = dollarfinder();
		Map<String, String> rates = new HashMap<>();
		rates.put("USD", "33333.333");
		rates.put("JPY", "100");
		rates.put("IDR", "20");
		
		// Act
		Optional<BigDecimal> actual = target.outOf(rates);
		
		// Assert
		assertThat(actual).contains(
				BigDecimal.valueOf(33333.333D));
	}

	@Test
	void finds_Matching_Currency() {
		// Arrange
		FindConversionRate target = dollarfinder();
		Map<String, String> rates = new HashMap<>();
		rates.put("USD", "1.256");
		
		// Act
		Optional<BigDecimal> actual = target.outOf(rates);
		
		// Assert
		assertThat(actual).contains(
				BigDecimal.valueOf(1.256D));
	}
	
	@Test	
	void finds_Nothing_In_Empty_Data() {
		// given
		// Arrange
		FindConversionRate target = dollarfinder();
		
		// when
		// Act
		Optional<BigDecimal> actual = target.outOf(
				Collections.emptyMap());
		
		// then
		// Assert
		assertThat(actual).isEmpty();
	}
	
	@Test	
	void finds_Nothing_In_Null() {
		assertThat(
			dollarfinder().outOf(null)
		).isEmpty();
	}

	private FindConversionRate dollarfinder() {
		return new FindConversionRate(
				Currency.getInstance("USD"));
	}
}
