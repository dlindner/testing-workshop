package com.schneide.workshop.testing.mock;

import java.math.BigDecimal;

import com.schneide.workshop.testing.mock.stubs.Price;

public class DiscountCalculation {

	public DiscountCalculation() {
		super();
	}
	
	public Price priceFor(Customer customer, Price original) {
		BigDecimal discount = customer.getPersonalDiscount().asFactor();
		return original.multipliedWith(discount);
	}
}
