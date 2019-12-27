package com.kontraktor.sellerservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class CustomOrder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne()
	private SellerInfo seller;
    @Column(nullable = false)
	private Long amount;
	@Column(nullable = false)
	private String successUrl;
	@Column(nullable = false)
	private String failUrl;
	@Column(nullable = false)
	private String token;
	
	

	

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



	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public CustomOrder(Long id, Long amount, String successUrl, String failUrl, String token) {
		super();
		this.id = id;
		this.amount = amount;

		this.token = token;
	}

	public CustomOrder() {
		super();
	}

	@Override
	public String toString() {
		return "CustomOrder [id=" + id + ", seller=" + seller + ", amount=" + amount + ", successUrl=" + successUrl
				+ ", failUrl=" + failUrl + ", token=" + token + "]";
	}

	
	
}
