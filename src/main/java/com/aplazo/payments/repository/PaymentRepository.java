package com.aplazo.payments.repository;

import com.aplazo.payments.entities.Payment;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the interface for Payment Repository
 *
 * @author Enrique Diaz
 * */
@Repository
public interface PaymentRepository extends CrudRepository<Payment, Integer>{

}