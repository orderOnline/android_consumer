package com.invsol.getfoodyc.models;

import org.json.JSONException;
import org.json.JSONObject;

import com.invsol.getfoodyc.constants.Constants;

import android.util.Log;

public class CustomerModel {

	private boolean isCustomerLoggedIn = false;
	private String gcm_registration_key;
	private int customer_id;
	private long phonenumber;
	private String name, email, address, city, state;
	private int zipcode;
	
	public String getGcm_registration_key() {
		return gcm_registration_key;
	}

	public void setGcm_registration_key(String gcm_registration_key) {
		Log.d("Restaurant Model", "key revd=="+gcm_registration_key);
		this.gcm_registration_key = gcm_registration_key;
	}

	public boolean isCustomerLoggedIn() {
		return isCustomerLoggedIn;
	}

	public void setCustomerLoggedIn(boolean isCustomerLoggedIn) {
		this.isCustomerLoggedIn = isCustomerLoggedIn;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public long getPhonenumber() {
		return phonenumber;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public void setPhonenumber(long phonenumber) {
		this.phonenumber = phonenumber;
	}
	
	public void setCustomerProfileDetails(JSONObject customerJson){
		try {
			name = customerJson.getString(Constants.JSON_NAME);
			city = customerJson.getString(Constants.JSON_CITY);
			state = customerJson.getString(Constants.JSON_STATE);
			address = customerJson.getString(Constants.JSON_ADDRESS);
			zipcode = customerJson.getInt(Constants.JSON_ZIPCODE);
			email = customerJson.getString(Constants.JSON_EMAIL);
			customer_id = customerJson.getInt(Constants.JSON_CONSUMER_ID);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getAddress() {
		return address;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public int getZipcode() {
		return zipcode;
	}
	
	public void clearCustomerDetails()
	{
		name = null;
		city = null;
		state = null;
		address = null;
		zipcode = 0;
		email = null;
		customer_id = 0;
		phonenumber = 0;
	}
}
