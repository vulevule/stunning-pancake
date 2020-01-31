package com.nc.naucnicentar.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Edition {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column
	private String title;
	@Column
	private Long amount;
	@ManyToOne
	private Magazine magazine;
	@Column(length = 100000)
	private String text;
	
	
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Magazine getMagazine() {
		return magazine;
	}
	public void setMagazine(Magazine magazine) {
		this.magazine = magazine;
	}
	public Edition(Long id, String title, Magazine magazine) {
		super();
		this.id = id;
		this.title = title;
		this.magazine = magazine;
	}
	public Edition() {
		super();
	}
	@Override
	public String toString() {
		return "Edition [id=" + id + ", title=" + title + ", magazine=" + magazine + "]";
	}
	
	
	
}
