package com.nc.naucnicentar.controller;

import java.util.Base64;




public class SellerInfo {

	private Long id;
	

	private String clientId;
	

	private String clientSecret;
	   


	
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
	

	
	public SellerInfo() {
		super();
	}
	public SellerInfo(String clientId, String clientSecret) {
		super();
		this.clientId = clientId;
		this.clientSecret = clientSecret;

	}
	public String getClientCredentialsBase64Encoded(){
		return Base64.getEncoder().encodeToString((new String(this.getClientId()+ ":" + this.getClientSecret())).getBytes());
	}
	

}
