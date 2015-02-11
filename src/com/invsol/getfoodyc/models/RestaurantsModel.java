package com.invsol.getfoodyc.models;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.invsol.getfoodyc.constants.Constants;
import com.invsol.getfoodyc.dataobjects.Restaurant;

import android.util.SparseArray;

public class RestaurantsModel {

	private ArrayList<Restaurant> restaurantsArray;

	public RestaurantsModel() {
		restaurantsArray = new ArrayList<Restaurant>();
	}

	public void setRestaurantsData(JSONArray resArray) {
		JSONObject tempRestaurant = null;
		Restaurant tempObj = null;
		try {
			for (int i = 0; i < resArray.length(); i++) {
				tempRestaurant = resArray.getJSONObject(i);
				tempObj = new Restaurant(tempRestaurant.getInt(Constants.JSON_RESTAURANT_ID));
				tempObj.setName(tempRestaurant.getString(Constants.JSON_NAME));
				tempObj.setAddress(tempRestaurant.getString(Constants.JSON_ADDRESS));
				tempObj.setCity(tempRestaurant.getString(Constants.JSON_CITY));
				tempObj.setEmail(tempRestaurant.getString(Constants.JSON_EMAIL));
				tempObj.setPhonenumber(tempRestaurant.getLong(Constants.JSON_PHONENUMBER));
				tempObj.setService_starttime(tempRestaurant.getString(Constants.JSON_SERVICE_START_TIME));
				tempObj.setService_endtime(tempRestaurant.getString(Constants.JSON_SERVICE_END_TIME));
				tempObj.setState(tempRestaurant.getString(Constants.JSON_STATE));
				tempObj.setZipcode(tempRestaurant.getInt(Constants.JSON_ZIPCODE));
				restaurantsArray.add(tempObj);
			}
		} catch (JSONException e) {
		}
	}

	public ArrayList<Restaurant> getRestaurantsArray() {
		return restaurantsArray;
	}
	
	
}
