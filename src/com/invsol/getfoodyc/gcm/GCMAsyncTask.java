package com.invsol.getfoodyc.gcm;

import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.invsol.getfoodyc.constants.Constants;

/**
 * Registers the application with GCM servers asynchronously.
 * <p>
 * Stores the registration ID and app versionCode in the application's
 * shared preferences.
 */
public class GCMAsyncTask  extends AsyncTask<Void, Void, String> {

	public static final String TAG = "GCMAsyncTask";
	
	private Context reqContext;
//	private String progressMsg;
	private Handler responseListener;
	private GoogleCloudMessaging gcm;
	private boolean exceptionRaised;
	private static int count = 0;

	/**
	 * @param reqContext
	 * @param progressMsg
	 * @param responseListener
	 */
	public GCMAsyncTask(Context reqContext,
			String progressMsg, Handler responseListener) {
		super();
		this.reqContext = reqContext;
//		this.progressMsg = progressMsg;
		this.responseListener = responseListener;
		exceptionRaised = false;
	}

	@Override
	protected String doInBackground(Void... params) {
		String registration_id = "";
        try {
        	System.out.println("Inside GCM asynctask");
            if (gcm == null) {
                gcm = GoogleCloudMessaging.getInstance(reqContext);
            }
            count++;
            Log.d(TAG, "GCM Registration attempt #"+count);
            registration_id = gcm.register(Constants.GCM_SENDER_ID);
            System.out.println("REGISTRATION ID "+registration_id);
            // Persist the regID - no need to register again.
          //  storeRegistrationId(reqContext, registration_id);
        } catch (IOException exception) {
        	exceptionRaised = true;
        	exception.printStackTrace();        	
			responseListener.sendMessage(responseListener.obtainMessage(
					Constants.EXCEPTION, exception));
			this.cancel(exceptionRaised);
        }
        return registration_id;
	}

	@SuppressLint("NewApi")
	@Override
    protected void onPostExecute(String registration_id) {
		if (!exceptionRaised) {
			if (registration_id != null && !registration_id.isEmpty())
				responseListener.sendMessage(responseListener.obtainMessage(
						Constants.SUCCESSFUL_RESPONSE, registration_id));
			/*else
				responseListener.sendMessage(responseListener.obtainMessage(
						Constants.ERROR, registration_id));*/
		}
	}
}
