package com.invsol.getfoodyc.net;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;

import com.invsol.getfoodyc.exceptions.ServerException;

public class ConnectivityHandler {

	public static final String TAG = ConnectivityHandler.class.getName();

	private Context context;

	public ConnectivityHandler() {
	}

	public ConnectivityHandler(Context context) {
		this.context = context;
	}

	public boolean isOnline() {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		return (networkInfo != null && networkInfo.isConnected());
	}

	public boolean isMobileDataConnected() {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr
				.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		return networkInfo.isConnected();
	}

	public boolean isWifiConnected() {
		ConnectivityManager connMgr = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr
				.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		return networkInfo.isConnected();
	}

	public byte[] makeHttpRequest(HttpParams params) throws ServerException {
		InputStream is = null;
		Bundle bundle = params.getRequestParamsBundle();
		String finalUrl = bundle.getString(HttpParams.REQUEST_URL) + "?"
				+ encodeUrl(bundle.getBundle(HttpParams.REQUEST_HEADERS));
		Log.d("final url>>", finalUrl);
		String requestMethod = bundle.getString(HttpParams.REQUEST_METHOD);

		URL url;
		try {
			url = new URL(finalUrl);
			
			/*Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(
					"proxy.tcs.com", 8080));
			HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);

			Authenticator.setDefault(new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					if (getRequestorType().equals(RequestorType.PROXY)) {
						return new PasswordAuthentication("477126", "Towers@0914"
								.toCharArray());
					}
					return super.getPasswordAuthentication();
				}
			});*/

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(10000 /* milliseconds */);
			conn.setConnectTimeout(15000 /* milliseconds */);
			conn.setRequestMethod(requestMethod);
			conn.setDoInput(true);
			// Starts the query
			conn.connect();
			int responseCode = conn.getResponseCode();
			Log.d(TAG, "The response is: " + responseCode);
			is = conn.getInputStream();
			return convertStreamToString(is).getBytes();
		} catch (MalformedURLException e) {
			throw new ServerException("Please check the network connections available.");
		} catch (IOException e) {
			throw new ServerException("Please check the network connections available.");
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public byte[] makeHttpPostRequest(HttpParams params, String requestData)
			throws ServerException {
		InputStream is = null;
		OutputStream os = null;
		HttpURLConnection conn = null;
		Bundle bundle = params.getRequestParamsBundle();
		String finalUrl = bundle.getString(HttpParams.REQUEST_URL) + "?"
				+ encodeUrl(bundle.getBundle(HttpParams.REQUEST_HEADERS));
		String requestMethod = bundle.getString(HttpParams.REQUEST_METHOD);

		URL url;
		try {
			url = new URL(finalUrl);

			conn = (HttpURLConnection) url.openConnection();
			conn.setReadTimeout(15000 /* milliseconds */);
			conn.setConnectTimeout(15000 /* milliseconds */);
			conn.setRequestMethod(requestMethod);
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setFixedLengthStreamingMode(requestData.getBytes().length);
			// setup send
			os = new BufferedOutputStream(conn.getOutputStream());
			os.write(requestData.getBytes());
			// clean up
			os.flush();
			// Starts the query
			conn.connect();

			int responseCode = conn.getResponseCode();
			Log.d(TAG, "The response code is: " + responseCode);
			is = conn.getInputStream();
			return convertStreamToString(is).getBytes();
		} catch (MalformedURLException e) {
			throw new ServerException("Please check the network connections available.");
		} catch (IOException e) {
			throw new ServerException("Please check the network connections available.");
		} finally {
			if (is != null) {
				try {
					is.close();
					os.close();
					conn.disconnect();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	public static String encodeUrl(Bundle parameters) {
		Log.d("ASync Task:", "Encode URL");
		if (parameters == null) {
			return "";
		}

		StringBuilder sb = new StringBuilder();
		boolean first = true;
		for (String key : parameters.keySet()) {
			if (first)
				first = false;
			else
				sb.append("&");
			sb.append(key + "=" + parameters.getString(key));
		}
		return sb.toString();
	}

	/**
	 * Method to convert stream to string
	 * 
	 * @param is
	 * @return String
	 */
	public static String convertStreamToString(InputStream is) throws ServerException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		StringBuilder sb = new StringBuilder();
		String line = null;
		try {
			while ((line = reader.readLine()) != null) {
				sb.append(line + "\n");
			}
		} catch (IOException e) {
			throw new ServerException("Problem reading data from the server. Please try again later.");
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				throw new ServerException("Problem reading data from the server. Please try again later.");
			}
		}
		Log.v(TAG, "String response====>" + sb.toString());
		return sb.toString();
	}

	// ---------------------------------------------------------------------------------------------------------

}