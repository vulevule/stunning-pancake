package com.nc.naucnicentar.model;

import java.io.Serializable;



public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	private SellerInfo seller;

	private Long amount;
		
	private String failUrl;
	
	private String successUrl;
	
	

	

	public String getSuccessUrl() {
		return successUrl;
	}

	public void setSuccessUrl(String successUrl) {
		this.successUrl = successUrl;
	}

	public String getFailUrl() {
		return failUrl;
	}

	public void setFailUrl(String failUrl) {
		this.failUrl = failUrl;
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

	@Override
	public String toString() {
		return "Order [id=" + id + ", seller=" + seller + ", amount=" + amount + ", redirectUrls=" +"]";
	}
	
	
}
