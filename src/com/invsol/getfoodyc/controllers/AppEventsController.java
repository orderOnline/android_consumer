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
		switch (eventId) {
		case NetworkEvents.EVENT_ID_REGISTER: {
			try {
				modelFacade.getRemoteModel().registerUser(eventData,
						NetworkResponseHandler.REGISTERUSER_HANDLER, view);
			} catch (Exception ex) {
				Log.d("Application Exception:", ex.getMessage());
			}
		}

			break;
		case NetworkEvents.EVENT_ID_REGISTER_VALIDATEOTP: {
			try {
				modelFacade
						.getRemoteModel()
						.registerUserValidateOTP(
								eventData,
								NetworkResponseHandler.REGISTERUSER_VALIDATEOTP_HANDLER,
								view);
			} catch (Exception ex) {
				Log.d("Application Exception:", ex.getMessage());
			}
		}

			break;
		case NetworkEvents.EVENT_ID_LOGIN: {
			Log.d(TAG, "Creating Bundle");
			try {
				modelFacade.getRemoteModel().loginUser(eventData,
						NetworkResponseHandler.LOGINUSER_HANDLER, view);
			} catch (Exception ex) {
				Log.d("Application Exception:", ex.getMessage());
			}
		}
			break;
		case NetworkEvents.EVENT_ID_PLACEORDER: {
			try {
				modelFacade.getRemoteModel().placeOrder(eventData,
						NetworkResponseHandler.PLACEORDER_HANDLER, view);
			} catch (Exception ex) {
				Log.d("Application Exception:", ex.getMessage());
			}
		}
			break;
		case NetworkEvents.EVENT_ID_UNREGISTER:{
			try {
				modelFacade.getRemoteModel().deleteConsumer(eventData,
						NetworkResponseHandler.UNREGISTERCONSUMER_HANDLER, view);
			} catch (Exception ex) {
				Log.d("Application Exception:", ex.getMessage());
			}
		}
		break;
		case NetworkEvents.EVENT_ID_SEARCH:{
			try {
				modelFacade.getRemoteModel().getRestaurants(eventData,
						NetworkResponseHandler.SEARCH_RESTAURANTS_HANDLER, view);
			} catch (Exception ex) {
				Log.d("Application Exception:", ex.getMessage());
			}
		}
		break;
	}
	}

	// ---------------------------------------------------------------------------------

}
