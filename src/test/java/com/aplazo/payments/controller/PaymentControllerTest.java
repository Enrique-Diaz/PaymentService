package com.aplazo.payments.controller;

import com.aplazo.payments.dto.CreditRequest;
import com.aplazo.payments.dto.PaymentResponse;
import com.aplazo.payments.service.IPaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@DisplayName("PaymentControllerTest")
public class PaymentControllerTest {

    @Mock
    public Logger logger;

    @Mock
    public IPaymentService iPaymentService;

    @InjectMocks
    PaymentController cut;

    @BeforeEach
    void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    @DisplayName("constructor")
    void testPaymentController() {
        assertNotNull(cut, "The constructor did not return the expected results");
    }

    @Nested
    @DisplayName("Test payments method")
    class testPayments {
        @Test
        @DisplayName("Test payments error bad request")
        void testPaymentsError() {

            var response = cut.payments(new CreditRequest());

            assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        }

        @Test
        @DisplayName("Test payment success request")
        void testPaymentsSuccess() {
            var creditRequest = new CreditRequest();
            creditRequest.setAmount(100D);
            creditRequest.setRate(1.08D);
            creditRequest.setTerm(5);

            var paymentResponse = new PaymentResponse();
            paymentResponse.setPaymentDate(Calendar.getInstance().getTime());
            paymentResponse.setAmount(25D);
            paymentResponse.setPaymentNumber(1);

            var payments = new ArrayList<PaymentResponse>();
            payments.add(paymentResponse);

            when(iPaymentService.calculatePayments(any())).thenReturn(payments);

            var response = cut.payments(creditRequest);

            assertNotNull(response);
            assertEquals(HttpStatus.CREATED, response.getStatusCode());

            var paymentsResponse = (List<PaymentResponse>) response.getBody();
            assertNotNull(paymentsResponse);
            assertEquals(1, paymentsResponse.size());
            assertEquals(25D, paymentsResponse.get(0).getAmount());
        }
    }
}