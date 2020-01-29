package com.kontraktor.bitcoinservice.service;

import com.kontraktor.bitcoinservice.model.PaymentRef;
import com.kontraktor.bitcoinservice.repository.PaymentRepository;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
	@Autowired
	private PaymentRepository repo;
	public PaymentRef findOne(String paymentId){
		return repo.findByPaymentId(paymentId);
	}
	public PaymentRef save(PaymentRef pr){
		return repo.save(pr);
	}
	
	public void execute(String paymentId, String payerId) throws PayPalRESTException{
		Payment payment = new Payment();
		payment.setId(paymentId);
		PaymentRef pr = findOne(paymentId);
		APIContext context = new APIContext(pr.getSeller().getClientId(), pr.getSeller().getClientSecret(), "sandbox");

		PaymentExecution paymentExecution = new PaymentExecution();
		paymentExecution.setPayerId(payerId);
		
		try {
		  payment.execute(context, paymentExecution);
		} catch (PayPalRESTException e) {
			  throw e;

		}
	}
}
