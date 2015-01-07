package com.invsol.getfoodyc;

import android.app.Activity;
import android.app.Application;

public class GetFoodyCustomerApplication  extends Application{
	
	private static boolean activityVisible;
	
	public static boolean isActivityVisible() {
	    return activityVisible;
	  }  

	  public static void activityResumed() {
	    activityVisible = true;
	  }

	  public static void activityPaused() {
	    activityVisible = false;
	  }
	  
	  private static Activity mCurrentActivity = null;
      public static Activity getCurrentActivity(){
            return mCurrentActivity;
      }
      public static void setCurrentActivity(Activity activity){
            mCurrentActivity = activity;
      }
      
      public static void clearReferences(){
        	  mCurrentActivity = null;
      }
}