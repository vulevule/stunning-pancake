package com.kontraktor.paypalservice.model;

public class Order {

	private Long id;

	private SellerInfo seller;

	private Long amount;
	
	private PayPalRedirectUrls redirectUrls;
	
	

	public PayPalRedirectUrls getRedirectUrls() {
		return redirectUrls;
	}

	public void setRedirectUrls(PayPalRedirectUrls redirectUrls) {
		this.redirectUrls = redirectUrls;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SellerInfo getSeller() {
		return seller;
	}

	public void setSeller(SellerInfo seller) {
		this.seller = seller;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Order(Long id, SellerInfo seller, Long amount) {
		super();
		this.id = id;
		this.seller = seller;
		this.amount = amount;
	}

	public Order() {
		super();
	}
	
	
}
