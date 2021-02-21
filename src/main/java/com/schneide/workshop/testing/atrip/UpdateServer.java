package com.schneide.workshop.testing.atrip;

import java.net.InetSocketAddress;

public class UpdateServer {

	private final InetSocketAddress server;
	
	public UpdateServer(String ip) {
		super();
		this.server = new InetSocketAddress(ip, 80);
	}
	
	public String hostname() {
		return this.server.getHostName();
	}
}
