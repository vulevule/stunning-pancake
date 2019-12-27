package com.nc.naucnicentar.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

@Controller
@RequestMapping("/api/payment")
public class PaymentController {
	@PostMapping("/order")
	public ResponseEntity<String> order(@RequestBody Order order) throws UnsupportedOperationException, IOException{
		//SECURITY PRO PA ONDA REQUEST
		String       postUrl       = "http://localhost:8000/api/paypal-service/api/paypal/order";// put in your url
		Gson         gson          = new Gson();
		HttpClient   httpClient    = HttpClientBuilder.create().build();
		HttpPost     post          = new HttpPost(postUrl);
		StringEntity postingString = null;
		try {
			postingString = new StringEntity(gson.toJson(order));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		post.setEntity(postingString);
		post.setHeader("Content-type", "application/json");
		HttpResponse  response = null;
		try {
			 response = httpClient.execute(post);
		} catch (IOException e) {
			e.printStackTrace();
		}		
		String body = EntityUtils.toString(response.getEntity(), "UTF-8");
		return  new ResponseEntity<>(body, HttpStatus.OK);
	}
}
