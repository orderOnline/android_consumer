package com.invsol.getfoodyc.view;

import com.invsol.getfoodyc.GetFoodyCustomerApplication;
import com.invsol.getfoodyc.R;
import com.invsol.getfoodyc.adapters.RestaurantsAdapter;
import com.invsol.getfoodyc.controllers.AppEventsController;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.ListView;

public class HomeActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
		ListView restaurantsList = (ListView)findViewById(R.id.listView_restaurants);
		RestaurantsAdapter adapter = new RestaurantsAdapter(this, R.layout.activity_home, AppEventsController.getInstance().getModelFacade().getRestaurantsModel().getRestaurantsArray());
		restaurantsList.setAdapter(adapter);
		
		ImageView imageview_cart = (ImageView)findViewById(R.id.imageView_cart);
		imageview_cart.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(HomeActivity.this,
						OrderActivity.class);
				HomeActivity.this.startActivity(screenChangeIntent);
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
