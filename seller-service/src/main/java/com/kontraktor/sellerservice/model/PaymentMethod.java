package com.kontraktor.sellerservice.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PaymentMethod {
	@Id
	private String name;
	
	@Column(nullable = false)
	private String serviceName;

	@Column(nullable = false)
	private String imageSrc;
	
	
	
	public String getImageSrc() {
		return imageSrc;
	}

	public void setImageSrc(String imageSrc) {
		this.imageSrc = imageSrc;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public PaymentMethod(String name, String serviceName) {
		super();
		this.name = name;
		this.serviceName = serviceName;
	}

	public PaymentMethod() {
		super();
	}
	
	
	
	
}
