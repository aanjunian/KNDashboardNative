package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.List;

import library.common.mBranchData;
import library.dal.mBranchDA;

/**
 * Created by ASUS ZE on 20/09/2016.
 */
public class mBranchBL extends clsMainBL {

    public void saveData(List<mBranchData> Listdata){
        SQLiteDatabase db=getDb();
        mBranchDA _mBranchDA=new mBranchDA(db);
        _mBranchDA.DeleteAllDataMConfig(db);
        for(mBranchData data:Listdata){
            _mBranchDA.SaveDataMConfig(db, data);
        }
        db.close();
    }

    public List<mBranchData> GetAllData(){
        SQLiteDatabase db=getDb();
        mBranchDA _mBranchDA=new mBranchDA(db);
        List<mBranchData> lisdata=_mBranchDA.getAllData(db);
        db.close();
        return lisdata;
    }

    public List<mBranchData> GetRegionByBranchId(String branchId){
        SQLiteDatabase _db=getDb();
        mBranchDA _mBranchDA=new mBranchDA(_db);
        List<mBranchData> lisdata = _mBranchDA.GetRegionByBranch(_db, branchId);
        return lisdata;
    }

    public List<mBranchData> GetArrayBranchByRegion(String region){
        SQLiteDatabase _db=getDb();
        mBranchDA _mBranchDA=new mBranchDA(_db);
        List<mBranchData> lisdata = _mBranchDA.GetBranchByRegion(_db, region);
        return lisdata;
    }
}
