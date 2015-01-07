package com.invsol.getfoodyc.view;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ListView;

import com.invsol.getfoodyc.R;
import com.invsol.getfoodyc.adapters.ChatAdapter;
import com.invsol.getfoodyc.constants.Constants;
import com.invsol.getfoodyc.controllers.AppEventsController;
import com.invsol.getfoodyc.dataobjects.ChatItem;
import com.invsol.getfoodyc.listeners.ActivityUpdateListener;
import com.invsol.getfoodyc.models.ConnectionModel;

public class ChatActivity extends ActionBarActivity implements ActivityUpdateListener{
	
	private ConnectionModel connModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		
		connModel = AppEventsController.getInstance().getModelFacade()
				.getConnModel();
		connModel.registerView(this);
		
		try {
			JSONObject chatJson = new JSONObject(getIntent().getStringExtra("CHAT"));
			//ChatItem chatItem = AppEventsController.getInstance().getModelFacade().getChatModel().getChatItems().get(chatJson.getInt(Constants.JSON_ORDER_ID));
			ListView chatList = (ListView)findViewById(R.id.chatList);
			ChatItem chatItem = new ChatItem(chatJson.getInt(Constants.JSON_CHAT_ORDER_ID), chatJson.getInt(Constants.JSON_CHAT_OWNER_ID));
			chatItem.addChatMessage(chatJson.getString(Constants.JSON_CHAT_MESSAGE), "CONSUMER");
			chatItem.addChatMessage("Order Updated", "RESTAURANT");
			ChatAdapter chatAdapter = new ChatAdapter(this, R.layout.activity_chat, chatItem.getMessages());
			chatList.setAdapter(chatAdapter);
		
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateActivity(int tag, Bundle data) {
		// TODO Auto-generated method stub
		
	}

	
}
