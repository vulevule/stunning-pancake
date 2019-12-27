package com.kontraktor.sellerservice.model;

public class OrderDto {

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

	public OrderDto(Long id, SellerInfo seller, Long amount) {
		super();
		this.id = id;
		this.seller = seller;
		this.amount = amount;
	}

	public OrderDto() {
		super();
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", seller=" + seller + ", amount=" + amount + ", redirectUrls=" + redirectUrls + "]";
	}

	public OrderDto(CustomOrder order) {
		super();
		this.amount = order.getAmount();
		this.seller = order.getSeller();
		this.redirectUrls = new RedirectUrls(order.getSuccessUrl(), order.getFailUrl());
	}
	
	
}
