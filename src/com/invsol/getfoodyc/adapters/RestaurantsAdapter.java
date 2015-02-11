package com.invsol.getfoodyc.adapters;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.invsol.getfoodyc.R;
import com.invsol.getfoodyc.dataobjects.ChatMessage;
import com.invsol.getfoodyc.dataobjects.Restaurant;

public class RestaurantsAdapter extends ArrayAdapter<Restaurant>{

	Context context;
	int layoutResourceId;
	ArrayList<Restaurant> restItems = null;
	ViewHolder holder = null;

	public RestaurantsAdapter(Context context, int layoutResourceId,
			ArrayList<Restaurant> objects) {
		super(context, layoutResourceId, objects);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.restItems = objects;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		Restaurant item = restItems.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater layout_inflator = ((Activity) context)
					.getLayoutInflater();
			convertView = layout_inflator.inflate(R.layout.item_restaurant, null);
			holder.dataCell_RestaurantName = (TextView) convertView.findViewById(R.id.textview_restaurant_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.dataCell_RestaurantName.setText(item.getName().toUpperCase());
		return convertView;
	}

	/**
	 * A class defining the view holder
	 */
	static class ViewHolder {
		private TextView dataCell_RestaurantName;
	}
}