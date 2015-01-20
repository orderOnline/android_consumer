package com.invsol.getfoodyc.constants;

public class Constants {
	
	// Required URLs
		//public static final String BASE_URL = "http://10.65.66.184:8080/jax-rs-heroku/";
		public static final String BASE_URL = "http://www.medoco.in/";
		public static final String URL_POST_LOGIN_REQUEST = "login/consumer.json";
		public static final String URL_POST_REGISTER_REQUEST = "register/consumer.json";
		public static final String URL_POST_VALIDATEOTP_REQUEST = "register/consumer/validateotp.json";
		public static final String URL_POST_NEW_ORDER = "orders/new.json";
		public static final String URL_POST_CHAT = "orders/new.json";
		// ----------------------------------------------------------------------------------
		
		/**
	     * Shared Preference Name
	     */
	    public static final String LOGIN_DATABASE_PREF_NAME = "LoginPrefName";
	    public static final String LOGIN_STATUS = "LoginStatus";
	    public static final String TEXT_DATABASE_ACCESS_VALUE_DEFAULT = "DatabaseKeyDoesNotExist";
	    //--------------------------------------------------------------------
	    
	    /**
	     * GCM Requirements
	     */
	    public static final String GCM_SENDER_ID = "244460346317";
	  //--------------------------------------------------------------------
		
		// Response Handling Constants
		public static final int SUCCESSFUL_RESPONSE = 0;
		public static final int ERROR = 1;
		public static final int EXCEPTION = 2;
		// ----------------------------------------------------------------------------------
		public static final String JSON_RESULT = "result";
		public static final String JSON_TYPE = "type";
		public static final String JSON_SUCCESS = "success";
		public static final String JSON_RESPONSE = "response";
		public static final String JSON_ERROR_MESSAGE = "errormessage";
		
		public static final String JSON_POST_DATA = "jsonpostdata";
		public static final String JSON_PHONENUMBER = "phonenumber";
		public static final String JSON_PASSWORD = "password";
		public static final String JSON_ORDER_TOTAL = "order_total";
		public static final String JSON_RESTAURANT_ID = "restaurant_id";
		public static final String JSON_CONSUMER_ID = "consumer_id";
		public static final String JSON_TIMESTAMP = "timestamp";
		public static final String JSON_ORDER_ITEMS = "order_items";
		public static final String JSON_ITEM_ID = "item_id";
		public static final String JSON_QUANTITY = "quantity";
		public static final String JSON_INSTRUCTIONS = "instructions";
		public static final String JSON_ADDRESS = "address";
		public static final String JSON_ORDER_STATUS = "order_status";
		public static final String JSON_ORDER_MESSAGE = "order_message";
		public static final String JSON_ORDER_STATEMENT = "orderstatement";
		public static final String JSON_CHAT_ORDER_ID = "order_id";
		public static final String JSON_CHAT_OWNER_ID = "owner_id";
		public static final String JSON_CHAT_OWNER_NAME = "owner_name";
		public static final String JSON_CHAT_MESSAGE = "message";
		public static final String JSON_CHAT_OWNER_TYPE = "owner_type";
		public static final String JSON_CHAT_JSON = "chatjson";
		public static final String JSON_ORDER_ID = "order_id";
		public static final String JSON_OTPCODE = "otpcode";
		public static final String JSON_GCM_KEY = "gcm_key";
		public static final String JSON_VALID_OTP_CODE = "valid otp";
		public static final String JSON_NAME = "name";
		public static final String JSON_EMAIL = "email";
		public static final String JSON_CITY = "city";
		public static final String JSON_STATE = "state";
		public static final String JSON_ZIPCODE = "zipcode";
		// ----------------------------------------------------------------------------------
		
		public static final String KEY_ORDER_CONFIRMATION = "orderconfirmation";
}
