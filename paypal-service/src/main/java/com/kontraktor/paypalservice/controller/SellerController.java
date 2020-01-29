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
import com.kontraktor.paypalservice.model.PaymentRef;
import com.kontraktor.paypalservice.model.RedirectUrls;
import com.kontraktor.paypalservice.model.SellerInfo;
import com.kontraktor.paypalservice.service.PaymentService;
import com.kontraktor.paypalservice.service.SellerService;
import com.paypal.api.payments.Amount;
import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payer;
import com.paypal.api.payments.Payment;
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
	public ResponseEntity<RedirectUrls> createOrder(@RequestBody Order order){
		
		System.out.println(order.toString());
		SellerInfo seller = sellerService.findOne(order.getSeller().getId());
		APIContext context = new APIContext(seller.getClientId(), seller.getClientSecret(), "sandbox");
		Payer payer = new Payer();
		payer.setPaymentMethod("paypal");

		// Set redirect URLs
		
		com.paypal.api.payments.RedirectUrls redirectUrls = new com.paypal.api.payments.RedirectUrls();
		
		
		redirectUrls.setCancelUrl("http://localhost:8001/api/paypal/fail");
		redirectUrls.setReturnUrl("http://localhost:8001/api/paypal/success");

		System.out.println(order.getRedirectUrls().getReturn_url());

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
			System.out.println("Creating order...");
		  Payment createdPayment = payment.create(context);
			System.out.println("Order created...");

		  PaymentRef pf = new PaymentRef(createdPayment.getId(), seller);
		  pf.setOrder(order);
		  paymentService.save(pf);
		  Iterator<Links> links = createdPayment.getLinks().iterator();
		  while (links.hasNext()) {
		    Links link =  links.next();
		    if (link.getRel().equalsIgnoreCase("approval_url")) {
		    	//System.out.println(link.getHref());
		    	RedirectUrls urls = new RedirectUrls();
		    	urls.setReturn_url(link.getHref());
		       return  new ResponseEntity<>(urls, HttpStatus.OK);
		    }
		  }
		} catch (PayPalRESTException e) {
		       return  new ResponseEntity<>( HttpStatus.NOT_FOUND);
		}
	       return  new ResponseEntity<>( HttpStatus.NOT_ACCEPTABLE);

	}
	@GetMapping("/success")
	public String success(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		PaymentRef ref = paymentService.findOne(paymentId);
		try {
			paymentService.execute(paymentId, payerId);			
			return "redirect:" + ref.getOrder().getRedirectUrls().getReturn_url();
		} catch (PayPalRESTException e) {
			return "redirect:" + ref.getOrder().getRedirectUrls().getCancel_url() ;

		}
	}
	@GetMapping("/fail")
	public String fail(){
		return "redirect:" + "http://localhost:4200/fail";
	}
}
