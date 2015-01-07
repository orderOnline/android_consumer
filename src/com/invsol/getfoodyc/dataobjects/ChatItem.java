package com.invsol.getfoodyc.dataobjects;

import java.util.ArrayList;

public class ChatItem {

	private int order_id;
	private ArrayList<ChatMessage> messages;
	
	public ChatItem(int orderid){
		this.order_id = orderid;
		messages = new ArrayList<ChatMessage>();
	}

	public int getOrder_id() {
		return order_id;
	}
	
	public void addChatMessage( int owner_id, String owner_name, String message, String owner_type ){
		ChatMessage newMsg = new ChatMessage(owner_id, owner_name, message, owner_type);
		messages.add(newMsg);
	}

	public ArrayList<ChatMessage> getMessages() {
		return messages;
	}
	
	
}
