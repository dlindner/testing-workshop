package com.schneide.workshop.testing.mock;

import java.math.BigDecimal;
import java.util.Currency;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.schneide.workshop.testing.mock.Customer;
import com.schneide.workshop.testing.mock.DiscountCalculation;
import com.schneide.workshop.testing.mock.stubs.Price;

@ExtendWith(MockitoExtension.class)
public class DiscountCalculationTest {

	@Test
	void noDiscountDoesntChangePrice(@Mock Customer customer) {
		Mockito.when(customer.getPersonalDiscount())
			   .thenReturn(() -> BigDecimal.ONE);
		
		DiscountCalculation target = new DiscountCalculation();
		Price actual = target.priceFor(customer, oneHundredEuro);
		Assertions.assertThat(actual).isEqualTo(oneHundredEuro);
	}
	
	private static final Price oneHundredEuro = new Price(
											BigDecimal.valueOf(100),
											Currency.getInstance("EUR"));
}
