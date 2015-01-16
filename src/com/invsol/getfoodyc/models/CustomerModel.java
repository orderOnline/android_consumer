package com.invsol.getfoodyc.models;

import android.util.Log;

public class CustomerModel {

	private boolean isCustomerLoggedIn = false;
	private String gcm_registration_key;
	private int customer_id;
	private long phonenumber;
	
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
	
	
}
