package bl;

import android.database.sqlite.SQLiteDatabase;

import com.kalbenutritionals.xteamnative.clsMainActivity;

import org.json.simple.JSONArray;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import library.common.APIData;
import library.common.clsHelper;
import library.common.linkAPI;
import library.common.mBranchData;
import library.common.mRegionData;
import library.common.mUserRoleData;
import library.common.mconfigData;
import library.common.tDeviceInfoUserData;
import library.common.tUserLoginData;
import library.dal.clsHardCode;
import library.dal.enumConfigData;
import library.dal.mBranchDA;
import library.dal.mRegionDA;
import library.dal.mconfigDA;
import library.dal.tDeviceInfoUserDA;
import library.dal.tUserLoginDA;

public class tUserLoginBL extends clsMainBL {
	public JSONArray Logout(String versionApp) throws ParseException {
		SQLiteDatabase db=getDb();
		JSONArray res=new JSONArray();
		mconfigDA _mconfigDA=new mconfigDA(db);
		tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		List<tUserLoginData> dttAbsenUserData= _tUserLoginDA.getUserLoginNow(db);
		
		String txtDomain= _mconfigDA.getDomainKalbeData(db);
		//String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;
		
		linkAPI dtlinkAPI=new linkAPI();
		dtlinkAPI.set_txtMethod("tUserLogin_mobileLogOut");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionApp);
		String strLinkAPI= dtlinkAPI.QueryString(getLinkAPI());
		APIData dtAPIDATA=new APIData();
		clsHelper _clsHelper=new clsHelper();
		String JsonData= _clsHelper.pushtData(strLinkAPI, "", Integer.valueOf(getBackGroundServiceOnline()));
		res= _clsHelper.ResultJsonArray(JsonData);
		//String txtParam=
        return res;
	}
	public JSONArray Login(String txtUserName, String txtPass, String intRoleId, String userID) throws ParseException {
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
		String txtMethod = "GetDataMWebUserWithActiveDirectory";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam(new clsHardCode().txtMethod_Kalbefood + "|" + txtUserName + "|" + txtPass);
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		String strLinkAPI = dtlinkAPI.QueryString(strVal2);

		String JsonData;
		JSONArray JsonArray = null;

		try {
			JsonData = _help.ResultJsonData(_help.getHTML(strLinkAPI));
			JsonArray = _help.ResultJsonArray(JsonData);

//			return JsonArray;
		} catch (Exception e) {
			e.printStackTrace();
		}


		_help = new clsHelper();
		dtlinkAPI = new linkAPI();
		txtMethod = "GetListCabangByUserAndRole";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam(userID + "|" + intRoleId);
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		strLinkAPI = dtlinkAPI.QueryString(strVal2);

		String JsonDataCabang;
		List<mUserRoleData> Listdata = new ArrayList<>();

		try {
			JsonDataCabang = _help.ResultJsonData(_help.getHTML(strLinkAPI));
			org.json.simple.JSONArray JsonArrayCabang = _help.ResultJsonArray(JsonDataCabang);

			Iterator i = JsonArrayCabang.iterator();

			SQLiteDatabase db = getDb();
			mBranchDA _mBranchDA = new mBranchDA(db);
			_mBranchDA.DeleteAllDataMConfig(db);
			clsMainActivity _clsMainActivity = new clsMainActivity();

			while (i.hasNext()) {

//				UUID uuid = UUID.randomUUID();
//				String randomUUIDString = uuid.toString();

				org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();

				mBranchData _data = new mBranchData();

				int index = _mBranchDA.getContactsCount(db) + 1;
				_data.set_uuId(String.valueOf(index));
				_data.set_txtBranchID(String.valueOf(innerObj.get("TxtCabangID")));
				_data.set_txtBranchName(String.valueOf(innerObj.get("TxtName")));
				_mBranchDA.SaveDataMConfig(db, _data);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return JsonArray;
	}

	public void getMasterBranch(String userID, String intRoleId) throws ParseException {
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
		String txtMethod = "GetListCabangByUserAndRoleKNDashboard";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam(intRoleId + "|" + userID);
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		String strLinkAPI = dtlinkAPI.QueryString(strVal2);

		String JsonData;
		List<mBranchData> Listdata = new ArrayList<>();

		try {
			JsonData = _help.ResultJsonData(_help.getHTML(strLinkAPI));
			org.json.simple.JSONArray JsonArray = _help.ResultJsonArray(JsonData);

			Iterator i = JsonArray.iterator();

			SQLiteDatabase db = getDb();
			mBranchDA _mBranchDA = new mBranchDA(db);
			_mBranchDA.DeleteAllDataMConfig(db);

			while (i.hasNext()) {
				org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();

				mBranchData _data = new mBranchData();
				int index = _mBranchDA.getContactsCount(db) + 1;
				_data.set_uuId(String.valueOf(index));
				_data.set_txtBranchID(String.valueOf(innerObj.get("TxtCabangID")));
				_data.set_txtBranchName(String.valueOf(innerObj.get("TxtName")));
				_data.set_txtRegion(String.valueOf(innerObj.get("TxtRegion")));
				_mBranchDA.SaveDataMConfig(db, _data);
//				Listdata.add(_data);
			}
//			return Listdata;
		} catch (Exception e) {
			e.printStackTrace();
		}
//		return Listdata;
	}

	public JSONArray getAllRegion() throws org.json.simple.parser.ParseException {
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
		String txtMethod = "GetListOfDataRegion";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtParam("");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		String strLinkAPI = dtlinkAPI.QueryString(strVal2);

		String JsonData;
		JSONArray JsonArray = null;

		try {
			JsonData = _help.ResultJsonData(_help.getHTML(strLinkAPI));
			JsonArray = _help.ResultJsonArray(JsonData);

			Iterator i = JsonArray.iterator();

			SQLiteDatabase db = getDb();
			mRegionDA _mRegionDA = new mRegionDA(db);
			_mRegionDA.DeleteAllDataMConfig(db);

			while (i.hasNext()) {
				org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();

				mRegionData _data = new mRegionData();
				int index = _mRegionDA.getContactsCount(db) + 1;
				_data.set_uuId(String.valueOf(index));
				_data.set_txtRegionID(String.valueOf(innerObj.get("IntID")));
				_data.set_txtRegionName(String.valueOf(innerObj.get("TxtName")));
				_mRegionDA.SaveDataMConfig(db, _data);

//				return JsonArray;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JsonArray;
	}

	public void saveData(tUserLoginData data){
		SQLiteDatabase db=getDb();
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		_tUserLoginDA.SaveDatatUserLoginData(db, data);
	}
	public void updateDataValueById(tUserLoginData dt, int id){
		SQLiteDatabase _db=getDb();
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(_db);
		_tUserLoginDA.updateDataById(_db, dt, id);
	}
	public JSONArray LogoutFromPushData(String versionApp) throws ParseException{
		SQLiteDatabase db=getDb();
		JSONArray res=new JSONArray();
		mconfigDA _mconfigDA=new mconfigDA(db);
		tDeviceInfoUserData dt= new tDeviceInfoUserDA(db).getData(db, 1);
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		List<tUserLoginData> dttAbsenUserData= _tUserLoginDA.getAllData(db);

		String txtDomain= _mconfigDA.getDomainKalbeData(db);
		//String txtParam= txtDomain+"|"+txtUserName+"|"+txtPass+"||"+dt.get_txtVersion()+"|"+dt.get_txtDevice()+"|"+dt.get_txtModel()+"|"+intRoleId;

		linkAPI dtlinkAPI=new linkAPI();
		dtlinkAPI.set_txtMethod("tUserLogin_mobileLogOut");
		dtlinkAPI.set_txtParam(String.valueOf(dttAbsenUserData.get(0).get_intId()));
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionApp);
		String strLinkAPI= dtlinkAPI.QueryString(getLinkAPI());
		APIData dtAPIDATA=new APIData();
		clsHelper _clsHelper=new clsHelper();
		String JsonData= _clsHelper.pushtData(strLinkAPI, "", Integer.valueOf(getBackGroundServiceOnline()));
		res= _clsHelper.ResultJsonArray(JsonData);
		//String txtParam=
		return res;
	}
}
