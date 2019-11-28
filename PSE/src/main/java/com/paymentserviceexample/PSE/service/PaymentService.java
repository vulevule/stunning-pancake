package com.paymentserviceexample.PSE.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymentserviceexample.PSE.model.Payment;
import com.paymentserviceexample.PSE.model.User;
import com.paymentserviceexample.PSE.repository.UserRepository;

@Service
public class PaymentService {
	@Autowired
	UserRepository userRepo;
	public void executePayment(Payment payment) throws Exception{
		User user = userRepo.findByEmail(payment.getEmail());
		if (user == null){
			throw new Exception("Not found");
		}
		if (user.getBalance() < payment.getTotal()){
			throw new Exception("Not enough funds");
		}
		user.setBalance(user.getBalance() - payment.getTotal());
		
	}
}
