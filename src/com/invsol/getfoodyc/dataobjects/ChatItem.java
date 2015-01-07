package com.invsol.getfoodyc.dataobjects;

import java.util.ArrayList;

public class ChatItem {

	private int order_id, owner_id;
	private ArrayList<ChatMessage> messages;
	
	public ChatItem(int orderid, int ownerid){
		this.order_id = orderid;
		this.owner_id = orderid;
		messages = new ArrayList<ChatMessage>();
	}

	public int getOrder_id() {
		return order_id;
	}

	public int getOwner_id() {
		return owner_id;
	}
	
	public void addChatMessage( String message, String owner_type ){
		ChatMessage newMsg = new ChatMessage(message, owner_type);
		messages.add(newMsg);
	}

	public ArrayList<ChatMessage> getMessages() {
		return messages;
	}
	
	
}
