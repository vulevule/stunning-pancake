package com.kontraktor.paypalservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
@Entity
public class PaymentRef {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String paymentId;
	@ManyToOne
	private SellerInfo seller;
	
	public String getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}
	public SellerInfo getSeller() {
		return seller;
	}
	public void setSeller(SellerInfo seller) {
		this.seller = seller;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	public PaymentRef( String paymentId, SellerInfo seller) {
		super();

		this.paymentId = paymentId;
		this.seller = seller;
	}
	public PaymentRef() {
		super();
	}
	
	
}
