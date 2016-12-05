package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import library.common.clsHelper;
import library.common.linkAPI;
import library.common.mUserRoleData;
import library.common.mconfigData;
import library.dal.clsHardCode;
import library.dal.enumConfigData;
import library.dal.mUserRoleDA;
import library.dal.mconfigDA;

public class mUserRoleBL extends clsMainBL {

    public mUserRoleData getIntUserID() {
        this.db = getDb();
        List<mUserRoleData> listData;
        mUserRoleDA _mUserRoleDA=new mUserRoleDA(db);
        listData=_mUserRoleDA.getAllData(db);
//		if (listData.size()==0){
//			listData=_tUserLoginDA.getAllData(db);
//		}
        db.close();
        return listData.get(0);
    }

    public List<mUserRoleData> GetAllData(){
        SQLiteDatabase db=getDb();
        List<mUserRoleData> listData;
        mUserRoleDA _mUserRoleDA=new mUserRoleDA(db);
        listData=_mUserRoleDA.getAllData(db);
        db.close();
        return listData;
    }

    public List<mUserRoleData> getRole(String username) throws ParseException {
        SQLiteDatabase _db = getDb();
        mconfigDA _mconfigDA = new mconfigDA(_db);

        String strVal2;
        mconfigData dataAPI = _mconfigDA.getData(db, enumConfigData.ApiKalbe.getidConfigData());
        strVal2 = dataAPI.get_txtValue();
        if (dataAPI.get_txtValue().equals("")) {
            strVal2 = dataAPI.get_txtDefaultValue();
        }
        clsHelper _help = new clsHelper();
        linkAPI dtlinkAPI = new linkAPI();
        String txtMethod = "GetAllMWebUserRoleByUserName";
        dtlinkAPI.set_txtMethod(txtMethod);
        dtlinkAPI.set_txtParam(username);
        dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
        String strLinkAPI = dtlinkAPI.QueryString(strVal2);

        String JsonData;
        List<mUserRoleData> Listdata = new ArrayList<>();

        try {
            JsonData = _help.ResultJsonData(_help.getHTML(strLinkAPI));
            org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);

            Iterator i = JsonArray.iterator();

            SQLiteDatabase db = getDb();
            mUserRoleDA _mUserRoleDA = new mUserRoleDA(db);
            _mUserRoleDA.DeleteAllDataMConfig(db);

            while (i.hasNext()) {
                org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
                Long IntResult = (Long) innerObj.get("_pboolValid");

                if(IntResult==1){

                    mUserRoleData _data = new mUserRoleData();
                    int index = _mUserRoleDA.getContactsCount(db) + 1;
                    _data.set_intId(String.valueOf(index));
                    _data.set_intRoleId(String.valueOf(innerObj.get("IntRoleID")));
                    _data.set_txtUserId(String.valueOf(innerObj.get("IntUserID")));
                    _data.set_txtRoleName(String.valueOf(innerObj.get("TxtRoleName")));
                    _mUserRoleDA.SaveDataMConfig(db, _data);
                    Listdata.add(_data);
                } else {
                    mUserRoleData _data = new mUserRoleData();
                    _data.set_intId(String.valueOf(innerObj.get("_pboolValid")));
                    _data.set_intRoleId(String.valueOf(innerObj.get("IntRoleID")));
                    _data.set_txtUserId(String.valueOf(innerObj.get("IntUserID")));
                    _data.set_txtRoleName(String.valueOf(innerObj.get("_pstrMessage")));
                    Listdata.add(_data);
                }

            }
            return Listdata;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Listdata;
    }


//
//	public List<mUserRoleData> getRoleAndOutlet(String username,String versionApp) throws ParseException{
//		List<mUserRoleData> Listdata=new ArrayList<mUserRoleData>();
//		linkAPI dtlinkAPI=new linkAPI();
//		String txtMethod="GetAllUserRoleByUserNameSalesInsentivePostData";
//		JSONObject resJson = new JSONObject();
//		resJson.put("username", username);
//		dtlinkAPI.set_txtMethod(txtMethod);
//		//dtlinkAPI.set_txtParam(username);
//		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
//		dtlinkAPI.set_txtVesion(versionApp);
//		String strLinkAPI= dtlinkAPI.QueryString(getLinkAPI());
//		APIData dtAPIDATA=new APIData();
//		clsHelper _clsHelper=new clsHelper();
//		String JsonData= _clsHelper.pushtData(strLinkAPI,String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
//		org.json.simple.JSONArray JsonArray= _clsHelper.ResultJsonArray(JsonData);
//		Iterator i = JsonArray.iterator();
//		SQLiteDatabase db=getDb();
//		mUserRoleDA _mUserRoleDA=new mUserRoleDA(db);
//		_mUserRoleDA.DeleteAllDataMConfig(db);
//
//		mEmployeeAreaDA _mEmployeeAreaDA = new mEmployeeAreaDA(db);
//		_mEmployeeAreaDA.DeleteAllDataMConfig(db);
//
//		while (i.hasNext()) {
//			org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
//
//			int boolValid= Integer.valueOf(String.valueOf( innerObj.get(dtAPIDATA.boolValid)));
//			if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
//
//				org.json.simple.JSONArray JsonArray_role= _clsHelper.ResultJsonArray(String.valueOf(innerObj.get("ListMWebUserRoleAPI")));
//				Iterator j = JsonArray_role.iterator();
//
//				while(j.hasNext()){
//					org.json.simple.JSONObject innerObj_detail = (org.json.simple.JSONObject) j.next();
//					mUserRoleData _data =new mUserRoleData();
//					int index=_mUserRoleDA.getContactsCount(db)+1;
//					_data.set_intId(String.valueOf(index));
//					_data.set_intRoleId(String.valueOf(innerObj_detail.get("IntRoleID")));
//					_data.set_txtUserId(String.valueOf(innerObj_detail.get("IntUserID")));
//					_data.set_txtRoleName(String.valueOf(innerObj_detail.get("TxtRoleName")));
//					_mUserRoleDA.SaveDataMConfig(db, _data);
//					Listdata.add(_data);
//				}
//
//				org.json.simple.JSONArray JsonArray_outlet = _clsHelper.ResultJsonArray(String.valueOf(innerObj.get("Listvw_SalesInsentive_EmployeeAreaData")));
//				Iterator k = JsonArray_outlet.iterator();
//
//				while(k.hasNext()){
//					org.json.simple.JSONObject innerObj_detail = (org.json.simple.JSONObject) k.next();
//					mEmployeeAreaData _data =new mEmployeeAreaData();
//					int index=_mEmployeeAreaDA.getContactsCount(db)+1;
//					_data.set_intID(String.valueOf(index));
//					_data.set_intBranchId(String.valueOf(innerObj_detail.get("IntBranchId")));
//					_data.set_intChannelId(String.valueOf(innerObj_detail.get("IntChannelId")));
//					_data.set_intEmployeeId(String.valueOf(innerObj_detail.get("IntEmployeeId")));
//					_data.set_intOutletId(String.valueOf(innerObj_detail.get("IntOutletId")));
//					_data.set_intRayonId(String.valueOf(innerObj_detail.get("IntRayonId")));
//					_data.set_intRegionId(String.valueOf(innerObj_detail.get("IntRegionId")));
//					_data.set_txtBranchCode(String.valueOf(innerObj_detail.get("TxtBranchCode")));
//					_data.set_txtBranchName(String.valueOf(innerObj_detail.get("TxtBranchName")));
//					_data.set_txtName(String.valueOf(innerObj_detail.get("TxtName")));
//					_data.set_txtNIK(String.valueOf(innerObj_detail.get("TxtNIK")));
//					_data.set_txtOutletCode(String.valueOf(innerObj_detail.get("TxtOutletCode")));
//					_data.set_txtOutletName(String.valueOf(innerObj_detail.get("TxtOutletName")));
//					_data.set_txtRayonCode(String.valueOf(innerObj_detail.get("TxtRayonCode")));
//					_data.set_txtRayonName(String.valueOf(innerObj_detail.get("TxtRayonName")));
//					_data.set_txtRegionName(String.valueOf(innerObj_detail.get("TxtRegionName")));
//
//					//_mEmployeeAreaDA.SaveDataMConfig(db, _data);
//				}
//			}
//		}
//		return Listdata;
//	}
}
