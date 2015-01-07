package com.invsol.getfoodyc.net;

import android.os.Bundle;
import android.util.Log;

public class HttpParams {
	public static final String TAG = "HttpParams";
	/**
	 * Bundle holding request params
	 */
	private Bundle requestParamsBundle;
	/**
	 * Request URL
	 */
	private String requestURL;
	/**
	 * Request Method
	 */
	private String requestMethod;
	/**
	 * Request Data
	 */
	private String requestData;
	/**
	 * Bundle to store headers
	 */
	private Bundle headersBundle;

	/**
	 * Static values
	 */
	public static final String REQUEST_URL = "Request_URL";
	public static final String REQUEST_METHOD = "Request_Method";
	public static final String REQUEST_HEADERS = "Request_Headers";
	public static final String REQUEST_DATA = "Request_Data";

	public static final String HTTP_GET = "GET";
	public static final String HTTP_POST = "POST";
	public static final String HTTP_PUT = "PUT";
	public static final String HTTP_DELETE = "DELETE";

	// -----------------------------------------------------------------------------------------------------------

	/**
	 * Constructor
	 */
	public HttpParams() {
		requestParamsBundle = new Bundle();
		headersBundle = new Bundle();
	}

	// -----------------------------------------------------------------------------------------------------------

	/**
	 * @return the requestParamsBundle
	 */
	public Bundle getRequestParamsBundle() {
		requestParamsBundle.putString(REQUEST_URL, requestURL);
		requestParamsBundle.putString(REQUEST_METHOD, requestMethod);
		if (requestMethod.equals(HTTP_POST) || requestMethod.equals(HTTP_PUT))
			requestParamsBundle.putString(REQUEST_DATA, requestData);
		requestParamsBundle.putBundle(REQUEST_HEADERS, headersBundle);
		return requestParamsBundle;
	}

	// -----------------------------------------------------------------------------------------------------------

	/**
	 * Method to set request url
	 */
	public void setRequestURL(String reqString) {
		Log.v(TAG, "Request URL=====>" + reqString);
		this.requestURL = reqString;
	}

	// -----------------------------------------------------------------------------------------------------------

	/**
	 * Method to set request method
	 */
	public void setRequestMethod(String reqMethod) {
		Log.v(TAG, "Request METHOD=====>" + reqMethod);
		this.requestMethod = reqMethod;
	}

	// -----------------------------------------------------------------------------------------------------------

	public void setRequestData(String requestData) {
		this.requestData = requestData;
	}

	/**
	 * Method to set request params
	 */
	public void setRequestHeaders(String key, String value) {
		headersBundle.putString(key, value);
	}

	// -----------------------------------------------------------------------------------------------------------

	/**
	 * Method to set request params
	 */
	public void setRequestHeadersBundle(Bundle headersBundle) {
		this.headersBundle = headersBundle;
	}
	// -----------------------------------------------------------------------------------------------------------
}