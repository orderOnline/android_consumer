package com.invsol.getfoodyc.gcm;

import java.util.Iterator;
import java.util.Set;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.invsol.getfoodyc.GetFoodyCustomerApplication;
import com.invsol.getfoodyc.R;
import com.invsol.getfoodyc.constants.Constants;
import com.invsol.getfoodyc.controllers.AppEventsController;
import com.invsol.getfoodyc.defines.ResponseTags;
import com.invsol.getfoodyc.models.ConnectionModel;
import com.invsol.getfoodyc.view.ChatActivity;
import com.invsol.getfoodyc.view.ConfirmationActivity;
import com.invsol.getfoodyc.view.OrderActivity;

/**
 * This {@code WakefulBroadcastReceiver} takes care of creating and managing a
 * partial wake lock for your app. It passes off the work of processing the GCM
 * message to an {@code IntentService}, while ensuring that the device does not
 * go back to sleep in the transition. The {@code IntentService} calls
 * {@code GcmBroadcastReceiver.completeWakefulIntent()} when it is ready to
 * release the wake lock.
 */
public class GCMBroadcastReceiver extends WakefulBroadcastReceiver {
	
	private static final String TAG = "GcmBroadcastReceiver";
	private Context ctx;
	private String gcmMessage, chatMessage;
	private Notification.InboxStyle inboxStyle;

    @Override
    public void onReceive(Context context, Intent intent) {
    	ctx = context;
    	PowerManager mPowerManager = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        WakeLock mWakeLock = mPowerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, TAG);
        mWakeLock.acquire();
         
