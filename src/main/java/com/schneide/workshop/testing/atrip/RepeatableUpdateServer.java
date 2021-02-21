package com.schneide.workshop.testing.atrip;

import java.net.InetSocketAddress;

public class RepeatableUpdateServer {

	private final InetSocketAddress server;
	
	public RepeatableUpdateServer(String ip) {
		super();
		this.server = InetSocketAddress.createUnresolved(ip, 80);
	}
	
	public String hostname() {
		return this.server.getHostName();
	}
}
