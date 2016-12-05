package bl;

import android.database.sqlite.SQLiteDatabase;

import library.common.mCounterNumberData;
import library.dal.enumCounterData;
import library.dal.mCounterNumberDA;

public class mCounterNumberBL extends clsMainBL {
	public void saveDateTimeServer(String datetimeNow){
		SQLiteDatabase db=getDb();
		mCounterNumberDA _mCounterNumberDA=new mCounterNumberDA(db);
		mCounterNumberData dtmCounterNumberData=new mCounterNumberData();
		dtmCounterNumberData.set_intId(enumCounterData.DateTimeServer.getidCounterData());
		dtmCounterNumberData.set_txtDeskripsi("Time Server Now");
		 dtmCounterNumberData.set_txtName("TimeServer");
         dtmCounterNumberData.set_txtValue(datetimeNow);
         _mCounterNumberDA.SaveDataMConfig(db, dtmCounterNumberData);
         db.close();
	}
	public void SaveData(mCounterNumberData dtmCounterNumberData){
		SQLiteDatabase db=getDb();
		mCounterNumberDA _mCounterNumberDA=new mCounterNumberDA(db);
		_mCounterNumberDA.SaveDataMConfig(db, dtmCounterNumberData);
		db.close();
	}
	public String getData(enumCounterData dt){
		SQLiteDatabase db=getDb();
		mCounterNumberDA _mCounterNumberDA=new mCounterNumberDA(db);
		mCounterNumberData dtmCounterNumberData=_mCounterNumberDA.getData(db, dt.getidCounterData());
		String Value=dtmCounterNumberData.get_txtValue();
		db.close();
		return Value;
	}
	public mCounterNumberData getDataByenumCounterData(enumCounterData dt){
		SQLiteDatabase db=getDb();
		mCounterNumberDA _mCounterNumberDA=new mCounterNumberDA(db);
		mCounterNumberData dtmCounterNumberData=_mCounterNumberDA.getData(db, dt.getidCounterData());
		db.close();
		return dtmCounterNumberData;
	}

}
