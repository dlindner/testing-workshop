package com.schneide.workshop.testing.unit;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class FindConversionRateTest {
	
	@Test
	void finds_Matching_Currency() {
		// arrange
		FindConversionRate target = new FindConversionRate(Currency.getInstance("USD"));
		Map<String, String> rates = new HashMap<>();
		rates.put("USD", "1.32");
		
		// act
		Optional<BigDecimal> actual = target.outOf(rates);
		
		// assert
		assertThat(actual).contains(BigDecimal.valueOf(1.32D));
	}
	
	@Test
	void finds_Nothing_If_Currency_Not_Given() {
		// arrange
		FindConversionRate target = new FindConversionRate(Currency.getInstance("USD"));
		Map<String, String> rates = new HashMap<>();
		rates.put("JPY", "127.81");
		rates.put("CHF", "1.08");
		rates.put("KRW", "1339.59");
		
		// act
		Optional<BigDecimal> actual = target.outOf(rates);
		
		// assert
		assertThat(actual).isEmpty();
	}
	
	@Test
	void finds_Other_Matching_Currency() {
		// arrange
		FindConversionRate target = new FindConversionRate(Currency.getInstance("KRW"));
		Map<String, String> rates = new HashMap<>();
		rates.put("JPY", "127.81");
		rates.put("CHF", "1.08");
		rates.put("KRW", "1339.59");
		
		// act
		Optional<BigDecimal> actual = target.outOf(rates);
		
		// assert
		assertThat(actual).contains(BigDecimal.valueOf(1339.59D));
	}
	
	@Test
	void finds_Nothing_In_Empty_Dataset() {
		// arrange
		FindConversionRate target = new FindConversionRate(Currency.getInstance("USD"));
		
		// act
		Optional<BigDecimal> actual = target.outOf(Collections.emptyMap());
		
		// assert
		assertThat(actual).isEmpty();
	}
	
	@Test
	void rejects_Null_Currency() {
		Assertions.assertThrows(NullPointerException.class, () -> {
			new FindConversionRate(null);
		});
	}

	@Test
	void does_Nothing_On_Null_Dataset() {
		// arrange
		FindConversionRate target = new FindConversionRate(Currency.getInstance("USD"));
		
		// act
		Optional<BigDecimal> actual = target.outOf(null);
		
		// assert
		assertThat(actual).isEmpty();
	}
}
