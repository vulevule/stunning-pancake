package com.kontraktor.paypalservice.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private SellerInfo seller;
	@Column()
	private Long amount;
	@OneToOne(cascade = CascadeType.ALL)
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
