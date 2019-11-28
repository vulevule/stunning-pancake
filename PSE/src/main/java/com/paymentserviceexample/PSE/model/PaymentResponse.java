package com.paymentserviceexample.PSE.model;

public class PaymentResponse {
	private Payment payment;
	private Status status;
	
	
	
	public PaymentResponse(Payment payment, Status status) {
		super();
		this.payment = payment;
		this.status = status;
	}



	public PaymentResponse() {
		super();
	}



	public Payment getPayment() {
		return payment;
	}



	public void setPayment(Payment payment) {
		this.payment = payment;
	}



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}



	public enum Status{
		SUCCESS, NOT_ENOUGH_FUNDS, AUTHENTICATION_FAIL, ERROR;
	}
	
}

