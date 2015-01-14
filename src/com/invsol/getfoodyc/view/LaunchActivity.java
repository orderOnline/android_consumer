package com.invsol.getfoodyc.view;

import com.invsol.getfoodyc.R;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class LaunchActivity extends ActionBarActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_launch);
		
		TextView btn_search = (TextView)findViewById(R.id.btn_search);
		btn_search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(LaunchActivity.this,
						OrderActivity.class);
				LaunchActivity.this.startActivity(screenChangeIntent);
			}
		});
	}
}