        try {
            GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(context);
             
            String messageType = gcm.getMessageType(intent);
            if (GoogleCloudMessaging.MESSAGE_TYPE_SEND_ERROR.equals(messageType)) {
                sendNotification(false);
                 
            } else if (GoogleCloudMessaging.MESSAGE_TYPE_DELETED.equals(messageType)) {
                sendNotification(false);
                 
            } else if (GoogleCloudMessaging.
                    MESSAGE_TYPE_MESSAGE.equals(messageType)) {
            	Log.i(TAG, "Received: " + intent.getExtras().toString());
            	Set<String> keys = intent.getExtras().keySet();
            	for (Iterator<String> iterator = keys.iterator(); iterator.hasNext();) {
					String string = (String) iterator.next();
					if( string.equals("order_status") ){
						gcmMessage = intent.getExtras().getString(string);
		            	 //String orderMsg = new String();
		            	 Log.i(TAG, "Received: " + gcmMessage);
		            	 try {
		            		 JSONObject json = new JSONObject(gcmMessage);
		            		 
		            		 //Code to check whether the app is in foreground or not
		            		 if(GetFoodyCustomerApplication.isActivityVisible()){
		            			 Intent screenChangeIntent = null;
		         					screenChangeIntent = new Intent(ctx,
		         						ConfirmationActivity.class);
		         					screenChangeIntent.putExtra("ORDER-STATUS", gcmMessage);
		         					ctx.startActivity(screenChangeIntent);
		            			 /*final Activity currentActivity = GetFoodyCustomerApplication.getCurrentActivity();
		            			 AlertDialog.Builder builder = new AlertDialog.Builder(
		            					 currentActivity);
		            				builder.setTitle(currentActivity.getResources().getString(R.string.info));
		            				builder.setMessage(json.getString(Constants.JSON_ORDER_STATEMENT));
		            				builder.setCancelable(false);
		            				builder.setPositiveButton(currentActivity.getResources().getString(R.string.OK),
		            						new DialogInterface.OnClickListener() {

		            							@Override
		            							public void onClick(DialogInterface dialog, int which) {
		            								dialog.cancel();
		            							}
		            						});
		            				builder.setNegativeButton(currentActivity.getResources().getString(R.string.action_button_chat),
		            						new DialogInterface.OnClickListener() {

            							@Override
            							public void onClick(DialogInterface dialog, int which) {
            								dialog.cancel();
            								Intent screenChangeIntent = null;
            								screenChangeIntent = new Intent(ctx,
            										ChatActivity.class);
            								ctx.startActivity(screenChangeIntent);
            							}
            						});
		            				AlertDialog alertDialog = builder.create();
		            				alertDialog.show();*/
		            		 }else if(!GetFoodyCustomerApplication.isActivityVisible()){
			            		 /*inboxStyle = new Notification.InboxStyle();
			            		 String[] events = new String[2];
			            		 events[0] = "Order Status: "+json.getString(Constants.JSON_ORDER_STATUS);
			            		 events[1] = "Details: " +json.getString(Constants.JSON_ORDER_STATEMENT);
			            		 // Sets a title for the Inbox in expanded layout
			            		 //inboxStyle.setBigContentTitle("Order details:");
			            		 // Moves events into the expanded layout
			            		 for (int i=0; i < events.length; i++) {
			        			    inboxStyle.addLine(events[i]);
			            		 }*/
			            		 sendNotification(true);
		            		 }
						} catch (JSONException e) {					
							e.printStackTrace();
						}
					}else if(string.equals("CHAT")){
						chatMessage = intent.getExtras().getString(string);
						Log.i(TAG, "Received: " + chatMessage);
		            	 try {
		            		 JSONObject json = new JSONObject(chatMessage);
		            		 //AppEventsController.getInstance().getModelFacade().getChatModel().addOrderChat(json);
		            		 //Code to check whether the app is in foreground or not
		            		 if(GetFoodyCustomerApplication.isActivityVisible()){
		            			 final Activity currentActivity = GetFoodyCustomerApplication.getCurrentActivity();
		            			 ConnectionModel model = AppEventsController.getInstance()
		         						.getModelFacade().getConnModel();
		            			 if( currentActivity instanceof ChatActivity ){
		            				model.setConnectionStatus(ConnectionModel.SUCCESS);
		            				Bundle chatJson = new Bundle();
		            				chatJson.putString(Constants.JSON_CHAT_JSON, json.toString());
		 							model.notifyView(ResponseTags.TAG_CHAT, chatJson);
		            			 }else{
		            				 AlertDialog.Builder builder = new AlertDialog.Builder(
			            					 currentActivity);
		            				builder.setTitle(currentActivity.getResources().getString(R.string.info));
		            				builder.setMessage(currentActivity.getResources().getString(R.string.text_newchat_notification) + json.getString(Constants.JSON_CHAT_OWNER_NAME));
		            				builder.setPositiveButton(currentActivity.getResources().getString(R.string.OK),
		            						new DialogInterface.OnClickListener() {

		            							@Override
		            							public void onClick(DialogInterface dialog, int which) {
		            								dialog.cancel();
		            								Intent screenChangeIntent = null;
		            								screenChangeIntent = new Intent(ctx,
		            										ChatActivity.class);
		            								ctx.startActivity(screenChangeIntent);
		            							}
		            						});
		            				AlertDialog alertDialog = builder.create();
		            				alertDialog.show();
		            			 }
		            		 }else if(!GetFoodyCustomerApplication.isActivityVisible()){
			            		 sendChatNotification( json.getString(Constants.JSON_CHAT_MESSAGE), true);
		            		 }
						} catch (JSONException e) {					
							e.printStackTrace();
						}
					}
            	}
            }
            setResultCode(Activity.RESULT_OK);
        } finally {
            mWakeLock.release();
        }
    }
    
    private void sendNotification(boolean launchApp) {
    	int icon = R.drawable.ic_launcher;
        NotificationManager mNotificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(ctx, ConfirmationActivity.class);
        notificationIntent.putExtra("ORDER-STATUS", gcmMessage);
        JSONObject json;
		try {
			json = new JSONObject(gcmMessage);
		
		  PendingIntent intent = PendingIntent.getActivity(ctx, 0,
				    notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		  Notification notification = new Notification.Builder(ctx)
			 .setContentIntent(intent)
			 .setContentTitle("Order Status: "+json.getString(Constants.JSON_ORDER_STATUS))
			 .setContentText("Details: " +json.getString(Constants.JSON_ORDER_STATEMENT))
	         .setSmallIcon(icon)
	        // .setStyle(inboxStyle)
	        // .setNumber(numOfMessages)
	         .build();
         
        if (launchApp) {
        	notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
        		    | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		  notification.defaults |= Notification.DEFAULT_SOUND;
        		  // Vibrate if vibrate is enabled
        	      notification.defaults |= Notification.DEFAULT_VIBRATE;
        		  notification.flags |= Notification.FLAG_INSISTENT;
        		  notification.flags |= Notification.FLAG_AUTO_CANCEL;
        		  notification.flags |= Notification.FLAG_NO_CLEAR;
        		  mNotificationManager.notify(0, notification);
        }
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
    private void sendChatNotification( String text, boolean launchApp ){
    	int icon = R.drawable.ic_launcher;
        NotificationManager mNotificationManager = (NotificationManager) ctx.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(ctx, ChatActivity.class);
        notificationIntent.putExtra("CHAT", chatMessage);
		  PendingIntent intent = PendingIntent.getActivity(ctx, 0,
				    notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
		  Notification notification = new Notification.Builder(ctx)
			 .setContentIntent(intent)
			 .setContentTitle("Message")
			 .setContentText(text)
	         .setSmallIcon(icon)
	         .build();
         
        if (launchApp) {
        	notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
        		    | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        		  notification.defaults |= Notification.DEFAULT_SOUND;
        		  // Vibrate if vibrate is enabled
        	      notification.defaults |= Notification.DEFAULT_VIBRATE;
        		  notification.flags |= Notification.FLAG_INSISTENT;
        		  notification.flags |= Notification.FLAG_AUTO_CANCEL;
        		  notification.flags |= Notification.FLAG_NO_CLEAR;
        		  mNotificationManager.notify(1, notification);
        }
    }
}