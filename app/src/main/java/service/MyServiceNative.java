package service;

import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.database.sqlite.SQLiteDatabase;
import android.os.IBinder;
import android.util.Log;

import org.json.JSONException;
import org.json.simple.JSONArray;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;

import bl.clsHelperBL;
import library.common.clsPushData;
import library.common.dataJson;
import library.common.mCounterNumberData;
import library.common.tAbsenUserData;
import library.common.tActivityData;
import library.common.tUserLoginData;
import library.dal.clsHardCode;
import library.dal.enumCounterData;
import library.dal.mCounterNumberDA;
import library.dal.tAbsenUserDA;
import library.dal.tActivityDA;
import library.dal.tUserLoginDA;

public class MyServiceNative extends Service{
	public MyServiceNative() {
	}

	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	
	@Override
    public void onCreate() {
        //Toast.makeText(this, "Welcome Kalbe SPG Mobile", Toast.LENGTH_LONG).show();
       
    }
	
	//private static long UPDATE_INTERVAL = 1*36*1000;  //default
	private static long UPDATE_INTERVAL = 1*360*1000;;  //default
	//private static long UPDATE_INTERVAL_DELAY = 180000;  //default
	private static long UPDATE_INTERVAL_TESTING = 6000;  //default
    private static Timer timer = new Timer(); 
	private void _startService()
    {      
//		long intInverval=0;
//		if(new clsMainBL().getLIVE().equals("1")){
//			intInverval=UPDATE_INTERVAL;
//		}else{
//			intInverval=UPDATE_INTERVAL_TESTING;
//		}
//
//		if(timer != null)
//	    {
//			timer.cancel();
//	    }
//		timer = new Timer();
//        timer.scheduleAtFixedRate(
//                new TimerTask() {
//                    public void run() {
//                    	try {
//							doServiceWork();
//						} catch (JSONException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}
//                    }
//                }, 30000,intInverval);
        //Log.i(getClass().getSimpleName(), "FileScannerService Timer started....");
    }

    private void doServiceWork() throws JSONException
    {
    	clsPushData dtJson= new clsHelperBL().pushData();
    	if(dtJson==null){
    		_shutdownService();
    	}else{
    		String versionName="";
    		try {
    			versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
    		} catch (NameNotFoundException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		}
    		try {
    			JSONArray JsonArrayResult=new clsHelperBL().callPushDataReturnJson(versionName,dtJson.getDtdataJson().txtJSON().toString(),dtJson.getFileUpload());
				new clsHelperBL().saveDataPush(dtJson.getDtdataJson(),JsonArrayResult);
				Intent serviceIntent = new Intent(this,MyNotificationService.class);
				serviceIntent.putExtra("From", "PUSHDATA");
				startService(serviceIntent);
		    
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	
    	SQLiteDatabase db;
    	String versionName="";
    	try {
			versionName = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		} catch (NameNotFoundException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		clsHardCode clsdthc = new clsHardCode();
		db = SQLiteDatabase.openOrCreateDatabase(clsdthc.txtDatabaseName,null);
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		if(_tUserLoginDA.getContactsCount(db)> 0){
			tUserLoginData _tUserLoginData=_tUserLoginDA.getData(db, 1);

			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    		Calendar cal = Calendar.getInstance();
				mCounterNumberDA _mCounterNumberDA=new mCounterNumberDA(db);
				mCounterNumberData _data =new mCounterNumberData();
				_data.set_intId(enumCounterData.MonitorSchedule.getidCounterData());
				_data.set_txtDeskripsi("value menunjukan waktu terakhir menjalankan services");
				_data.set_txtName("Monitor Service");
				_data.set_txtValue(dateFormat.format(cal.getTime()));
				_mCounterNumberDA.SaveDataMConfig(db, _data);

				//new clsInit().PushData(db,versionName);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			tAbsenUserDA _tAbsenUserDA =new tAbsenUserDA (db);
			tActivityDA _tActivityDA =new tActivityDA (db);

			List<tAbsenUserData> ListOftAbsenUserData=_tAbsenUserDA.getAllDataToPushData(db);
			List<tActivityData> ListOftActivityData=_tActivityDA.getAllDataToPushData(db);
			dataJson dtPush=new dataJson();
			HashMap<String, String> FileUpload=null;
			FileUpload=new HashMap<String, String>();
			if(ListOftAbsenUserData!= null){
				dtPush.setListOftAbsenUserData(ListOftAbsenUserData);
				for (tAbsenUserData dttAbsenUserData : ListOftAbsenUserData) {
					if(dttAbsenUserData.get_txtImg1()!=null){
						FileUpload.put("FUAbsen1"+dttAbsenUserData.get_intId(), dttAbsenUserData.get_txtImg1().toString());
					}
					if(dttAbsenUserData.get_txtImg2()!=null){
						FileUpload.put("FUAbsen2"+dttAbsenUserData.get_intId(), dttAbsenUserData.get_txtImg2().toString());
					}
				}
			}
			if(ListOftActivityData!=null){
				dtPush.setListOftActivityData(ListOftActivityData);
				for (tActivityData dttActivityData : ListOftActivityData) {
					if(dttActivityData.get_txtImg1()!=null){
						FileUpload.put("FUActivity1"+dttActivityData.get_intId(), dttActivityData.get_txtImg1().toString());
					}
					if(dttActivityData.get_txtImg2()!=null){
						FileUpload.put("FUActivity2"+dttActivityData.get_intId(), dttActivityData.get_txtImg2().toString());
					}
				}
			}
		}
		else{
			_shutdownService();
		}

		db.close();
    }

    private void _shutdownService()
    {
        if (timer != null) timer.cancel();
        Log.i(getClass().getSimpleName(), "Timer stopped...");
    }
 
    @Override
    public void onStart(Intent intent, int startId) {
    	// For time consuming an long tasks you can launch a new thread here...
        //Toast.makeText(this, "Welcome Kalbe SPG Mobile", Toast.LENGTH_LONG).show();
    	_startService();
   
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
    	//Toast.makeText(this, " onStartCommand", Toast.LENGTH_LONG).show();
    	_startService();
        return START_STICKY;
    }
 
    @Override
    public void onDestroy() {
        //Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
        _shutdownService();
    }
}
