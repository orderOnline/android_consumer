package com.invsol.getfoodyc.view;

import java.util.concurrent.atomic.AtomicInteger;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;

import com.invsol.getfoodyc.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.invsol.getfoodyc.constants.Constants;
import com.invsol.getfoodyc.controllers.AppEventsController;
import com.invsol.getfoodyc.gcm.GCMAsyncTask;

public class SplashActivity extends FragmentActivity{

	// Time in Milliseconds
	private int SPLASH_TIMER = 2000;
	private String accessToken;
	private String TAG = "SplashActivity";
	
	//GCM Variables
	/**
     * Substitute you own sender ID here. This is the project number you got
     * from the API Console, as described in "Getting Started."
     */
    String SENDER_ID = "upheld-archway-726";
    AtomicInteger msgId = new AtomicInteger();
    private String regid;
    private GoogleCloudMessaging gcm;
    private Context context;
    private final static int PLAY_SERVICES_RESOLUTION_REQUEST = 9000;
	private static final String EXTRA_MESSAGE = "message";
	private static final String PROPERTY_REG_ID = "registration_id";
    private static final String PROPERTY_APP_VERSION = "appVersion";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_splash);
		
		context = getApplicationContext();
		
		if(checkPlayServices()){
			gcm = GoogleCloudMessaging.getInstance(this);
            regid = getRegistrationId(context);

            if (regid.isEmpty()) {
                GCMAsyncTask gcmRegistrationTask = new GCMAsyncTask(context, "", gcmResponseHandler());
                gcmRegistrationTask.execute(null, null, null);
            }
		}else{
			Log.i(TAG, "No valid Google Play Services APK found.");
		}
		
		/*SharedPreferences sharedPref = getSharedPreferences(
				Constants.DATABASE_PREF_NAME, MODE_PRIVATE);
		accessToken = sharedPref.getString(Constants.TEXT_ACCESSTOKEN,
				Constants.TEXT_DATABASE_ACCESS_VALUE_DEFAULT);
		AppEventsController.getInstance().getModelFacade().getUserModel().setAccessToken(accessToken);*/
		
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent screenChangeIntent = null;
				screenChangeIntent = new Intent(SplashActivity.this,
						LaunchActivity.class);
				SplashActivity.this.startActivity(screenChangeIntent);
				SplashActivity.this.finish();
				
			}
		}, SPLASH_TIMER);

	}
	
	/**
	 * Check the device to make sure it has the Google Play Services APK. If
	 * it doesn't, display a dialog that allows users to download the APK from
	 * the Google Play Store or enable it in the device's system settings.
	 */
	private boolean checkPlayServices() {
	    int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
	    if (resultCode != ConnectionResult.SUCCESS) {
	        if (GooglePlayServicesUtil.isUserRecoverableError(resultCode)) {
	            GooglePlayServicesUtil.getErrorDialog(resultCode, this,
	                    PLAY_SERVICES_RESOLUTION_REQUEST).show();
	        } else {
	            Log.i(TAG , "This device is not supported.");
	            finish();
	        }
	        return false;
	    }
	    return true;
	}
	
	/**
	 * Gets the current registration ID for application on GCM service.
	 * <p>
	 * If result is empty, the app needs to register.
	 *
	 * @return registration ID, or empty string if there is no existing
	 *         registration ID.
	 */
	private String getRegistrationId(Context context) {
	    final SharedPreferences prefs = getGCMPreferences(context);
	    String registrationId = prefs.getString(PROPERTY_REG_ID, "");
	    if (registrationId.isEmpty()) {
	        Log.i(TAG, "Registration not found.");
	        return "";
	    }
	    // Check if app was updated; if so, it must clear the registration ID
	    // since the existing regID is not guaranteed to work with the new
	    // app version.
	    int registeredVersion = prefs.getInt(PROPERTY_APP_VERSION, Integer.MIN_VALUE);
	    int currentVersion = getAppVersion(context);
	    if (registeredVersion != currentVersion) {
	        Log.i(TAG, "App version changed.");
	        return "";
	    }
	    return registrationId;
	}
	
	/**
	 * @return Application's {@code SharedPreferences}.
	 */
	private SharedPreferences getGCMPreferences(Context context) {
	    // This sample app persists the registration ID in shared preferences, but
	    // how you store the regID in your app is up to you.
	    return getSharedPreferences(SplashActivity.class.getSimpleName(),
	            Context.MODE_PRIVATE);
	}
	
	/**
	 * @return Application's version code from the {@code PackageManager}.
	 */
	private static int getAppVersion(Context context) {
	    try {
	        PackageInfo packageInfo = context.getPackageManager()
	                .getPackageInfo(context.getPackageName(), 0);
	        return packageInfo.versionCode;
	    } catch (NameNotFoundException e) {
	        // should never happen
	        throw new RuntimeException("Could not get package name: " + e);
	    }
	}
	
	private void storeGCMRegKey()
	{
		final SharedPreferences prefs = getGCMPreferences(context);
		Editor editor = prefs.edit();
	    editor.putString(PROPERTY_REG_ID, AppEventsController.getInstance().getModelFacade().getCustomerModel().getGcm_registration_key());
	    editor.putInt(PROPERTY_APP_VERSION, getAppVersion(SplashActivity.this));
	    editor.commit();
	}
	
	private Handler gcmResponseHandler() {
		return new Handler() {
			@Override
			public void handleMessage(Message msg) {
				switch (msg.what) {
				case Constants.SUCCESSFUL_RESPONSE: {
					AppEventsController.getInstance().getModelFacade().getCustomerModel().setGcm_registration_key(msg.obj.toString());
					storeGCMRegKey();
				}
					break;
				case Constants.EXCEPTION: {
				}
					break;
				}
			}

		};
	}
}
