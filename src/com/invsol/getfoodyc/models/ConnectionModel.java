package com.invsol.getfoodyc.models;

import com.invsol.getfoodyc.listeners.ConnListener;


public class ConnectionModel extends BaseModel {

	private int connectionStatus;
	private String connectionErrorMessage;
	private ConnListener listener;
	
	public static final int ERROR = -1;
	public static final int SUCCESS = 0;
	public static final int EXCEPTION = 1;
	
	public ConnectionModel() {

	}

	public int getConnectionStatus() {
		return connectionStatus;
	}

	public void setConnectionStatus(int connectionStatus) {
		this.connectionStatus = connectionStatus;
	}

	public ConnListener getListener() {
		return listener;
	}

	public void setListener(ConnListener listener) {
		this.listener = listener;
	}

	public String getConnectionErrorMessage() {
		return connectionErrorMessage;
	}

	public void setConnectionErrorMessage(String connectionErrorMessage) {
		this.connectionErrorMessage = connectionErrorMessage;
	}

}