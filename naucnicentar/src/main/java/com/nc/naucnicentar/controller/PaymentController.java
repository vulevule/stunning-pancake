package com.nc.naucnicentar.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.nc.naucnicentar.model.Order;

@Controller
@RequestMapping("/api/payment")
public class PaymentController {
	
	@PostMapping("/order")
	public ResponseEntity<String> order(@RequestBody Order order) throws UnsupportedOperationException, IOException, KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException{
		
		//restTemp.postForLocation("http://localhost:8000/api/seller-service/api/order", order);
		//SECURITY PRO PA ONDA REQUEST
		
		String password = "password";
		SSLContext sslContext = SSLContextBuilder
                .create()
                .loadKeyMaterial(ResourceUtils.getFile("classpath:Client.jks"), password.toCharArray(), password.toCharArray())
                .loadTrustMaterial(ResourceUtils.getFile("classpath:Client.jks"), password.toCharArray())
                .build();
		String       postUrl       = "http://localhost:8000/api/seller-service/api/order";// put in your url
		Gson         gson          = new Gson();
		HttpClient   httpClient    = HttpClients.custom().setSSLContext(sslContext).build();
		
		
		HttpPost     post          = new HttpPost(postUrl);
		StringEntity postingString = null;
		order.setFailUrl("http://localhost:4200/fail");
		order.setSuccessUrl("http://localhost:4200/success");
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
			 System.out.println(httpClient.toString());
		} catch ( IOException e) {
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		String body = EntityUtils.toString(response.getEntity(), "UTF-8");
		return  new ResponseEntity<>(body, HttpStatus.OK);
	}
}
