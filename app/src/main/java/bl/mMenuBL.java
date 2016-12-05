package bl;

import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import library.common.mEmployeeAreaData;
import library.common.mMenuData;
import library.common.tLeaveMobileData;
import library.dal.mEmployeeAreaDA;
import library.dal.mEmployeeBranchDA;
import library.dal.mEmployeeSalesProductDA;
import library.dal.mMenuDA;
import library.dal.mProductBarcodeDA;
import library.dal.mTypeLeaveMobileDA;
import library.dal.tAbsenUserDA;
import library.dal.tLeaveMobileDA;
import library.dal.tSalesProductHeaderDA;

public class mMenuBL extends clsMainBL {
	public void SaveData(List<mMenuData> Listdata) {
		SQLiteDatabase db = getDb();
		mMenuDA _mMenuDA = new mMenuDA(db);
		_mMenuDA.DeleteAllDAta(db);
		Long index = Long.valueOf(_mMenuDA.getContactsCount(db) + 1);
		for (mMenuData data : Listdata) {
			data.set_intId(index);
			_mMenuDA.SaveDataMConfig(db, data);
			index += 1;
		}
		db.close();
	}

	public mMenuData getMenuDataByMenuName(String menuName) {
		SQLiteDatabase db = getDb();
		mMenuDA _mMenuDA = new mMenuDA(db);
		mMenuData dt = _mMenuDA.getDataByNamaMenu(db, menuName);
		db.close();
		return dt;
	}

	public mMenuData getMenuDataByMenuName2(String menuName) {
		SQLiteDatabase db = getDb();
		mMenuDA _mMenuDA = new mMenuDA(db);
		mMenuData dt = _mMenuDA.getDataByNamaMenu2(db, menuName);
		db.close();
		return dt;
	}

	public List<mMenuData> getDatabyParentId(int id) {
		SQLiteDatabase db = getDb();
		mMenuDA _mMenuDA = new mMenuDA(db);
		tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(db);
		List<mMenuData> listData = _mMenuDA.getDatabyParentId(db, id);
		List<mMenuData> tmpData = new ArrayList<mMenuData>();
		List<tLeaveMobileData> listDataLeave = new tLeaveMobileBL().getData("");
		for (mMenuData data : listData) {
			if (listDataLeave.size() == 0 && data.get_TxtDescription().contains("mnReporting")) {
				if (_tSalesProductHeaderDA.getContactsCount(db) > 0) {
					tmpData.add(data);
				}
			} else if (data.get_TxtDescription().contains("mnAbsenSPG") || data.get_TxtDescription().contains("mnPushDataSPG") || data.get_TxtDescription().contains("mnLeaveSPG")) {
				mEmployeeAreaDA _mEmployeeAreaDA = new mEmployeeAreaDA(db);
				mEmployeeBranchDA _mEmployeeBranchDA = new mEmployeeBranchDA(db);
				mProductBarcodeDA _mProductBarcodeDA = new mProductBarcodeDA(db);
				tLeaveMobileDA _tLeaveMobileDA = new tLeaveMobileDA(db);
				mEmployeeSalesProductDA _mEmployeeSalesProductDA = new mEmployeeSalesProductDA(db);
				if (_mEmployeeAreaDA.getContactsCount(db) > 0 && _mEmployeeBranchDA.getContactsCount(db) > 0) {
					if (listDataLeave.size() == 0 && _mEmployeeAreaDA.getContactsCount(db) > 0 && _mEmployeeBranchDA.getContactsCount(db) > 0) {

						int validate = 1;
						if(data.get_TxtDescription().contains("mnAbsenSPG")){

						List<mEmployeeAreaData> datamEmployeeArea = new mEmployeeAreaBL().GetAllData();

							for(mEmployeeAreaData dt : datamEmployeeArea){
								if(dt.get_txtLatitude()==""||dt.get_txtLatitude()==null||dt.get_txtLatitude().equals("")
										&&dt.get_txtLongitude()==""||dt.get_txtLongitude()==null||dt.get_txtLongitude().equals("")){
									validate = 0;
								}
							}
						}

						if(validate==1){
							tmpData.add(data);
						}
					} else if (data.get_TxtDescription().contains("mnLeave")&&listDataLeave.size() > 0 && _mEmployeeAreaDA.getContactsCount(db) > 0 && _mEmployeeBranchDA.getContactsCount(db) > 0){
						tmpData.add(data);

					}

				} else if (data.get_TxtDescription().contains("mnLeave")) {
					mTypeLeaveMobileDA _mTypeLeaveMobileDA = new mTypeLeaveMobileDA(db);
					tAbsenUserDA _tAbsenUserDA = new tAbsenUserDA(db);
					if (_tAbsenUserDA.getContactsCountSubmit(db) == 0 && _mTypeLeaveMobileDA.getContactsCount(db) > 0) {
						tmpData.add(data);
					}
				}
			}
			else{
				tmpData.add(data);
			}
		}
		db.close();
		return tmpData;
	}
}