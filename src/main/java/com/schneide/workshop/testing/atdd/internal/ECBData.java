package com.schneide.workshop.testing.atdd.internal;

public interface ECBData {

	String timestamp();
	
	Iterable<Conversion> conversions();
}