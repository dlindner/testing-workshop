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
	
	public String conversionRateTable() throws IOException {
		URL url = new URL("https://www.ecb.europa.eu/stats/eurofxref/eurofxref-daily.xml");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setConnectTimeout(1000);
		connection.setReadTimeout(1000);
		
		BufferedReader in = new BufferedReader(
				  new InputStreamReader(connection.getInputStream()));
		String inputLine;
		StringBuffer content = new StringBuffer();
		while ((inputLine = in.readLine()) != null) {
		    content.append(inputLine);
		}
		in.close();
		
		connection.disconnect();
		return content.toString();
	}
	
	public static void main(String[] args) throws IOException {
		ECBWebFetch target = new ECBWebFetch();
		System.out.println(target.conversionRateTable());
	}
}
