package com.kontraktor.bitcoinservice.model;

import javax.persistence.*;
import java.util.Base64;

@Entity
public class SellerInfo {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String clientId;
	
	@Column(nullable = false)
	private String clientSecret;
	   
	@OneToOne(cascade = CascadeType.ALL)
	private AuthToken token;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	
	public AuthToken getToken() {
		return token;
	}
	public void setToken(AuthToken token) {
		this.token = token;
	}
	
	public SellerInfo() {
		super();
	}
	public SellerInfo(String clientId, String clientSecret, AuthToken token) {
		super();
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.token = token;
	}
	public String getClientCredentialsBase64Encoded(){
		return Base64.getEncoder().encodeToString((new String(this.getClientId()+ ":" + this.getClientSecret())).getBytes());
	}
	

}
