package com.aplazo.payments;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@DisplayName("PaymentsApplicationTest")
class PaymentsApplicationTest {

	PaymentsApplication cut = new PaymentsApplication();

	@Test
	@DisplayName("Constructor")
	public void testDispatchServiceApplication() {
		PaymentsApplication.main(new String[] {});
		assertNotNull(cut, "The call to the constructor did not return the expected results");
	}

	@Test
	@DisplayName("Swagger API Bean")
	public void testSwaggerBean() {
		assertNotNull(cut.api(Mockito.any(), Mockito.any()), "The call to the constructor did not return the expected results");
	}
}