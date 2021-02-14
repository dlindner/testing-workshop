package com.schneide.workshop.testing.mock;

import com.schneide.workshop.testing.mock.stubs.Address;
import com.schneide.workshop.testing.mock.stubs.Discount;
import com.schneide.workshop.testing.mock.stubs.PaymentMethod;

public class Customer {

	private final String surname;
	private final String lastname;
	private final Address shippingAddress;
	private final Address invoiceAddress;
	private final PaymentMethod paymentPreference;
	private final Discount personalDiscount;
	
	public Customer(
			String surname,
			String lastname,
			Address shippingAddress,
			Address invoiceAddress,
			PaymentMethod paymentPreference,
			Discount personalDiscount) {
		super();
		this.surname = surname;
		this.lastname = lastname;
		this.shippingAddress = shippingAddress;
		this.invoiceAddress = invoiceAddress;
		this.paymentPreference = paymentPreference;
		this.personalDiscount = personalDiscount;
	}
	
	/*
	 * all the other getters
	 */
	
	public Discount getPersonalDiscount() {
		return personalDiscount;
	}
}
