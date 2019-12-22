package com.kontraktor.paypalservice.model;

public class OrderResponse {
String message;

public String getMessage() {
	return message;
}

public void setMessage(String message) {
	this.message = message;
}

public OrderResponse(String message) {
	super();
	this.message = message;
}

public OrderResponse() {
	super();
}

}
