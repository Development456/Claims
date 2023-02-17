package com.miracle.claims.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.miracle.claims.beans.Payment;

@Repository
public interface PaymentRepository extends MongoRepository<Payment, Long> {

	Payment findByPaymentId(int paymentId);


}

