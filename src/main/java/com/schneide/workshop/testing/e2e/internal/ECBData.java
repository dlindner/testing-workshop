package com.schneide.workshop.testing.e2e.internal;

public interface ECBData {
	
	String timestamp();
	
	Iterable<Conversion> conversions();

	public static interface Conversion {
		
		String currency();
		
		String rate();
	}
}
