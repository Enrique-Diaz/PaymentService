package com.aplazo.payments.service.impl;

import com.aplazo.payments.dto.CreditRequest;
import com.aplazo.payments.dto.PaymentResponse;
import com.aplazo.payments.entities.Credit;
import com.aplazo.payments.entities.Payment;
import com.aplazo.payments.repository.CreditRepository;
import com.aplazo.payments.repository.PaymentRepository;
import com.aplazo.payments.service.IPaymentService;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * This is the implementation of Payment Services
 *
 * @author Enrique Diaz
 * */
@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    public CreditRepository creditRequestRepository;

    @Autowired
    public PaymentRepository paymentRepository;

    @Autowired
    public Logger logger;

    @Override
    @Transactional
    public List<PaymentResponse> calculatePayments(CreditRequest creditRequest) {
        logger.info("Inside PaymentServiceImpl.calculatePayments");

        // Convert from Request to Entity Object
        var credit = new Credit();
        BeanUtils.copyProperties(creditRequest, credit);

        logger.info("Calculating amountToPay...");
        /*
         * Amount times rate divided by term
         * i.e.
         * amountToPay = 1000 * 1.08 / 5
         */
        var amountToPay = credit.getAmount() * credit.getRate() / credit.getTerm();
        var payments = new ArrayList<Payment>();

        logger.info("Calculating payment dates...");
        for (int i = 0; i < credit.getTerm(); i++){
            var payment = new Payment();
            payment.setAmount(amountToPay);
            payment.setPaymentNumber(i+1);
            payment.setCreditRequest(credit);

            var paymentDate = Calendar.getInstance();

            /*
             * Add 15 days per each term, first date must be the
             * same as the current date in which the payment need to be done.
             */
            paymentDate.add(Calendar.DAY_OF_MONTH, i*15);
            payment.setPaymentDate(paymentDate.getTime());
            payments.add(payment);
        }

        logger.info("Save credit requested...");
        creditRequestRepository.save(credit);
        logger.info("Save payments for given credit...");
        paymentRepository.saveAll(payments);

        // Convert from Entity to Response Object
        var paymentsResponse = new ArrayList<PaymentResponse>();
        for(Payment payment : payments){
            var paymentResponse = new PaymentResponse();
            BeanUtils.copyProperties(payment, paymentResponse);

            paymentsResponse.add(paymentResponse);
        }

        logger.info("Returning paymentsResponse...");
        return paymentsResponse;
    }
}