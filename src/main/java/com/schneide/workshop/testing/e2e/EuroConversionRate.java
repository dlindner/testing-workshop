package com.schneide.workshop.testing.e2e;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Currency;
import java.util.Optional;

public interface EuroConversionRate {

	Optional<BigDecimal> currentFor(Currency target) throws IOException;
}
