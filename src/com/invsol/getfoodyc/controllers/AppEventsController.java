package com.invsol.getfoodyc.controllers;

import com.invsol.getfoodyc.defines.NetworkEvents;
import com.invsol.getfoodyc.models.ModelFacade;
import com.invsol.getfoodyc.net.NetworkResponseHandler;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class AppEventsController {
	
	public static final String TAG = "Application Controller";
	/**
	 * Singleton Class Reference
	 */
	public static AppEventsController appController;
	/**
	 * ModelFacade Reference
	 */
	private ModelFacade modelFacade;

	// ---------------------------------------------------------------------------------

	/**
	 * Constructor
	 */
	private AppEventsController() {
		modelFacade = new ModelFacade();
	}

	// ---------------------------------------------------------------------------------

	/**
	 * Get Single instance of this class
	 * 
	 * @return ApplicationController single instance
	 */
	public static AppEventsController getInstance() {
		if (appController == null) {
			// creating new instance of application controller
			appController = new AppEventsController();
		}
		return appController;
	}

	// ---------------------------------------------------------------------------------

	/**
	 * Model Facade Reference
	 * 
	 * @return ModelFacade Reference
	 */
	public ModelFacade getModelFacade() {
		return modelFacade;
	}

	// ---------------------------------------------------------------------------------

	/**
	 * Handle Event Based on Event ID and Object
	 * 
	 * @param eventId
	 *            Event raised
	 * @param eventObjects
	 *            It stores the data for the given Event
	 */
	public void handleEvent(int eventId, Bundle eventData, View view) {
		fireEvents(eventId, eventData, view);
	}

	// ---------------------------------------------------------------------------------

	/**
	 * Method to actually handle events
	 */
	private void fireEvents(int eventId, Bundle eventData, View view) {

	}

	// ---------------------------------------------------------------------------------

}
