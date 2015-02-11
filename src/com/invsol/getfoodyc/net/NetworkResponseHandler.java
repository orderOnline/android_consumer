package com.invsol.getfoodyc.net;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.invsol.getfoodyc.constants.Constants;
import com.invsol.getfoodyc.controllers.AppEventsController;
import com.invsol.getfoodyc.defines.ResponseTags;
import com.invsol.getfoodyc.models.ConnectionModel;
import com.invsol.getfoodyc.models.CustomerModel;


public class NetworkResponseHandler {
	public static final String TAG = "Network Response Handler";
	
	public static final Handler PLACEORDER_HANDLER = placeOrderHandler();
	public static final Handler REGISTERUSER_HANDLER = registerUserHandler();
	public static final Handler REGISTERUSER_VALIDATEOTP_HANDLER = registerUserValidateOTPHandler();
	public static final Handler LOGINUSER_HANDLER = loginUserHandler();
	public static final Handler UNREGISTERCONSUMER_HANDLER = unregisterConsumerHandler();
	public static final Handler SEARCH_RESTAURANTS_HANDLER = searchRestaurantsHandler();

	private static Handler placeOrderHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel model = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					Log.d("response==", ((JSONObject) msg.obj).toString());
					try {
						JSONObject resp = ((JSONObject) msg.obj).getJSONObject(Constants.JSON_RESULT);
						if( (resp.get(Constants.JSON_TYPE)).equals(Constants.JSON_SUCCESS) ){
							JSONObject restData = resp.getJSONObject(Constants.JSON_RESPONSE);
							AppEventsController.getInstance().getModelFacade().getOrderModel().setOrder_id(restData.getInt(Constants.JSON_ORDER_ID));
							Bundle dataBundle = new Bundle();
							dataBundle.putInt(Constants.JSON_ORDER_ID, restData.getInt(Constants.JSON_ORDER_ID));
							model.setConnectionStatus(ConnectionModel.SUCCESS);
							model.notifyView(ResponseTags.TAG_NEWORDER, dataBundle);
						}
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
					break;
				case Constants.EXCEPTION: {
					Exception exceptionObj = (Exception) msg.obj;
					Log.d(TAG, "exception:" + exceptionObj.getMessage());
					Bundle dataBundle = new Bundle();
					dataBundle.putString(Constants.JSON_ERROR_MESSAGE, exceptionObj.getMessage());
					model.setConnectionStatus(ConnectionModel.ERROR);
					model.setConnectionErrorMessage(exceptionObj.getMessage());
					model.notifyView(ResponseTags.TAG_ERROR, dataBundle);
				}
					break;
				}
			}

		};
	}
	
	private static Handler searchRestaurantsHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel model = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					Log.d("response==", ((JSONObject) msg.obj).toString());
					try {
						JSONObject resp = ((JSONObject) msg.obj).getJSONObject(Constants.JSON_RESULT);
						String type = resp.getString(Constants.JSON_TYPE);
						if( type.equals(Constants.JSON_SUCCESS)){
							JSONArray restaurantsArray = resp.getJSONArray(Constants.JSON_RESPONSE);
							if( restaurantsArray.length() > 0 ){
								AppEventsController.getInstance().getModelFacade().getRestaurantsModel().setRestaurantsData(restaurantsArray);
								model.setConnectionStatus(ConnectionModel.SUCCESS);
								model.notifyView(ResponseTags.TAG_SEARCH_RESTAURANTS, null);
							}else{
								Bundle dataBundle = new Bundle();
								dataBundle.putString(Constants.JSON_ERROR_MESSAGE, "No data found.");
								model.setConnectionStatus(ConnectionModel.ERROR);
								model.notifyView(ResponseTags.TAG_SEARCH_RESTAURANTS, dataBundle);
							}
						}else{
							model.setConnectionStatus(ConnectionModel.ERROR);
							model.notifyView(ResponseTags.TAG_SEARCH_RESTAURANTS, null);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
					break;
				case Constants.EXCEPTION: {
					Exception exceptionObj = (Exception) msg.obj;
					Log.d(TAG, "exception:" + exceptionObj.getMessage());
					Bundle dataBundle = new Bundle();
					dataBundle.putString(Constants.JSON_ERROR_MESSAGE, exceptionObj.getMessage());
					model.setConnectionStatus(ConnectionModel.ERROR);
					model.setConnectionErrorMessage(exceptionObj.getMessage());
					model.notifyView(ResponseTags.TAG_ERROR, dataBundle);
				}
					break;
				}
			}

		};
	}

	private static Handler unregisterConsumerHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel model = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					Log.d("response==", ((JSONObject) msg.obj).toString());
					try {
						JSONObject resp = ((JSONObject) msg.obj).getJSONObject(Constants.JSON_RESULT);
						String type = resp.getString(Constants.JSON_TYPE);
						if( type.equals(Constants.JSON_SUCCESS)){
							model.setConnectionStatus(ConnectionModel.SUCCESS);
							model.notifyView(ResponseTags.TAG_UNREGISTER, null);
						}else{
							model.setConnectionStatus(ConnectionModel.ERROR);
							model.notifyView(ResponseTags.TAG_UNREGISTER, null);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
					break;
				case Constants.EXCEPTION: {
					Exception exceptionObj = (Exception) msg.obj;
					Log.d(TAG, "exception:" + exceptionObj.getMessage());
					Bundle dataBundle = new Bundle();
					dataBundle.putString(Constants.JSON_ERROR_MESSAGE, exceptionObj.getMessage());
					model.setConnectionStatus(ConnectionModel.ERROR);
					model.setConnectionErrorMessage(exceptionObj.getMessage());
					model.notifyView(ResponseTags.TAG_ERROR, dataBundle);
				}
					break;
				}
			}

		};
	}

	private static Handler registerUserHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel model = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					Log.d("response==", ((JSONObject) msg.obj).toString());
					CustomerModel customerModel = AppEventsController
							.getInstance().getModelFacade()
							.getCustomerModel();
					try {
						JSONObject resp = ((JSONObject) msg.obj).getJSONObject(Constants.JSON_RESULT);
						JSONObject restData = (JSONObject)resp.getJSONObject(Constants.JSON_RESPONSE);
						String type = resp.getString(Constants.JSON_TYPE);
						if( type.equals(Constants.JSON_SUCCESS)){
							customerModel.setPhonenumber(restData.getLong(Constants.JSON_PHONENUMBER));
							customerModel.setCustomer_id(restData.getInt(Constants.JSON_CONSUMER_ID));
							model.setConnectionStatus(ConnectionModel.SUCCESS);
							model.notifyView(ResponseTags.TAG_REGISTER, null);
						}else if(type.equals(Constants.JSON_ERROR)){
							model.notifyView(ResponseTags.TAG_ERROR, null);
						}
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
					break;
				case Constants.EXCEPTION: {
					Exception exceptionObj = (Exception) msg.obj;
					Log.d(TAG, "exception:" + exceptionObj.getMessage());
					Bundle dataBundle = new Bundle();
					dataBundle.putString(Constants.JSON_ERROR_MESSAGE, exceptionObj.getMessage());
					model.setConnectionStatus(ConnectionModel.ERROR);
					model.setConnectionErrorMessage(exceptionObj.getMessage());
					model.notifyView(ResponseTags.TAG_ERROR, dataBundle);
				}
					break;
				}
			}

		};
	}
	
	private static Handler registerUserValidateOTPHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel model = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					Log.d("response==", ((JSONObject) msg.obj).toString());
					try {
						JSONObject resp = ((JSONObject) msg.obj).getJSONObject(Constants.JSON_RESULT);
						String restData = resp.getString(Constants.JSON_RESPONSE);
						if( restData.equals(Constants.JSON_VALID_OTP_CODE) ){
							AppEventsController.getInstance().getModelFacade().getCustomerModel().setCustomerLoggedIn(true);
							model.setConnectionStatus(ConnectionModel.SUCCESS);
							model.notifyView(ResponseTags.TAG_VALIDATE_OTP, null);
						}
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
					break;
				case Constants.EXCEPTION: {
					Exception exceptionObj = (Exception) msg.obj;
					Log.d(TAG, "exception:" + exceptionObj.getMessage());
					Bundle dataBundle = new Bundle();
					dataBundle.putString(Constants.JSON_ERROR_MESSAGE, exceptionObj.getMessage());
					model.setConnectionStatus(ConnectionModel.ERROR);
					model.setConnectionErrorMessage(exceptionObj.getMessage());
					model.notifyView(ResponseTags.TAG_ERROR, dataBundle);
				}
					break;
				}
			}

		};
	}
	
	private static Handler loginUserHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel model = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					Log.d("response==", ((JSONObject) msg.obj).toString());
					try {
						JSONObject resp = ((JSONObject) msg.obj).getJSONObject(Constants.JSON_RESULT);
						if( (resp.get(Constants.JSON_TYPE)).equals(Constants.JSON_SUCCESS) ){
							JSONObject restData = resp.getJSONObject(Constants.JSON_RESPONSE);
							AppEventsController.getInstance().getModelFacade().getCustomerModel().setCustomerProfileDetails(restData);
							model.setConnectionStatus(ConnectionModel.SUCCESS);
							model.notifyView(ResponseTags.TAG_LOGIN, null);
						}
						
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
					break;
				case Constants.EXCEPTION: {
					Exception exceptionObj = (Exception) msg.obj;
					Log.d(TAG, "exception:" + exceptionObj.getMessage());
					Bundle dataBundle = new Bundle();
					dataBundle.putString(Constants.JSON_ERROR_MESSAGE, exceptionObj.getMessage());
					model.setConnectionStatus(ConnectionModel.ERROR);
					model.setConnectionErrorMessage(exceptionObj.getMessage());
					model.notifyView(ResponseTags.TAG_ERROR, dataBundle);
				}
					break;
				}
			}

		};
	}
}
