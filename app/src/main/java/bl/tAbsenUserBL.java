package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.tAbsenUserData;
import library.dal.tAbsenUserDA;

public class tAbsenUserBL extends clsMainBL {
	public void saveData(List<tAbsenUserData> Listdata){
		SQLiteDatabase db=getDb();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);		
		for(tAbsenUserData data:Listdata){
			_tAbsenUserDA.SaveDatatAbsenUserData(db, data);	
		}
	}
	public List<tAbsenUserData> GetData(String IdAbsen){
		SQLiteDatabase db=getDb();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
		List<tAbsenUserData> ListData=new ArrayList<tAbsenUserData>();
		if(IdAbsen.equals("")){
			ListData=_tAbsenUserDA.getAllData(db);
		}else{
			tAbsenUserData dt=new tAbsenUserData();
			dt=_tAbsenUserDA.getData(db, Integer.valueOf(IdAbsen));
			ListData.add(dt);
		}
		return ListData;
	}
	public tAbsenUserData getDataCheckInActive(){
		SQLiteDatabase db=getDb();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
		tAbsenUserData dt=new tAbsenUserData();
		dt=_tAbsenUserDA.getDataCheckInActive(db);
		return dt;
	}
	public List<tAbsenUserData>  getAllDataToPushData(){
		SQLiteDatabase db=getDb();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
		List<tAbsenUserData> ListData=new ArrayList<tAbsenUserData>();
		ListData=_tAbsenUserDA.getAllDataToPushData(db);
		return ListData;
	}

	public List<tAbsenUserData>  getAllDataActive(){
		SQLiteDatabase db=getDb();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
		List<tAbsenUserData> ListData=new ArrayList<tAbsenUserData>();
		ListData=_tAbsenUserDA.getAllDataActive(db);
		return ListData;
	}

	public int  getContactsCount(){
		SQLiteDatabase db=getDb();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
		return _tAbsenUserDA.getContactsCount(db);
	}
	public void  getContactsCount(String IdData){
		SQLiteDatabase db=getDb();
		tAbsenUserDA _tAbsenUserDA=new tAbsenUserDA(db);
		_tAbsenUserDA.SaveDataSubmit(db,IdData);
	}
}
