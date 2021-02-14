package com.schneide.workshop.testing;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.schneide.workshop.testing.Fizz;

class FizzTest {

	@Test void fizzes() {
		assertThat(Fizz.of(3)).isEqualTo("fizz");
	}
}
