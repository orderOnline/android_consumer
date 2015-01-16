package com.invsol.getfoodyc.view;

import com.invsol.getfoodyc.GetFoodyCustomerApplication;
import com.invsol.getfoodyc.R;
import com.invsol.getfoodyc.controllers.AppEventsController;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LaunchActivity extends ActionBarActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);

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
						OrderActivity.class);
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
}
