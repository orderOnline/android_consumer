package com.invsol.getfoodyc.view;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.invsol.getfoodyc.R;
import com.invsol.getfoodyc.adapters.ChatAdapter;
import com.invsol.getfoodyc.constants.Constants;
import com.invsol.getfoodyc.controllers.AppEventsController;
import com.invsol.getfoodyc.dataobjects.ChatItem;
import com.invsol.getfoodyc.dataobjects.ChatMessage;
import com.invsol.getfoodyc.defines.NetworkEvents;
import com.invsol.getfoodyc.listeners.ActivityUpdateListener;
import com.invsol.getfoodyc.models.ConnectionModel;

public class ChatActivity extends ActionBarActivity implements ActivityUpdateListener{
	
	private ConnectionModel connModel;
	private EditText edittext_writehere;
	private TextView btn_send;
	private ListView chatList;
	private ChatAdapter chatAdapter;
	private JSONObject chatJson;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);
		
		connModel = AppEventsController.getInstance().getModelFacade()
				.getConnModel();
		connModel.registerView(this);
		
		try {
			chatJson = new JSONObject(getIntent().getStringExtra("CHAT"));
			//ChatItem chatItem = AppEventsController.getInstance().getModelFacade().getChatModel().getChatItems().get(chatJson.getInt(Constants.JSON_ORDER_ID));
			chatList = (ListView)findViewById(R.id.chatList);
			ChatItem chatItem = new ChatItem(chatJson.getInt(Constants.JSON_CHAT_ORDER_ID));
			chatItem.addChatMessage(chatJson.getInt(Constants.JSON_CHAT_OWNER_ID), chatJson.getString(Constants.JSON_CHAT_OWNER_NAME), chatJson.getString(Constants.JSON_CHAT_MESSAGE), "CONSUMER");
			chatItem.addChatMessage(3, "Manpasand", "Order Updated", "RESTAURANT");
			chatAdapter = new ChatAdapter(this, R.layout.activity_chat, chatItem.getMessages());
			chatList.setAdapter(chatAdapter);
		
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		edittext_writehere = (EditText)findViewById(R.id.edittext_writehere);
		btn_send = (TextView)findViewById(R.id.btn_send);
		btn_send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Bundle eventData = new Bundle();
				JSONObject postData = new JSONObject();
				try {
					postData.put(Constants.JSON_CHAT_ORDER_ID, 0);
					postData.put(Constants.JSON_CHAT_OWNER_ID, 3);
					postData.put(Constants.JSON_CHAT_OWNER_TYPE, "CONSUMER");
					postData.put(Constants.JSON_CHAT_MESSAGE, edittext_writehere.getText().toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				eventData.putString(Constants.JSON_POST_DATA, postData.toString());
				AppEventsController.getInstance().handleEvent(NetworkEvents.EVENT_ID_CHAT, eventData, btn_send);
				try {
					ArrayList<ChatMessage> items = chatAdapter.getChatItems();
					ChatMessage msg;
					msg = new ChatMessage(chatJson.getInt(Constants.JSON_CHAT_OWNER_ID), chatJson.getString(Constants.JSON_CHAT_OWNER_NAME), edittext_writehere.getText().toString(), "CONSUMER");
					items.add(msg);
					chatAdapter.notifyDataSetChanged();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void updateActivity(int tag, Bundle data) {
		
	}

	
}
