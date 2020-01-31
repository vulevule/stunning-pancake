package com.nc.naucnicentar.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Orderr")
public class Order implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	private SellerInfo seller;
	@Column
	private Long amount;
	@Column
	private String failUrl;
	@Column
	private String successUrl;
	@Column
	private OrderStatus status;
	@ManyToOne 
	Edition edition;
	
	
	
	
	public Edition getEdition() {
		return edition;
	}

	public void setEdition(Edition edition) {
		this.edition = edition;
	}

	public enum OrderStatus{
		REQUESTED, FAILED, COMPLETED
	}
	

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

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

	public Order(SellerInfo seller, Long amount, OrderStatus status) {
		super();
		this.seller = seller;
		this.amount = amount;
		this.status = status;
	}

	public Order() {
		super();
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", seller=" + seller + ", amount=" + amount + ", redirectUrls=" +"]";
	}
	
	
}
