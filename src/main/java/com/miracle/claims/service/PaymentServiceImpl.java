package com.miracle.claims.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.miracle.claims.beans.Payment;

public interface PaymentServiceImpl {
	public List<Payment> getAllPayments();

	public Payment getPaymentById(int paymentId);

	public ResponseEntity<Payment> updatePayment(int paymentId, Payment payment);
}
