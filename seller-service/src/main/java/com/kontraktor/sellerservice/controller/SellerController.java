package com.kontraktor.sellerservice.controller;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.UUID;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.kontraktor.sellerservice.model.CustomOrder;
import com.kontraktor.sellerservice.model.OrderDto;
import com.kontraktor.sellerservice.model.RedirectUrls;
import com.kontraktor.sellerservice.model.PaymentMethod;
import com.kontraktor.sellerservice.repository.OrderRepository;
import com.kontraktor.sellerservice.repository.PaymentMethodRepository;
import com.kontraktor.sellerservice.repository.SellerRepository;
@Controller
@RequestMapping("/api")
public class SellerController {
	@Autowired
	SellerRepository sellerRepo;
	@Autowired
	PaymentMethodRepository paymentMethodRepo;
	@Autowired
	OrderRepository orderRepo;
	@PostMapping("/order")
	public ResponseEntity<RedirectUrls> order(@RequestBody CustomOrder order){
		System.out.println(order.toString());
	    String generatedString = UUID.randomUUID().toString().replace("-", "");
	    order.setToken(generatedString);
	    orderRepo.save(order);
		RedirectUrls urls = new RedirectUrls("http://localhost:4201/paymentMethods?token=" + order.getToken() , "");
		return new ResponseEntity<RedirectUrls>(urls, HttpStatus.OK);
		
	}
	@GetMapping("/paymentMethods/{token}")
	public ResponseEntity<List<PaymentMethod>> getPaymentMethods(@PathVariable("token") String token){
		System.out.println(token);
		
		CustomOrder order = orderRepo.findByToken(token);
		System.out.println(order);
		System.out.println(order.getSeller());
		return new ResponseEntity<>(order.getSeller().getPaymentMethods(), HttpStatus.OK);
	}
	@GetMapping("/pay/{token}/{method}")
	public ResponseEntity<String> makePayment(@PathVariable("token") String token, @PathVariable("method") String method) throws ParseException, IOException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException{
		PaymentMethod paymentMethod = paymentMethodRepo.findOne(method);
		System.out.println(method.toString());
		CustomOrder order = orderRepo.findByToken(token);
		System.out.println(order);
		OrderDto orderDto = new OrderDto(order);
		String password = "password";
		String       postUrl       = "http://localhost:8000/api/" + paymentMethod.getServiceName() + "/api/" + paymentMethod.getName() + "/order";// put in your url

		Gson         gson          = new Gson();
//		SSLContext sslContext = SSLContextBuilder
//                .create()
//                .loadKeyMaterial(ResourceUtils.getFile("classpath:SellerService.jks"), password.toCharArray(), password.toCharArray())
//                .loadTrustMaterial(ResourceUtils.getFile("classpath:SellerService.jks"), password.toCharArray())
//                .build();
//		HttpClient   httpClient    = HttpClientBuilder.create().setSSLContext(sslContext).build();
		HttpClient   httpClient    = HttpClientBuilder.create().build();
		HttpPost     post          = new HttpPost(postUrl);
		StringEntity postingString = null;
		try {
			postingString = new StringEntity(gson.toJson(orderDto));
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
