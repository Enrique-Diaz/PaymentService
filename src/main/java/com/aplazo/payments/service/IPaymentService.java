package com.aplazo.payments.service;

import com.aplazo.payments.dto.CreditRequest;
import com.aplazo.payments.dto.PaymentResponse;

import java.util.List;

/**
 * This is the interface of Payment Services
 *
 * @author Enrique Diaz
 * */
public interface IPaymentService {

    List<PaymentResponse> calculatePayments(CreditRequest creditRequest);
}