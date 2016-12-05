package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.common.mTypeSumberData;
import library.dal.mTypeSumberDA;

/**
 * Created by ASUS ZE on 10/11/2016.
 */

public class mTypeSumberBL extends clsMainBL{

    public List<mTypeSumberData> GetAllData(){
        SQLiteDatabase db=getDb();
        mTypeSumberDA _mTypeSumberDA=new mTypeSumberDA(db);
        List<mTypeSumberData> lisdata=_mTypeSumberDA.getAllData(db);
        db.close();
        return lisdata;
    }
}
