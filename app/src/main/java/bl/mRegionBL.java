package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.common.mRegionData;
import library.dal.mRegionDA;

/**
 * Created by ASUS ZE on 08/11/2016.
 */

public class mRegionBL extends clsMainBL{

    public List<mRegionData> GetAllData(){
        SQLiteDatabase db=getDb();
        mRegionDA _mRegionDA=new mRegionDA(db);
        List<mRegionData> lisdata=_mRegionDA.getAllData(db);
        db.close();
        return lisdata;
    }
}
