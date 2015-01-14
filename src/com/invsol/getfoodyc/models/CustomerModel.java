package com.invsol.getfoodyc.models;

import android.util.Log;

public class CustomerModel {

	private String gcm_registration_key;
	
	public String getGcm_registration_key() {
		return gcm_registration_key;
	}

	public void setGcm_registration_key(String gcm_registration_key) {
		Log.d("Restaurant Model", "key revd=="+gcm_registration_key);
		this.gcm_registration_key = gcm_registration_key;
	}
}
