package com.invsol.getfoodyc.dataobjects;


public class ChatMessage {
	
	private int owner_id;
	private String owner_name, message, owner_type;

	public ChatMessage(int owner_id, String owner_name, String message, String owner_type) {
		this.owner_id = owner_id;
		this.owner_name = owner_name;
		this.message = message;
		this.owner_type = owner_type;
	}

	public String getMessage() {
		return message;
	}

	public String getOwner_type() {
		return owner_type;
	}

	public int getOwner_id() {
		return owner_id;
	}

	public String getOwner_name() {
		return owner_name;
	}

	
}
