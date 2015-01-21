package com.invsol.getfoodyc.view;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.invsol.getfoodyc.GetFoodyCustomerApplication;
import com.invsol.getfoodyc.R;
import com.invsol.getfoodyc.constants.Constants;

public class ConfirmationActivity extends ActionBarActivity{
	
	String confirmationData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_confirmation);
		
		confirmationData = getIntent().getStringExtra("ORDER-STATUS");
		try {
			JSONObject json = new JSONObject(confirmationData);
			TextView textViewStatus = (TextView)findViewById(R.id.label_orderstatus_value);
			textViewStatus.setText(json.getString(Constants.JSON_ORDER_STATUS));
			TextView textViewStatement = (TextView)findViewById(R.id.label_orderstatement_value);
			textViewStatement.setText(Constants.JSON_ORDER_STATEMENT);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		RelativeLayout rl_button_ok = (RelativeLayout)findViewById(R.id.relativelayout_ok);
		rl_button_ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				finish();
			}
		});
		RelativeLayout rl_button_chat = (RelativeLayout)findViewById(R.id.relativelayout_chat);
		rl_button_chat.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(ConfirmationActivity.this,
						ChatActivity.class);
				startActivity(screenChangeIntent);
				finish();
			}
		});
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
	  //this.unregisterReceiver(smsReceiver);
	  GetFoodyCustomerApplication.clearReferences();
	  GetFoodyCustomerApplication.activityPaused();
	}
}
