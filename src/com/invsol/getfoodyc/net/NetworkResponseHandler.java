package com.invsol.getfoodyc.net;

import org.json.JSONException;
import org.json.JSONObject;

import com.invsol.getfoodyc.constants.Constants;
import com.invsol.getfoodyc.controllers.AppEventsController;
import com.invsol.getfoodyc.defines.ResponseTags;
import com.invsol.getfoodyc.models.ConnectionModel;

import android.os.Handler;
import android.os.Message;
import android.util.Log;


public class NetworkResponseHandler {
	public static final String TAG = "Network Response Handler";
	
	public static final Handler PLACEORDER_HANDLER = placeOrderHandler();

	private static Handler placeOrderHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				ConnectionModel model = AppEventsController.getInstance()
						.getModelFacade().getConnModel();
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					Log.d("response==", ((JSONObject) msg.obj).toString());
					/*try {
						JSONObject resp = ((JSONObject) msg.obj).getJSONObject(Constants.JSON_RESULT);
						if( (resp.get(Constants.JSON_TYPE)).equals(Constants.JSON_SUCCESS) ){
							JSONObject restData = resp.getJSONObject(Constants.JSON_RESPONSE);
							//AppEventsController.getInstance().getModelFacade().getResModel().setRestaurantProfileDetails(restData);
							//AppEventsController.getInstance().getModelFacade().getResModel().setCuisinesData(restData.getJSONArray(Constants.JSON_CUISINES));
							if( (restData.getJSONArray(Constants.JSON_MENU)).length() > 0 )
								AppEventsController.getInstance().getModelFacade().getResModel().setMenuData(restData.getJSONArray(Constants.JSON_MENU));
							model.setConnectionStatus(ConnectionModel.SUCCESS);
							model.notifyView(ResponseTags.TAG_NEWORDER);
						}
						
					} catch (JSONException e) {
						e.printStackTrace();
					}*/
				}
					break;
				case Constants.EXCEPTION: {
					Exception exceptionObj = (Exception) msg.obj;
					Log.d(TAG, "exception:" + exceptionObj.getMessage());
					model.setConnectionStatus(ConnectionModel.ERROR);
					model.setConnectionErrorMessage(exceptionObj.getMessage());
					model.notifyView(ResponseTags.TAG_ERROR, null);
				}
					break;
				}
			}

		};
	}
}
