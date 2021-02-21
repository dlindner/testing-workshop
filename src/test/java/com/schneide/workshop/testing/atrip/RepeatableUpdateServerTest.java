package com.schneide.workshop.testing.atrip;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.UnknownHostException;

import org.junit.jupiter.api.Test;

public class RepeatableUpdateServerTest {

	@Test void doesntResolveHostname() throws UnknownHostException {
		final String ipAddress = "8.8.8.8";
		RepeatableUpdateServer target = new RepeatableUpdateServer(ipAddress);
		assertThat(target.hostname()).isEqualTo(ipAddress);
	}
}
