package com.schneide.workshop.testing.unit;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FindConversionRateTest {

	@Test
	void finds_Nothing_On_Empty_Dataset() {
		// arrange
		FindConversionRate cut = new FindConversionRate(Currency.getInstance("EUR"));
		
		// act
		Optional<BigDecimal> actual = cut.outOf(Collections.emptyMap());
		
		// assert
		Assertions.assertThat(actual).isEmpty();
	}
}
