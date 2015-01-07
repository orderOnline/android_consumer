package com.invsol.getfoodyc.adapters;

import java.util.ArrayList;

import com.invsol.getfoodyc.R;
import com.invsol.getfoodyc.dataobjects.ChatMessage;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ChatAdapter extends ArrayAdapter<ChatMessage>{

	Context context;
	int layoutResourceId;
	ArrayList<ChatMessage> chatItems = null;
	ViewHolder holder = null;

	public ChatAdapter(Context context, int layoutResourceId,
			ArrayList<ChatMessage> objects) {
		super(context, layoutResourceId, objects);
		this.context = context;
		this.layoutResourceId = layoutResourceId;
		this.chatItems = objects;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {
		ChatMessage item = chatItems.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater layout_inflator = ((Activity) context)
					.getLayoutInflater();
			convertView = layout_inflator.inflate(R.layout.chatlist_rowlayout, null);
			holder.dataCell_chatmsg = (TextView) convertView.findViewById(R.id.textview_chat);
			if( item.getOwner_type().equals("CONSUMER") ){
				
			}else if(item.getOwner_type().equals("RESTAURANT") ){
				
			}
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.dataCell_chatmsg.setText(item.getMessage());
		return convertView;
	}

	/**
	 * A class defining the view holder
	 */
	static class ViewHolder {
		private TextView dataCell_chatmsg;
	}
}
