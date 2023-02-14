package com.miracle.claims.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.miracle.claims.beans.Payment;
import com.miracle.claims.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {
	
	@Autowired
	PaymentService paymentServices;
	
	
	@GetMapping("/getallpayments")
	public ResponseEntity<List<Payment>> getAllPayments() {
		List<Payment> payment = paymentServices.getAllPayments();
		return new ResponseEntity<List<Payment>>(payment , new HttpHeaders(), HttpStatus.OK);
	}
}
