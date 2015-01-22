package com.invsol.getfoodyc.view;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.invsol.getfoodyc.GetFoodyCustomerApplication;
import com.invsol.getfoodyc.R;
import com.invsol.getfoodyc.constants.Constants;
import com.invsol.getfoodyc.controllers.AppEventsController;
import com.invsol.getfoodyc.defines.NetworkEvents;
import com.invsol.getfoodyc.defines.ResponseTags;
import com.invsol.getfoodyc.listeners.ActivityUpdateListener;
import com.invsol.getfoodyc.models.ConnectionModel;

public class OrderActivity extends ActionBarActivity implements ActivityUpdateListener{
	
	private TextView item_billamount;
	private EditText editText_instructions, edtTxtAddress;
	private CheckBox chkBoxAddress;
	private ConnectionModel connModel;
	private boolean isAddressChecked;
	private LinearLayout layout_address;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		
		connModel = AppEventsController.getInstance().getModelFacade()
				.getConnModel();
		connModel.registerView(this);

		LinearLayout newOrder_layout = (LinearLayout) findViewById(R.id.layout_orderdetails);

		View view = getLayoutInflater().inflate(R.layout.item_order_menuitems,
				newOrder_layout, false);
		EditText editText_qty = (EditText) view.findViewById(R.id.edittext_item_qty);
		editText_qty.setText("1");
		TextView item_name = (TextView) view
				.findViewById(R.id.textview_item_name);
		item_name.setText("Paneer Butter Masala");
		TextView item_price = (TextView) view
				.findViewById(R.id.textview_item_price);
		item_price.setText("150");
		newOrder_layout.addView(view);
		
		View secondview = getLayoutInflater().inflate(R.layout.item_order_menuitems,
				newOrder_layout, false);
		EditText editText_qty1 = (EditText) secondview.findViewById(R.id.edittext_item_qty);
		editText_qty1.setText("5");
		TextView item_name1 = (TextView) secondview
				.findViewById(R.id.textview_item_name);
		item_name1.setText("Breads");
		TextView item_price1 = (TextView) secondview
				.findViewById(R.id.textview_item_price);
		item_price1.setText("100");
		newOrder_layout.addView(secondview);
		
		item_billamount = (TextView)findViewById(R.id.textview_bill_amount_value);
		item_billamount.setText("650");
		
		layout_address = (LinearLayout)findViewById(R.id.layout_address);
		
		chkBoxAddress = (CheckBox)findViewById(R.id.checkbox_address);
		edtTxtAddress = (EditText)findViewById(R.id.edittext_address);
		isAddressChecked = chkBoxAddress.isChecked();
		chkBoxAddress.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				isAddressChecked = isChecked;
				if(!isChecked){
					layout_address.setVisibility(View.VISIBLE);
				}else{
					layout_address.setVisibility(View.GONE);
				}
			}
		});
		
		editText_instructions = (EditText)findViewById(R.id.edittext_instructions);
		
		TextView btn_placeOrder = (TextView)findViewById(R.id.btn_place_order);
		btn_placeOrder.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View view) {
				Bundle eventData = new Bundle();
				JSONObject postData = new JSONObject();
				try {
					postData.put(Constants.JSON_ORDER_TOTAL, Integer.parseInt(item_billamount.getText().toString()));
					postData.put(Constants.JSON_RESTAURANT_ID, 3);
					postData.put(Constants.JSON_CONSUMER_ID, 2);
					if( (null != editText_instructions.getText().toString()) || !(editText_instructions.getText().toString().isEmpty()) )
						postData.put(Constants.JSON_INSTRUCTIONS, editText_instructions.getText());
					if( isAddressChecked )
						postData.put(Constants.JSON_ADDRESS, "Flat-501, Sector-22, Dwarka");
					else
						postData.put(Constants.JSON_ADDRESS, edtTxtAddress.getText());
					Long tsLong = System.currentTimeMillis()/1000;
					postData.put(Constants.JSON_TIMESTAMP, tsLong);
					JSONArray orderItemsArray = new JSONArray();
					
					JSONObject tempItemJson = new JSONObject();
					tempItemJson.put(Constants.JSON_ITEM_ID, 18);
					tempItemJson.put(Constants.JSON_QUANTITY, 1);
					orderItemsArray.put(tempItemJson);
					
					tempItemJson = null;
					tempItemJson = new JSONObject();
					tempItemJson.put(Constants.JSON_ITEM_ID, 20);
					tempItemJson.put(Constants.JSON_QUANTITY, 5);
					orderItemsArray.put(tempItemJson);
					
					postData.put(Constants.JSON_ORDER_ITEMS, orderItemsArray);
				} catch (JSONException e) {
					e.printStackTrace();
				}
				eventData.putString(Constants.JSON_POST_DATA, postData.toString());
				AppEventsController.getInstance().handleEvent(NetworkEvents.EVENT_ID_PLACEORDER, eventData, edtTxtAddress);
			}
		});
	}
	
	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		View view = getCurrentFocus();
		boolean ret = super.dispatchTouchEvent(event);

		if (view instanceof EditText) {
			View w = getCurrentFocus();
			int scrcoords[] = new int[2];
			w.getLocationOnScreen(scrcoords);
			float x = event.getRawX() + w.getLeft() - scrcoords[0];
			float y = event.getRawY() + w.getTop() - scrcoords[1];

			if (event.getAction() == MotionEvent.ACTION_UP
					&& (x < w.getLeft() || x >= w.getRight() || y < w.getTop() || y > w
							.getBottom())) {
				InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.hideSoftInputFromWindow(getWindow().getCurrentFocus()
						.getWindowToken(), 0);
			}
		}
		return ret;
	}

	@Override
	public void updateActivity(int tag, Bundle data) {
		switch( tag ){
		case ResponseTags.TAG_NEWORDER:{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(getResources().getString(R.string.info));
			builder.setMessage(getResources().getString(R.string.text_order_confirmation));
			builder.setPositiveButton(getResources().getString(R.string.action_button_chat),
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
							Intent screenChangeIntent = null;
							screenChangeIntent = new Intent(OrderActivity.this,
									ChatActivity.class);
							startActivity(screenChangeIntent);
							connModel.unregisterAllView();
							finish();
						}
					});
			builder.setNegativeButton(getResources().getString(R.string.OK),
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							connModel.unregisterAllView();
							dialog.cancel();
							finish();
						}
					});
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
		}
		break;
		case ResponseTags.TAG_ERROR:{
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle(getResources().getString(R.string.info));
			builder.setMessage(data.getString(Constants.JSON_ERROR_MESSAGE));
			builder.setPositiveButton(getResources().getString(R.string.OK),
					new DialogInterface.OnClickListener() {

						@Override
						public void onClick(DialogInterface dialog, int which) {
							dialog.cancel();
						}
					});
			AlertDialog alertDialog = builder.create();
			alertDialog.show();
		}
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
	  //this.unregisterReceiver(smsReceiver);
	  GetFoodyCustomerApplication.clearReferences();
	  GetFoodyCustomerApplication.activityPaused();
	}
}
