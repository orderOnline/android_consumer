package com.invsol.getfoodyc.net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.invsol.getfoodyc.constants.Constants;
import com.invsol.getfoodyc.exceptions.ServerException;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;

public class NetworkAsyncTask extends AsyncTask<HttpParams, String, byte[]> {

	public static final String TAG = "NetworkSyncTask";
	// Progress Dialog
	private ProgressDialog pDialog;
	private Context reqContext;
	private String progressMsg;
	private Handler responseListener;
	private ConnectivityHandler connHandler;
	private byte[] responseData = null;
	private boolean exceptionRaised;
	private boolean showProgress = true;

	public NetworkAsyncTask(Context context, String progressMsg,
			Handler listener, boolean showProgress) {
		super();
		this.reqContext = context;
		this.progressMsg = progressMsg;
		this.responseListener = listener;
		connHandler = new ConnectivityHandler(context);
		exceptionRaised = false;
		this.showProgress = showProgress;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		if( showProgress ){
			pDialog = new ProgressDialog(reqContext);
			pDialog.setMessage(progressMsg);
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(false);
			pDialog.show();
		}
	}

	@Override
	protected byte[] doInBackground(HttpParams... params) {
		try {
			HttpParams param = params[0];
			Bundle bundle = param.getRequestParamsBundle();
			String requestMethod = bundle.getString(HttpParams.REQUEST_METHOD);
			if (requestMethod.equals(HttpParams.HTTP_GET))
				responseData = connHandler.makeHttpRequest(param);
			else {
				String requestData = bundle.getString(HttpParams.REQUEST_DATA);
				responseData = connHandler.makeHttpPostRequest(param,
						requestData);
			}
		} catch (ServerException exception) {
			exceptionRaised = true;
			responseListener.sendMessage(responseListener.obtainMessage(
					Constants.EXCEPTION, exception));
		}
		return responseData;
	}

	@Override
	protected void onPostExecute(byte[] result) {
		super.onPostExecute(result);
		if (!exceptionRaised) {
			validateJsonData(responseData);
		}
		if( showProgress )
			pDialog.dismiss();
	}

	/**
	 * Validate Json data
	 */
	public void validateJsonData(byte data[]) {
		Object parsedResult = null;
		// First create string for the given result
		String buffer = new String(data);
		// Now we need to create the json object
		JSONObject jsonObject = null;
		try {
			// Creating Json Object Which Will Hold The Result in Proper Format
			// i.e. Json
			jsonObject = new JSONObject(buffer);
			parsedResult = jsonObject;
		} catch (JSONException exception) {
			try {
				JSONArray jsonArray = new JSONArray(buffer);
				parsedResult = jsonArray;
			} catch (JSONException e) {
				parsedResult = e.getMessage();
			}
		}

		if (parsedResult instanceof JSONObject
				|| parsedResult instanceof JSONArray) {
			// successful response
			responseListener.sendMessage(responseListener.obtainMessage(
					Constants.SUCCESSFUL_RESPONSE, parsedResult));
		} else if (parsedResult instanceof String) {
			// handle the error
			responseListener.sendMessage(responseListener.obtainMessage(
					Constants.ERROR, parsedResult));
		} else if (parsedResult instanceof Exception) {
			responseListener.sendMessage(responseListener.obtainMessage(
					Constants.EXCEPTION, parsedResult));
		}
	}

}