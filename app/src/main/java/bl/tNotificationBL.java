package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.tNotificationData;
import library.dal.tNotificationDA;

public class tNotificationBL extends clsMainBL {
	public void saveData(List<tNotificationData> Listdata){
		SQLiteDatabase db=getDb();
		tNotificationDA _tNotificationDA=new tNotificationDA(db);		
		for(tNotificationData data:Listdata){
			_tNotificationDA.SaveDataMConfig(db, data);	
		}
	}
	public List<tNotificationData> getData(String id){
		List<tNotificationData> listData=new ArrayList<tNotificationData>();
		tNotificationDA _tNotificationDA=new tNotificationDA(db);
		if(id.equals("")){
			listData=_tNotificationDA.getAllData(db);
		}else{
			tNotificationData data=new tNotificationData();
			data=_tNotificationDA.getData(db, id);
			listData.add(data);
		}
		return listData;
	}
	
	public List<tNotificationData> getDataStatus(String status){
		List<tNotificationData> listData=new ArrayList<tNotificationData>();
		tNotificationDA _tNotificationDA=new tNotificationDA(db);
		if(status.equals("")){
			listData=_tNotificationDA.getAllData(db);
		}else{
			tNotificationData data=new tNotificationData();
			data=(tNotificationData) _tNotificationDA.getDataStatusN(db, status);
			listData.add(data);
		}
		return listData;
	}
	
	public List<tNotificationData> getAllDataWillAlert(String Status){
		List<tNotificationData> listData=new ArrayList<tNotificationData>();
		tNotificationDA _tNotificationDA=new tNotificationDA(db);
		listData=_tNotificationDA.getAllDataWillAlert(db,Status);
		return listData;
	}

	
	public int  getContactsCount(){
		SQLiteDatabase db=getDb();
		tNotificationDA _tNotificationDA=new tNotificationDA(db);
		return _tNotificationDA.getContactsCount(db);
	}
	public int  getContactsCountStatus(){
		SQLiteDatabase db=getDb();
		tNotificationDA _tNotificationDA=new tNotificationDA(db);
		return _tNotificationDA.getContactsCountStatus(db);
	}
	
	public void SaveDataUpdate(List<tNotificationData> listData){
		SQLiteDatabase db=getDb();
		tNotificationDA _tNotificationDA=new tNotificationDA(db);
		for(tNotificationData dt : listData){
			//_tNotificationDA.UpdateDataItemForSubmit(db, dt.get_guiID());
			_tNotificationDA.updateStatus(db, dt.get_guiID());
		}
	}
	public List<tNotificationData> GetAllData(){
		SQLiteDatabase db=getDb();
		tNotificationDA _tNotificationDA = new tNotificationDA(db);
		return _tNotificationDA.getAllData(db);
	}
	
	
}
