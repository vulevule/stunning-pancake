package com.paymentserviceexample.PSE.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.paymentserviceexample.PSE.model.Payment;
import com.paymentserviceexample.PSE.model.PaymentResponse;
import com.paymentserviceexample.PSE.model.PaymentResponse.Status;
import com.paymentserviceexample.PSE.service.PaymentService;

@Controller
@RequestMapping("api")
public class PaymentController {
	@Autowired PaymentService paymentService;
	@RequestMapping(method = RequestMethod.POST, value="/pay")
	public PaymentResponse pay(@RequestBody Payment payment){
		try{
			paymentService.executePayment(payment);
			return new PaymentResponse(payment, Status.SUCCESS);
		}catch(Exception e){
			if (e.getMessage().equals("Not found")){
				return new PaymentResponse(payment, Status.AUTHENTICATION_FAIL);
			}else if (e.getMessage().equals("Not enough funds")){
				return new PaymentResponse(payment, Status.NOT_ENOUGH_FUNDS);
			}else{
				return new PaymentResponse(payment, Status.ERROR);
			}
		}
	}
}
