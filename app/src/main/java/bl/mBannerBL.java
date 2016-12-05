package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.common.mBannerData;
import library.dal.mBannerDA;

/**
 * Created by ASUS ZE on 08/11/2016.
 */

public class mBannerBL extends clsMainBL {

    public List<mBannerData> GetAllData(){
        SQLiteDatabase db=getDb();
        mBannerDA _mBannerDA=new mBannerDA(db);
        List<mBannerData> lisdata=_mBannerDA.getAllData(db);
        db.close();
        return lisdata;
    }
}
