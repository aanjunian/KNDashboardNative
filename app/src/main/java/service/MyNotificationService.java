package service;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import bl.tNotificationBL;
//import com.example.viewbadger.ShortcutBadger;
import com.kalbenutritionals.xteamnative.R;

import library.common.tNotificationData;

public class MyNotificationService extends Service{
	@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
//	String titleFinal = null;
//	String descFinal = null;
//	String className = null;
//	String classNameF =null;
//	private static final String TAG_UUID = "id";
//	@Override
//	public IBinder onBind(Intent intent) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//
//	@SuppressLint("NewApi") @Override
//	@Deprecated
//	public void onStart(Intent intent, int startId) {
//		super.onStart(intent, startId);
//		if(intent!=null){
//			Bundle extras = intent.getExtras();
//			 if(extras == null)
//			     Log.d("Service","null");
//			 else
//			 {
//			     Log.d("Service","not null");
//			     String from = (String) extras.get("From");
//		     	if(from.equalsIgnoreCase("PUSHDATA")){
//		     		tNotificationBL _tNotificationBL=new tNotificationBL();
//		     		List<tNotificationData> ListData=_tNotificationBL.getAllDataWillAlert("2");
//		     		List<tNotificationData> tmpListData= new ArrayList<tNotificationData>();
//		     		if(ListData!=null){
//		     			for (tNotificationData dttNotificationData : ListData) {
//		     				dttNotificationData.set_txtStatus("1");
//		     				tmpListData.add(dttNotificationData);
//		     				 String index = String.valueOf(dttNotificationData.get_intIndex());
//		    		    	 String title = String.valueOf(dttNotificationData.get_txtTitle());
//		    		    	 String desc = String.valueOf(dttNotificationData.get_txtDescription());
//		    		    	 String className = String.valueOf(dttNotificationData.get_txtLink());
//		     				Class<?> myClass = null;
//		    				try {
//		    					myClass = Class.forName(className);
//		    				} catch (ClassNotFoundException e) {
//		    					e.printStackTrace();
//		    				}
//		     				Intent i = new Intent(getApplicationContext(), MainMenu.class);
//		     				i.putExtra("key_view", "Notifcation");
//		    				i.putExtra(TAG_UUID, String.valueOf(dttNotificationData.get_guiID()));
//		     				int idn = Integer.parseInt(index);
//
//							PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),idn, i, PendingIntent.FLAG_ONE_SHOT);
//
//							int icon = R.mipmap.ic_kalbe_phonegap;
//							String tickerText = dttNotificationData.get_txtTitle();
//							long when = System.currentTimeMillis();
//							Notification tnotification = new Notification.Builder(MyNotificationService.this)
//							.setContentIntent(pendingIntent)
//			                .setContentTitle(title)
//			                .setContentText(desc)
//			                .setSmallIcon(icon)
//			                .setWhen(when)
//			                .setTicker(tickerText)
//			                .setPriority(Notification.PRIORITY_HIGH)
//			     			.setAutoCancel(true)
//			                .setDefaults(Notification.DEFAULT_ALL | Notification.FLAG_SHOW_LIGHTS | Notification.PRIORITY_DEFAULT)
//			                .build();
//							NotificationManager tnotificationManager=(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
//							tnotification.defaults=Notification.DEFAULT_ALL;
//							tnotificationManager.notify(idn,tnotification);
//						}
//		     			_tNotificationBL.saveData(tmpListData);
//		     			int totalStatus = new tNotificationBL().getContactsCountStatus();
//				     	ShortcutBadger.applyCount(MyNotificationService.this, totalStatus);
//		     		}
//			     	}
//			     }
//		}
//		stopSelf();
//	 }
	}

