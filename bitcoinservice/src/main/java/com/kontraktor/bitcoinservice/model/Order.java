package com.kontraktor.bitcoinservice.model;

public class Order {

	private Long id;

	private SellerInfo seller;

	private Long amount;
	
	private RedirectUrls redirectUrls;
	
	

	public RedirectUrls getRedirectUrls() {
		return redirectUrls;
	}

	public void setRedirectUrls(RedirectUrls redirectUrls) {
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

	@Override
	public String toString() {
		return "Order [id=" + id + ", seller=" + seller + ", amount=" + amount + ", redirectUrls=" + redirectUrls + "]";
	}
	
	
}
