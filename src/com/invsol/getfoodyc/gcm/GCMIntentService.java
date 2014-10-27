package com.invsol.getfoodyc.gcm;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;

public class GCMIntentService  extends IntentService {
	
	Context context = this;
	public static final int NOTIFICATION_ID = 1;
    NotificationCompat.Builder builder;
    public static final String TAG = "GCM Intent Service";
    
	public GCMIntentService() {
		super("GCMIntentService");
	}

	@Override
	protected void onHandleIntent(Intent intent) {
		// TODO Auto-generated method stub
		Bundle extras = intent.getExtras();
		GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
		String messageType = gcm.getMessageType(intent);

		 if (!extras.isEmpty()) {

			 if (GoogleCloudMessaging.
	                    MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
	                Log.i(TAG,"Send error: " + extras.toString());
	            } else if (GoogleCloudMessaging.
	                    MESSAGE_TYPE_DELETED.equals(messageType)) {
	            	Log.i(TAG,"Deleted messages on server: " +
	                        extras.toString());
	            // If it's a regular GCM message, do some work.
	            } else if (GoogleCloudMessaging.
	                    MESSAGE_TYPE_MESSAGE.equals(messageType)) {
	            	 Log.i(TAG, "Received: " + extras.toString());
	            }
	        }
		 GCMBroadcastReceiver.completeWakefulIntent(intent);
	}
}