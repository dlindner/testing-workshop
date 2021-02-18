package com.schneide.workshop.testing.e2e.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.schneide.workshop.testing.e2e.internal.ECBData.Conversion;

public class XMLDataParsing {

	public XMLDataParsing() {
		super();
	}
	
	public Optional<ECBData> parse(String xml) {
		try (
			Scanner lines = new Scanner(xml);
		) {
			final List<String> timestamps = new ArrayList<>();
			final List<Conversion> conversions = new ArrayList<>();
			while (lines.hasNextLine()) {
				String line = lines.nextLine();
				Optional<String> maybeTimestamp = parseDate(line);
				maybeTimestamp.ifPresent(timestamps::add);
				Optional<ECBData.Conversion> maybeConversion = parseConversion(line);
				maybeConversion.ifPresent(conversions::add);
			}
			ECBData result = null;
			if (!timestamps.isEmpty()) {
				result = new ECBData() {
					@Override
					public String timestamp() {
						return timestamps.get(0);
					}
					@Override
					public Iterable<Conversion> conversions() {
						return conversions;
					}
				};
			}
			return Optional.ofNullable(result);
		}
	}
	
	private Optional<ECBData.Conversion> parseConversion(String line) {
		Pattern datePattern = Pattern.compile(".+Cube\\ currency='(.{3})'\\ rate='([^']*)'.+");
		Matcher matcher = datePattern.matcher(line);
		if (!matcher.find()) {
			return Optional.empty();
		}
		Conversion result = new Conversion() {
			@Override
			public String rate() {
				return matcher.group(2);
			}
			@Override
			public String currency() {
				return matcher.group(1);
			}
		};
		return Optional.of(result);
	}
	
	private Optional<String> parseDate(String line) {
		Pattern datePattern = Pattern.compile(".+Cube\\ time='(.{10}).+");
		Matcher matcher = datePattern.matcher(line);
		if (!matcher.find()) {
			return Optional.empty();
		}
		return Optional.of(matcher.replaceAll("$1"));
	}
}
