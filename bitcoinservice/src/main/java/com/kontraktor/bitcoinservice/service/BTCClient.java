package com.kontraktor.bitcoinservice.service;

import com.kontraktor.bitcoinservice.model.Order;
import com.kontraktor.bitcoinservice.model.OrderViaBTC;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class BTCClient {

	private String url = "http://localhost:4200";
	private String sandboxUrl = "https://api-sandbox.coingate.com/v2/orders";
	// Coingate merchant secret
	private String clientSecret = "Token kYzE-5G8EHxnwkaKdazYMsBksptPxKg8E1SSPEgE";
	private String token;

//	@Autowired
//	private OrderRepository orderRepository;

	public Map<String, Object> create(Order order) {
		Map<String, Object> response = new HashMap<String, Object>();
		OrderViaBTC orderViaBTC = new OrderViaBTC();

		orderViaBTC.setOrder_id("Merchant's ID");
		orderViaBTC.setPrice_amount(Double.valueOf(order.getAmount()));
		orderViaBTC.setCancel_url(url + "/fail");
		token = UUID.randomUUID().toString();
		orderViaBTC.setSuccess_url(url + "/success/");
		// orderViaBTC.setCallback_url("http://localhost:8080/api/bitcoin/complete/payment");
		orderViaBTC.setToken(token);
//		order.s(token);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", clientSecret);
		ResponseEntity<OrderViaBTC> responseEntity = new RestTemplate().exchange(sandboxUrl, HttpMethod.POST,
				new HttpEntity<OrderViaBTC>(orderViaBTC, headers), OrderViaBTC.class);

		if (responseEntity.getStatusCode() == HttpStatus.UNPROCESSABLE_ENTITY) {
//			order.setExecuted(false);
//			orderService.save(order);
			response.put("status", "error");
			return response;
		}
//		orderService.save(order);
		response.put("status", "success");
		response.put("redirect_url", responseEntity.getBody().getPayment_url());

		return response;
	}

//	public Map<String, Object> complete(HttpServletRequest request) {
//		Map<String, Object> response = new HashMap<String, Object>();
//		try {
//			Order o=orderService.findOne(Long.parseLong(request.getParameter("paymentId")));
//			orderService.updateExecution(o, true);
//			System.out.println("btc: completed . . . ");
//			response.put("status", "success");
//		} catch (RuntimeException e) {
//			response.put("status", "errror");
//		}
//		// TODO Auto-generated method stub
//		return response;
//	}
//
//	public Map<String, Object> createMembershipPaying(Magazine magazine) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
