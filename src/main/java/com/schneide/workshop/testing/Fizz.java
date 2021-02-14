package com.schneide.workshop.testing;

public class Fizz {

	public static String of(int number) {
		if (number % 3 == 0) {
			return "fizz";
		}
		return String.valueOf(number);
	}
}
