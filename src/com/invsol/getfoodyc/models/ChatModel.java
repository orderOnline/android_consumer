package com.invsol.getfoodyc.models;

import org.json.JSONException;
import org.json.JSONObject;

import android.util.SparseArray;

import com.invsol.getfoodyc.constants.Constants;
import com.invsol.getfoodyc.dataobjects.ChatItem;


public class ChatModel {

	private JSONObject newChatMessage;
	private SparseArray<ChatItem> chatItems;
	
	public ChatModel(){
		chatItems = new SparseArray<ChatItem>();
	}
	
	public void addOrderChat( JSONObject chatJson ){
		try {
			newChatMessage = chatJson;
			int order_id = chatJson.getInt(Constants.JSON_CHAT_ORDER_ID);
			int owner_id = chatJson.getInt(Constants.JSON_CHAT_OWNER_ID);

			ChatItem item = null;
			if( chatItems.get(order_id) != null){
				item = chatItems.get(order_id);
			}else{
				item = new ChatItem(order_id);
				chatItems.put(order_id, item);
			}
			item.addChatMessage(owner_id, chatJson.getString(Constants.JSON_CHAT_OWNER_NAME), chatJson.getString(Constants.JSON_CHAT_MESSAGE), chatJson.getString(Constants.JSON_CHAT_OWNER_TYPE));
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public SparseArray<ChatItem> getChatItems() {
		return chatItems;
	}

	public JSONObject getNewChatMessage() {
		return newChatMessage;
	}
	
}
