package com.schneide.workshop.testing.e2e.internal;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ECBWebFetch {

	public ECBWebFetch() {
		super();
	}
	
	public String conversionRateTableFrom(String url) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(1000);
		connection.setReadTimeout(1000);
		
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		    content.append("\n");
		}
		in.close();
		
		connection.disconnect();
		return content.toString();
	}
}
