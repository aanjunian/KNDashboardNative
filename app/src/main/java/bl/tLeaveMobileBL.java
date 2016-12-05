package bl;


import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.tLeaveMobileData;
import library.dal.tLeaveMobileDA;

public class tLeaveMobileBL extends clsMainBL {
	public void saveData(List<tLeaveMobileData> Listdata){
		SQLiteDatabase db=getDb();
		tLeaveMobileDA _tLeaveMobileDA=new tLeaveMobileDA(db);		
		for(tLeaveMobileData data:Listdata){
			_tLeaveMobileDA.SaveDataMConfig(db, data);	
		}
	}
	public List<tLeaveMobileData> getData(String id){
		List<tLeaveMobileData> listData=new ArrayList<tLeaveMobileData>();
		tLeaveMobileDA _tLeaveMobileDA=new tLeaveMobileDA(db);
		if(id.equals("")){
			listData=_tLeaveMobileDA.getAllData(db);
		}else{
			tLeaveMobileData data=new tLeaveMobileData();
			data=_tLeaveMobileDA.getData(db, id);
			listData.add(data);
		}
		return listData;
	}
	public List<tLeaveMobileData>  getAllDataToPushData(){
		SQLiteDatabase db=getDb();
		tLeaveMobileDA _tLeaveMobileDA=new tLeaveMobileDA(db);
		List<tLeaveMobileData> ListData=new ArrayList<tLeaveMobileData>();
		ListData=_tLeaveMobileDA.getAllDataPushData(db);
		return ListData;
	}
	public int  getContactsCount(){
		SQLiteDatabase db=getDb();
		tLeaveMobileDA _tLeaveMobileDA=new tLeaveMobileDA(db);
		return _tLeaveMobileDA.getContactsCount(db);
	}
}
