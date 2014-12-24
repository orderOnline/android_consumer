package com.invsol.getfoodyc.view;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.invsol.getfoodyc.R;

public class OrderActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);

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
		
		TextView item_billamount = (TextView)findViewById(R.id.textview_bill_amount_value);
		item_billamount.setText("INR 650");
	}
}
