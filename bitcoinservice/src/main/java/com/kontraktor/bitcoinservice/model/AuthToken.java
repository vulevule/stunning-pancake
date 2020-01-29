package com.kontraktor.bitcoinservice.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AuthToken {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String token;
	
	@Column(nullable = false)
	private Date expireDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	
	public AuthToken() {
		super();
	}

	public AuthToken(String token, Date expireDate) {
		super();
		this.token = token;
		this.expireDate = expireDate;
	}

	@Override
	public String toString() {
		return "AuthToken [id=" + id + ", token=" + token + ", expireDate=" + expireDate + "]";
	}
	
	
}
