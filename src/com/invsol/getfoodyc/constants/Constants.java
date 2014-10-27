package com.invsol.getfoodyc.constants;

public class Constants {
	
	// Required URLs
		public static final String BASE_URL = "http://10.0.2.2:8080/api.invsol.com/";
		public static final String URL_POST_AUTHORIZE_REQUEST = "authorize/consumer.json";
		public static final String URL_POST_AUTHENTICATE_REQUEST = "authenticate/consumer.json";
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
		
		public static final String JSON_POST_DATA = "jsonpostdata";
		public static final String JSON_PHONENUMBER = "phonenumber";
		public static final String JSON_PASSWORD = "password";
		// ----------------------------------------------------------------------------------
}
