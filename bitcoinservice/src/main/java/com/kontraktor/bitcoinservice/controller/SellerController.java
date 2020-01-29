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
		if (retVal.get("status").equals("success")) {
			retVal.put("return_url", retVal.get("redirect_url"));
			return ResponseEntity.ok(retVal);
		} else {
//			Error
			retVal.put("return_url", "http://localhost:8002/api/bitcoin/fail");
			return ResponseEntity.unprocessableEntity().body(retVal);
		}
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
