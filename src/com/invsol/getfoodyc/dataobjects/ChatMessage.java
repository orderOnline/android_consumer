package com.invsol.getfoodyc.dataobjects;

public class ChatMessage {
	
	private String message, owner_type;

	public ChatMessage(String message, String owner_type) {
		this.message = message;
		this.owner_type = owner_type;
	}

	public String getMessage() {
		return message;
	}

	public String getOwner_type() {
		return owner_type;
	}

}
