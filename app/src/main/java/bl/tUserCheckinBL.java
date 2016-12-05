package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.common.tUserCheckinData;
import library.dal.tUserCheckinDA;

/**
 * Created by ASUS ZE on 20/09/2016.
 */
public class tUserCheckinBL extends clsMainBL{

    public void saveData(tUserCheckinData data){
        SQLiteDatabase db=getDb();
        tUserCheckinDA _tUserCheckinDA = new tUserCheckinDA(db);
            _tUserCheckinDA.SaveDatatUserCheckinData(db, data);
    }

    public List<tUserCheckinData> GetAllData(){
        SQLiteDatabase db=getDb();
        tUserCheckinDA _tUserCheckinDA = new tUserCheckinDA(db);
        List<tUserCheckinData> lisdata=_tUserCheckinDA.getAllData(db);
        db.close();
        return lisdata;
    }
}
