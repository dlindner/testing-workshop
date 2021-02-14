package com.schneide.workshop.testing.mock.stubs;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Objects;

public class Price {
	
	private BigDecimal amount;
	private Currency currency;

	public Price(BigDecimal amount, Currency currency) {
		super();
		this.amount = amount;
		this.currency = currency;
	}
	
	public Price multipliedWith(BigDecimal factor) {
		return new Price(
				amount.multiply(factor), 
				currency);
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, currency);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Price other = (Price) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(currency, other.currency);
	}

	@Override
	public String toString() {
		return "Price [amount=" + amount + ", currency=" + currency + "]";
	}
}
