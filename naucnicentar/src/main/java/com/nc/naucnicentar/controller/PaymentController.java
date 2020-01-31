package com.nc.naucnicentar.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLContext;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
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
import com.nc.naucnicentar.model.Edition;
import com.nc.naucnicentar.model.Order;
import com.nc.naucnicentar.model.Order.OrderStatus;
import com.nc.naucnicentar.model.SellerInfo;
import com.nc.naucnicentar.repository.EditionRepository;
import com.nc.naucnicentar.repository.OrderRepository;

@Controller
@RequestMapping("/api/payment")
public class PaymentController {
	@Autowired EditionRepository editionRepo;
	@Autowired OrderRepository orderRepo;
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
	@GetMapping(value="/editions")
	public ResponseEntity<List<Edition>> allEditions(){
		return new ResponseEntity<List<Edition>>(editionRepo.findAll(), HttpStatus.OK);
	}
	@GetMapping(value="/success/{orderId}")
	public ResponseEntity<Edition> successPayment(@PathVariable Long orderId){
		System.out.println(orderId);
		Order order = orderRepo.findById(orderId).get();
		System.out.println(order.toString());
		System.out.println(order.getEdition());
		return new ResponseEntity<Edition>(order.getEdition(), HttpStatus.OK);
	}
	@PostMapping(value="/pay/edition/{id}")
	public ResponseEntity<String> payForMagazineEdition(@PathVariable Long id) throws KeyManagementException, UnrecoverableKeyException, NoSuchAlgorithmException, KeyStoreException, CertificateException, FileNotFoundException, IOException{		
		String password = "password";
		SSLContext sslContext = SSLContextBuilder
                .create()
                .loadKeyMaterial(ResourceUtils.getFile("classpath:Client.jks"), password.toCharArray(), password.toCharArray())
                .loadTrustMaterial(ResourceUtils.getFile("classpath:Client.jks"), password.toCharArray())
                .build();
		String       postUrl       = "http://localhost:8000/api/seller-service/api/order";// put in your url
		Gson         gson          = new Gson();
		HttpClient   httpClient    = HttpClients.custom().setConnectionTimeToLive(5, TimeUnit.MINUTES).setSSLContext(sslContext).build();
		
		
		HttpPost     post          = new HttpPost(postUrl);
		StringEntity postingString = null;
		
		Edition editionToPay = editionRepo.findById(id).get();
		SellerInfo seller = editionToPay.getMagazine().getSeller();
		Order order = new Order(seller, editionToPay.getAmount(), OrderStatus.REQUESTED);
		order.setEdition(editionToPay);
		Order savedOrder = orderRepo.save(order);
		order.setFailUrl("http://localhost:4200/fail/" + savedOrder.getId());
		order.setSuccessUrl("http://localhost:4200/success/" + savedOrder.getId());
		orderRepo.save(order);
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
