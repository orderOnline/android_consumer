package com.invsol.getfoodyc.constants;

public class Constants {
	
	// Required URLs
		public static final String BASE_URL = "http://10.0.2.2:8080/api.invsol.com/";
		public static final String URL_POST_AUTHORIZE_REQUEST = "authorize/consumer.json";
		public static final String URL_POST_AUTHENTICATE_REQUEST = "authenticate/consumer.json";
		public static final String URL_POST_NEW_ORDER = "orders/new.json";
		public static final String URL_POST_CHAT = "orders/new.json";
		// ----------------------------------------------------------------------------------
		
		/**
	     * Shared Preference Name
	     */
	    public static final String DATABASE_PREF_NAME = "offonbLoginPrefName";
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
		public static final String JSON_TYPE = "result";
		public static final String JSON_SUCCESS = "success";
		public static final String JSON_RESPONSE = "response";
		
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
		public static final String JSON_ORDER_STATUS = "orderstatus";
		public static final String JSON_ORDER_MESSAGE = "ordermessage";
		public static final String JSON_ORDER_STATEMENT = "orderstatement";
		public static final String JSON_CHAT_ORDER_ID = "order_id";
		public static final String JSON_CHAT_OWNER_ID = "owner_id";
		public static final String JSON_CHAT_OWNER_NAME = "owner_name";
		public static final String JSON_CHAT_MESSAGE = "message";
		public static final String JSON_CHAT_OWNER_TYPE = "owner_type";
		public static final String JSON_CHAT_JSON = "chatjson";
		public static final String JSON_ORDER_ID = "order_id";
		// ----------------------------------------------------------------------------------
		
		public static final String KEY_ORDER_CONFIRMATION = "orderconfirmation";
}
