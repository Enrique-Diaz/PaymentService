package com.aplazo.payments.repository;

import com.aplazo.payments.entities.Credit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * This is the interface for Credit Repository
 *
 * @author Enrique Diaz
 * */
@Repository
public interface CreditRepository extends CrudRepository<Credit, Integer>{

}