package com.invsol.getfoodyc.view;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.invsol.getfoodyc.GetFoodyCustomerApplication;
import com.invsol.getfoodyc.R;
import com.invsol.getfoodyc.adapters.ChatAdapter;
import com.invsol.getfoodyc.constants.Constants;
import com.invsol.getfoodyc.controllers.AppEventsController;
import com.invsol.getfoodyc.dataobjects.ChatItem;
import com.invsol.getfoodyc.dataobjects.ChatMessage;
import com.invsol.getfoodyc.defines.NetworkEvents;
import com.invsol.getfoodyc.defines.ResponseTags;
import com.invsol.getfoodyc.listeners.ActivityUpdateListener;
import com.invsol.getfoodyc.models.ConnectionModel;

public class ChatActivity extends ActionBarActivity implements
		ActivityUpdateListener {

	private ConnectionModel connModel;
	private EditText edittext_writehere;
	private TextView btn_send;
	private ListView chatList;
	private ChatAdapter chatAdapter;
	private JSONObject chatJson;
	private ArrayList<ChatMessage> chatMessages;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chat);

		connModel = AppEventsController.getInstance().getModelFacade()
				.getConnModel();
		connModel.registerView(this);
		
		chatList = (ListView) findViewById(R.id.chatList);
		chatMessages = new ArrayList<ChatMessage>();
		chatAdapter = new ChatAdapter(this, R.layout.activity_chat,
				chatMessages);
		chatList.setAdapter(chatAdapter);

		if (getIntent().hasExtra("CHAT")) {
			try {
				chatJson = new JSONObject(getIntent().getStringExtra("CHAT"));
				ChatItem chatItem = AppEventsController.getInstance().getModelFacade().getChatModel().getChatItems().get(chatJson.getInt(Constants.JSON_ORDER_ID));
				
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}

		edittext_writehere = (EditText) findViewById(R.id.edittext_writehere);
		btn_send = (TextView) findViewById(R.id.btn_send);
		btn_send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Bundle eventData = new Bundle();
				JSONObject postData = new JSONObject();
				try {
					postData.put(Constants.JSON_CHAT_ORDER_ID,
							AppEventsController.getInstance().getModelFacade()
									.getOrderModel().getOrder_id());
					postData.put(Constants.JSON_CHAT_OWNER_ID,
							AppEventsController.getInstance().getModelFacade()
									.getCustomerModel().getCustomer_id());
					postData.put(Constants.JSON_CHAT_OWNER_TYPE, "CONSUMER");
					postData.put(Constants.JSON_CHAT_MESSAGE,
							edittext_writehere.getText().toString());
				} catch (JSONException e) {
					e.printStackTrace();
				}
				eventData.putString(Constants.JSON_POST_DATA,
						postData.toString());
				AppEventsController.getInstance().handleEvent(
						NetworkEvents.EVENT_ID_CHAT, eventData, btn_send);
				ArrayList<ChatMessage> items = chatAdapter.getChatItems();
				ChatMessage msg;
				msg = new ChatMessage(AppEventsController.getInstance()
						.getModelFacade().getCustomerModel().getCustomer_id(),
						AppEventsController.getInstance().getModelFacade()
								.getCustomerModel().getName(),
						edittext_writehere.getText().toString(), "CONSUMER");
				items.add(msg);
				chatAdapter.notifyDataSetChanged();
				
				edittext_writehere.setText("");
			}
		});
	}

	@Override
	public void updateActivity(int tag, Bundle data) {
		switch (tag) {
		case ResponseTags.TAG_CHAT: {
			try {
				JSONObject chatJson = new JSONObject(
						data.getString(Constants.JSON_CHAT_JSON));
				ArrayList<ChatMessage> items = chatAdapter.getChatItems();
				ChatMessage msg;
				msg = new ChatMessage(chatJson.getInt(Constants.JSON_CHAT_OWNER_ID),
						chatJson.getString(Constants.JSON_CHAT_OWNER_NAME),
						chatJson.getString(Constants.JSON_CHAT_MESSAGE),
						chatJson.getString(Constants.JSON_CHAT_OWNER_TYPE));
				items.add(msg);
				chatAdapter.notifyDataSetChanged();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			break;

		default:
			break;
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		GetFoodyCustomerApplication.setCurrentActivity(this);
		GetFoodyCustomerApplication.activityResumed();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// this.unregisterReceiver(smsReceiver);
		GetFoodyCustomerApplication.clearReferences();
		GetFoodyCustomerApplication.activityPaused();
	}
}
