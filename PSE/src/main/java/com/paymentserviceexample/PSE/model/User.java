package com.paymentserviceexample.PSE.model;

import org.springframework.stereotype.Component;

@Component
public class User {
	private String email;
	private double balance;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public User(String email, double balance) {
		super();
		this.email = email;
		this.balance = balance;
	}
	public User() {
		super();
	}
	
}
