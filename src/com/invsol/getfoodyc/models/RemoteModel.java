package com.invsol.getfoodyc.models;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;

import com.invsol.getfoodyc.R;
import com.invsol.getfoodyc.constants.Constants;
import com.invsol.getfoodyc.net.ConnectivityHandler;
import com.invsol.getfoodyc.net.HttpParams;
import com.invsol.getfoodyc.net.NetworkAsyncTask;



public class RemoteModel {
	public static final String TAG = "Remote Model";
	
	public void placeOrder(Bundle params,Handler listener,View view) throws Exception
	{
		ConnectivityHandler connHandler = new ConnectivityHandler(view.getContext());
		if(connHandler.isOnline())
		{
			HttpParams httpParams = new HttpParams();
			httpParams.setRequestURL(Constants.BASE_URL + Constants.URL_POST_NEW_ORDER);
			httpParams.setRequestMethod(HttpParams.HTTP_POST);
			
			String requestData = params.getString(Constants.JSON_POST_DATA);
			httpParams.setRequestData(requestData);
			Log.v(TAG, "Request Data=====>" + requestData);
			
			NetworkAsyncTask asyncTask = new NetworkAsyncTask(view.getContext(), "Connecting", listener, true);
			asyncTask.execute(httpParams);
		}
		else
		{
			listener.sendMessage(listener.obtainMessage(Constants.EXCEPTION, view.getResources().getString(
					R.string.error_no_network_connection)));
		}		
	}
}