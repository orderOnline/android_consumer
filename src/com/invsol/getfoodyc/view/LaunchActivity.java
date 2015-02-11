package com.invsol.getfoodyc.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.invsol.getfoodyc.GetFoodyCustomerApplication;
import com.invsol.getfoodyc.R;
import com.invsol.getfoodyc.controllers.AppEventsController;
import com.invsol.getfoodyc.defines.NetworkEvents;
import com.invsol.getfoodyc.defines.ResponseTags;
import com.invsol.getfoodyc.listeners.ActivityUpdateListener;
import com.invsol.getfoodyc.models.ConnectionModel;

public class LaunchActivity extends ActionBarActivity implements ActivityUpdateListener{
	
	private ConnectionModel connModel;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
		
		connModel = AppEventsController.getInstance().getModelFacade()
				.getConnModel();
		connModel.registerView(this);

		if (AppEventsController.getInstance().getModelFacade()
				.getCustomerModel().isCustomerLoggedIn()) {
			LinearLayout login_layout = (LinearLayout) findViewById(R.id.login_options_layout);
			login_layout.setVisibility(View.GONE);
		}

		TextView btn_search = (TextView) findViewById(R.id.btn_search);
		btn_search.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(LaunchActivity.this,
						HomeActivity.class);
				LaunchActivity.this.startActivity(screenChangeIntent);
			}
		});

		TextView btn_register = (TextView) findViewById(R.id.textview_login_options_register);
		btn_register.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(LaunchActivity.this,
						SignupActivity.class);
				LaunchActivity.this.startActivity(screenChangeIntent);
			}
		});
		
		TextView btn_loginphonenumber = (TextView) findViewById(R.id.textview_login_options_phonenumber);
		btn_loginphonenumber.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(LaunchActivity.this,
						LoginActivity.class);
				LaunchActivity.this.startActivity(screenChangeIntent);
			}
		});
		
		TextView btn_unregister = (TextView) findViewById(R.id.textview_login_options_unregister);
		btn_unregister.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				AppEventsController.getInstance().getModelFacade().getCustomerModel().setCustomer_id(0);
				if( AppEventsController.getInstance().getModelFacade().getCustomerModel().getCustomer_id() != -1 ){
					AppEventsController.getInstance().handleEvent(
						NetworkEvents.EVENT_ID_UNREGISTER, null, view);
				}
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		GetFoodyCustomerApplication.setCurrentActivity(this);
		GetFoodyCustomerApplication.activityResumed();
		if (AppEventsController.getInstance().getModelFacade()
				.getCustomerModel().isCustomerLoggedIn()) {
			LinearLayout login_layout = (LinearLayout) findViewById(R.id.login_options_layout);
			login_layout.setVisibility(View.GONE);
		}
	}
	
	@Override
	protected void onPause() {
	  super.onPause();
	  GetFoodyCustomerApplication.clearReferences();
	  GetFoodyCustomerApplication.activityPaused();
	}

	@Override
	public void updateActivity(int tag, Bundle data) {
		switch (tag) {
		case ResponseTags.TAG_UNREGISTER:
		{
			switch (connModel.getConnectionStatus()) {
			case ConnectionModel.SUCCESS:
			{
				Toast alert = Toast.makeText(this, R.string.text_launch_unregistered_success, Toast.LENGTH_SHORT);
				alert.show();
			}
				break;
			case ConnectionModel.ERROR:{
				Toast alert = Toast.makeText(this, R.string.text_launch_unregistered_failure, Toast.LENGTH_SHORT);
				alert.show();
			}
			break;

			default:
				break;
			}
		}
			break;

		default:
			break;
		}
	}
}
