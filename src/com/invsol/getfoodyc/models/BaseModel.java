package com.invsol.getfoodyc.models;

import java.util.Vector;

import android.os.Bundle;

import com.invsol.getfoodyc.listeners.ActivityUpdateListener;

public class BaseModel {
	public static final String TAG = "Base Model";
	/**
	 * Vector holding registered managers
	 */
	private Vector<ActivityUpdateListener> registeredView;

	// --------------------------------------------------------------------------------------------

	/**
	 * Constructor
	 */
	public BaseModel() {
		registeredView = new Vector<ActivityUpdateListener>();
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * Register view which should be updated.
	 */
	public void registerView(ActivityUpdateListener view) {
		registeredView.addElement(view);
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * UnRegister view which should be updated.
	 */
	public void unregisterView(ActivityUpdateListener view) {
		registeredView.removeElement(view);
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * UnRegister view which should be updated.
	 */
	public void unregisterAllView() {
		registeredView.removeAllElements();
	}

	// --------------------------------------------------------------------------------------------

	/**
	 * Notify view which should be updated.
	 */
	public void notifyView(int tag, Bundle data) {
		int viewsCount = registeredView.size();
		ActivityUpdateListener view = null;
		for (int i = 0; i < viewsCount; i++) {
			view = (ActivityUpdateListener) registeredView.elementAt(i);
			view.updateActivity(tag, data);
		}
	}
	// --------------------------------------------------------------------------------------------

	public Vector<ActivityUpdateListener> getRegisteredView() {
		return registeredView;
	}
}