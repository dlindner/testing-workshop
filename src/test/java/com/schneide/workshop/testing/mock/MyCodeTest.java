package com.schneide.workshop.testing.mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MyCodeTest {

	@Test
	void testName(@Mock Ref_t nullRef) {
		//Ref_t nullRef = mock(Ref_t.class);
		when(nullRef.isNotNull()).thenReturn(false);
		
		MyCode target = new MyCode(nullRef);
		
		assertThat(
			target.isValid()
		).isFalse();
	}
}
