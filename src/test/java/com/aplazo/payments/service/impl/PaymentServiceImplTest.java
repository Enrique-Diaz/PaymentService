package com.aplazo.payments.service.impl;

import com.aplazo.payments.dto.CreditRequest;
import com.aplazo.payments.repository.CreditRepository;
import com.aplazo.payments.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@DisplayName("PaymentServiceImplTest")
public class PaymentServiceImplTest {

    @Mock
    public CreditRepository creditRequestRepository;

    @Mock
    public PaymentRepository paymentRepository;

    @Mock
    public Logger logger;

    @InjectMocks
    PaymentServiceImpl cut;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("constructor")
    void testPaymentService() {
        assertNotNull(cut, "The constructor did not return the expected results");
    }

    @Nested
    @DisplayName("Test calculatePayments method")
    class testCalculatePayments {
        @Test
        @DisplayName("Test calculate payments")
        void testCalculatePayment() {
            var creditRequest = new CreditRequest();
            creditRequest.setAmount(100D);
            creditRequest.setRate(1.08D);
            creditRequest.setTerm(5);

            var response = cut.calculatePayments(creditRequest);

            verify(creditRequestRepository, times(1)).save(any());
            verify(paymentRepository, times(1)).saveAll(any());

            assertNotNull(response);
            assertEquals(5, response.size());
        }
    }
}