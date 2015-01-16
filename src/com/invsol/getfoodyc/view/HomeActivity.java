package com.invsol.getfoodyc.view;

import com.invsol.getfoodyc.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class HomeActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		
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
}
