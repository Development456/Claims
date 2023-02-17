package com.miracle.claims.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.miracle.claims.beans.Order;
import com.miracle.claims.beans.Payment;
import com.miracle.claims.repository.PaymentRepository;

@Component
@Service
public class PaymentService implements PaymentServiceImpl{
	@Autowired
	PaymentRepository paymentRepository;
	
	@Autowired
	MongoOperations mongoOperations; 
	
	@Override
	public List<Payment> getAllPayments(){
		return paymentRepository.findAll();
	}
	
	@Override
	public Payment getPaymentById(int paymentId) {
		return paymentRepository.findByPaymentId(paymentId);
	}
	
	@Override
	public ResponseEntity<Payment> updatePayment(int paymentId, Payment payment){
		try {
			Payment payments = paymentRepository.findByPaymentId(paymentId);
			payments.setPaymentId(payment.getPaymentId());
			payments.setInvoiceAmount(payment.getInvoiceAmount());
			payments.setAccuralAmount(payment.getAccuralAmount());
			payments.setCurrencyType(payment.getCurrencyType());
			payments.setGlCode(payment.getGlCode());
			payments.setCostCenter(payment.getCostCenter());
			payments.setPaymentDate(payment.getPaymentDate());
			payments.setPaymentReference(payment.getPaymentReference());
			payments.setApVendor(payment.getApVendor());
			paymentRepository.save(payments);
			return new ResponseEntity<Payment>(HttpStatus.OK);
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}		
	}
}
