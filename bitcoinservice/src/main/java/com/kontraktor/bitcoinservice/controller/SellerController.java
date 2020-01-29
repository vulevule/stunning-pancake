package com.kontraktor.bitcoinservice.controller;

import com.kontraktor.bitcoinservice.service.BTCClient;
import com.kontraktor.bitcoinservice.model.Order;
import com.kontraktor.bitcoinservice.model.RedirectUrls;
import com.kontraktor.bitcoinservice.model.SellerInfo;
import com.kontraktor.bitcoinservice.service.PaymentService;
import com.kontraktor.bitcoinservice.service.SellerService;
//import com.paypal.api.payments.*;
//import com.paypal.base.rest.APIContext;
//import com.paypal.base.rest.PayPalRESTException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

@Controller
@RequestMapping("/api/bitcoin")
public class SellerController {

	private final BTCClient bTCClient;

	@Autowired
	SellerController(BTCClient bTCClient) {
		this.bTCClient = bTCClient;
	}

	@Autowired
	private SellerService sellerService;
	@Autowired
	private PaymentService paymentService;

	@PostMapping("/order")
	public ResponseEntity<Object> createOrder(@RequestBody Order order) throws URISyntaxException {
		
		System.out.println(order.toString());
		SellerInfo seller = sellerService.findOne(order.getSeller().getId());
//		Payer payer = new Payer();
//		payer.setPaymentMethod("bitcoin");

		Map<String, Object> retVal = bTCClient.create(order);
		// Set redirect URLs
//		com.paypal.api.payments.RedirectUrls redirectUrls = new com.paypal.api.payments.RedirectUrls();
//		redirectUrls.setCancelUrl("http://localhost:8002/api/paypal/fail");
//		redirectUrls.setReturnUrl("http://localhost:8002/api/paypal/success");
		if (retVal.get("status").equals("success")) {
			retVal.put("return_url", retVal.get("redirect_url"));
//			Proceed to pay url
//			URI yahoo = new URI(retVal.get("redirect_url").toString());
//			HttpHeaders httpHeaders = new HttpHeaders();
//			httpHeaders.setLocation(yahoo);
//			return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
			return ResponseEntity.ok(retVal);
		} else {
//			Error
		}

		return null;

	}

	@GetMapping("/success")
	public String success(@RequestParam("paymentId") String paymentId, @RequestParam("PayerID") String payerId){
		try {
			paymentService.execute(paymentId, payerId);
			return "redirect:" + "http://localhost:4200/success";
		} catch (Exception e) {
			return "redirect:" + "http://localhost:4200/success";

		}
	}
	@GetMapping("/fail")
	public String fail(){
		return "redirect:" + "http://localhost:4200/fail";
	}
}
