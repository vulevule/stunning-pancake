package com.kontraktor.paypalservice.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kontraktor.paypalservice.model.Order;
import com.kontraktor.paypalservice.model.OrderResponse;
import com.kontraktor.paypalservice.model.PaymentRef;
import com.kontraktor.paypalservice.model.SellerInfo;
import com.kontraktor.paypalservice.service.PaymentService;
import com.kontraktor.paypalservice.service.SellerService;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
import com.paypal.api.payments.PaymentExecution;
import com.paypal.api.payments.RedirectUrls;
import com.paypal.api.payments.Transaction;
import com.paypal.base.rest.APIContext;
import com.paypal.base.rest.PayPalRESTException;

@Controller
@RequestMapping("/api/paypal")
public class SellerController {
	@Autowired
	private SellerService sellerService;
	@Autowired
	private PaymentService paymentService;

	@PostMapping("/order")
	public String createOrder(@RequestBody Order order){
		SellerInfo seller = sellerService.findOne(order.getSeller().getId());
		APIContext context = new APIContext(seller.getClientId(), seller.getClientSecret(), "sandbox");
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		// Set redirect URLs
		RedirectUrls redirectUrls = new RedirectUrls();
		redirectUrls.setCancelUrl("http://localhost:8001/api/paypal/fail");
		redirectUrls.setReturnUrl("http://localhost:8001/api/paypal/success");

		

		// Payment amount
		Amount amount = new Amount();
		amount.setCurrency("USD");
		// Total must be equal to sum of shipping, tax and subtotal.
		amount.setTotal(order.getAmount() + "");

		// Transaction information
		Transaction transaction = new Transaction();
		transaction.setAmount(amount);
		transaction
		  .setDescription("Description");

		// Add transaction to a list
		List<Transaction> transactions = new ArrayList<Transaction>();
		transactions.add(transaction);

		// Add payment details
		Payment payment = new Payment();
		payment.setIntent("sale");
		payment.setPayer(payer);
		payment.setRedirectUrls(redirectUrls);
		payment.setTransactions(transactions);
		
		// Create payment
		try {
		  Payment createdPayment = payment.create(context);
		  PaymentRef pf = new PaymentRef(createdPayment.getId(), seller);
		  paymentService.save(pf);
		  Iterator<Links> links = createdPayment.getLinks().iterator();
		  while (links.hasNext()) {
		    Links link =  links.next();
		    if (link.getRel().equalsIgnoreCase("approval_url")) {
		    	System.out.println(link.getHref());
		       return "redirect:" + link.getHref();
		    }
		  }
		} catch (PayPalRESTException e) {
		    return "fail";
		}
		return "fail";
	}
	@GetMapping("/success")
	public ResponseEntity<OrderResponse> success(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		try {
			paymentService.execute(paymentId, payerId);
		  return new ResponseEntity<>(new OrderResponse("success"), HttpStatus.OK );
		} catch (PayPalRESTException e) {
			  return new ResponseEntity<>(new OrderResponse("fail"), HttpStatus.OK );

		}
	}
	@GetMapping("/fail")
	public ResponseEntity<OrderResponse> fail(){
		return new ResponseEntity<>(new OrderResponse("fail"), HttpStatus.NOT_ACCEPTABLE);
	}
}
