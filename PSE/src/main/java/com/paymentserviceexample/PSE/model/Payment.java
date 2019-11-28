package com.paymentserviceexample.PSE.model;


import org.springframework.stereotype.Component;


@Component
public class Payment{

	Long id;
	String email;
	double total;
	String currency;
	String description;
	String method;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}

	public Payment(String email, double total, String currency, String description, String method, String successUrl,
			String cancelUrl) {
		super();
		this.email = email;
		this.total = total;
		this.currency = currency;
		this.description = description;
		this.method = method;

	}
	public Payment() {
		super();
	}
	
	
}
