package com.armezo.analytics.payload;

public class MessagePayload {
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "MessagePayload [message=" + message + "]";
	}
	
	

}
