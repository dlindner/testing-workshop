package com.schneide.workshop.testing.atrip;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

public class UpdateServerTest {

	@Test void resolvesHostname() {
		UpdateServer target = new UpdateServer("8.8.8.8");
		
		// with internet connection and DNS up
		assertThat(target.hostname()).isEqualTo("dns.google");
		
		// without internet connection or DNS not accessible
		//assertThat(target.hostname()).isEqualTo("8.8.8.8");
	}
}
