package com.schneide.workshop.testing.atdd;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Currency;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.schneide.workshop.testing.atdd.internal.DataValidation;
import com.schneide.workshop.testing.atdd.internal.ECBData;
import com.schneide.workshop.testing.atdd.internal.InformationExtraction;
import com.schneide.workshop.testing.atdd.internal.XMLDataParsing;
import com.schneide.workshop.testing.atdd.internal.Conversion;

public class ECBConversionRateSystemTest {

	@Test
	@DisplayName("Reads € to $ conversion rate from XML")
	void extracts_USD_conversion_rate() {
		XMLDataParsing parsing = new XMLDataParsing();
		DataValidation validation = new DataValidation(today());
		InformationExtraction extraction = new InformationExtraction(
				Currency.getInstance("USD"));
		String ecbXML = simulated_ECB_XML_For(
				today().instant(),
				"1.3218");
		
		Optional<ECBData> parsed = parsing.parse(ecbXML);
		Optional<Iterable<Conversion>> validated = parsed.flatMap(validation::validate);
		Optional<BigDecimal> actual = validated.flatMap(extraction::extractFor);
				
		assertThat(actual).contains(BigDecimal.valueOf(1.3218D));
	}
	
	private String simulated_ECB_XML_For(
			Instant date,
			String conversionRate) {
		String today = DateTimeFormatter.ISO_LOCAL_DATE.withZone(ZoneOffset.UTC).format(date);
		String xml = String.join(System.lineSeparator(),
				"<gesmes:Envelope>",
				"<gesmes:subject>Reference rates</gesmes:subject>",
				"<gesmes:Sender>",
				"<gesmes:name>European Central Bank</gesmes:name>",
				"</gesmes:Sender>",
				"<Cube>",
				"<Cube time='$today'>",
				"<Cube currency='USD' rate='$rate'/>",
				"</Cube>",
				"</Cube>",
				"</gesmes:Envelope>");
		return xml.replace("$today", today)
				  .replace("$rate", conversionRate);
	}
	
	private Clock today() {
		return Clock.fixed(
				LocalDateTime.of(2021, 2, 18, 19, 27).toInstant(ZoneOffset.UTC),
				ZoneOffset.UTC);
	}
}
