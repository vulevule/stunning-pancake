package com.nc.naucnicentar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Magazine {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String name;
	@ManyToOne
	SellerInfo seller;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SellerInfo getSeller() {
		return seller;
	}
	public void setSeller(SellerInfo seller) {
		this.seller = seller;
	}
	public Magazine(Long id, String name, SellerInfo seller) {
		super();
		this.id = id;
		this.name = name;
		this.seller = seller;
	}
	public Magazine() {
		super();
	}
	@Override
	public String toString() {
		return "Magazine [id=" + id + ", name=" + name + ", seller=" + seller + "]";
	}
	
	
}
