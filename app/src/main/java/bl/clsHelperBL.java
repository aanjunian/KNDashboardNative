package bl;

import android.database.sqlite.SQLiteDatabase;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import library.common.APIData;
import library.common.clsHelper;
import library.common.clsPushData;
import library.common.dataJson;
import library.common.linkAPI;
import library.common.mCounterNumberData;
import library.common.mconfigData;
import library.common.tAbsenUserData;
import library.common.tActivityData;
import library.common.tCustomerBasedMobileHeaderData;
import library.common.tLeaveMobileData;
import library.common.tSalesProductHeaderData;
import library.common.tUserCheckinData;
import library.common.tUserLoginData;
import library.dal.clsHardCode;
import library.dal.enumConfigData;
import library.dal.enumCounterData;
import library.dal.mCounterNumberDA;
import library.dal.mconfigDA;
import library.dal.tAbsenUserDA;
import library.dal.tActivityDA;
import library.dal.tCustomerBasedMobileHeaderDA;
import library.dal.tLeaveMobileDA;
import library.dal.tSalesProductDetailDA;
import library.dal.tSalesProductHeaderDA;
import library.dal.tUserCheckinDA;
import library.dal.tUserLoginDA;

//import org.xml.sax.DTDHandler;
//import com.kalbe.salesforce.TableNotif;
//import com.kalbe.service.MyNotificationService;
//import android.content.Intent;
//import come.example.viewbadger.ShortcutBadger;

public class clsHelperBL extends clsMainBL {
	public void DeleteAllDB(){
		SQLiteDatabase db=getDb();
		new clsHelper().DeleteAllDB(db);
		db.close();
	}
	public void DownloadData(String versionName) throws ParseException {
		SQLiteDatabase _db=getDb();
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(_db);
		tUserLoginData _dataUserLogin = _tUserLoginDA.getData(db, 1);
		JSONObject resJson = new JSONObject();
		resJson.put("User", _dataUserLogin.get_txtUserId());
		resJson.put("txtBranchCode", "");
		resJson.put("txtOutletCode", "");
		mconfigDA _mconfigDA =new mconfigDA(_db);
		String strVal2="";
		mconfigData dataAPI = _mconfigDA.getData(db,enumConfigData.ApiKalbe.getidConfigData());
		strVal2 = dataAPI.get_txtValue();
		if (dataAPI.get_txtValue() == "") {
			strVal2 = dataAPI.get_txtDefaultValue();
		}
		clsHelper _help =new clsHelper();
		linkAPI dtlinkAPI=new linkAPI();
		String txtMethod="DownloadAllDataTransaction";
		dtlinkAPI.set_txtMethod(txtMethod);
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionName);
		String strLinkAPI= dtlinkAPI.QueryString(strVal2);
		//String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
		String JsonData= _help.pushtData(strLinkAPI,String.valueOf(resJson), Integer.valueOf(getBackGroundServiceOnline()));
		org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(JsonData);

		APIData dtAPIDATA=new APIData();
		//String aa=new clsHelper().linkAPI(db);
		Iterator i = JsonArray.iterator();
		Boolean flag=true;
		String ErrorMess="";
		clsHelper _clsHelper=new clsHelper();
	}
	public org.json.simple.JSONArray callPushDataReturnJson(String versionName,String strJson,HashMap<String, String> ListOfDataFile) throws Exception{
		SQLiteDatabase _db=getDb();
		Boolean flag=true;
		String ErrorMess="";
		String txtMethod="PushDataKNDashboard";
		linkAPI dtlinkAPI=new linkAPI();
		clsHelper _help =new clsHelper();
		dtlinkAPI=new linkAPI();
		dtlinkAPI.set_txtMethod(txtMethod);
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(_db);
		tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
		dtlinkAPI.set_txtParam(_dataUserLogin.get_txtUserId()+"|||");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionName);
		String strVal2="";
		mconfigDA _mconfigDA =new mconfigDA(_db);
		mconfigData dataAPI = _mconfigDA.getData(db,enumConfigData.ApiKalbe.getidConfigData());
		strVal2 = dataAPI.get_txtValue();
		if (dataAPI.get_txtValue() == "") {
			strVal2 = dataAPI.get_txtDefaultValue();
		}
		dataAPI = _mconfigDA.getData(_db,enumConfigData.BackGroundServiceOnline.getidConfigData());
		String TimeOut = dataAPI.get_txtValue();
		String strLinkAPI= dtlinkAPI.QueryString(strVal2);
		String JsonData=_help.PushDataWithFile(strLinkAPI, strJson, Integer.valueOf(TimeOut), ListOfDataFile);
		//String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
		org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(JsonData);
		APIData dtAPIDATA=new APIData();
		Iterator i = JsonArray.iterator();
		mCounterNumberDA _mCounterNumberDA=new mCounterNumberDA(_db);
		while (i.hasNext()) {
			org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
			int boolValid= Integer.valueOf(String.valueOf( innerObj.get(dtAPIDATA.boolValid)));
			if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
				mCounterNumberData _data =new mCounterNumberData();
				_data.set_intId(enumCounterData.dtPushKBN.getidCounterData());
				_data.set_txtDeskripsi((String) innerObj.get("_pstrMethodRequest"));
				_data.set_txtName((String) innerObj.get("_pstrMethodRequest"));
				_data.set_txtValue((String) innerObj.get("_pstrArgument"));
				_mCounterNumberDA.SaveDataMConfig(_db, _data);
			}else{
				flag=false;
				ErrorMess=(String) innerObj.get(dtAPIDATA.strMessage);
				break;
			}
		}
		_db.close();
		return JsonArray;
	}

	public org.json.simple.JSONArray GetDatamversionAppPostData(String versionName) throws Exception{
		SQLiteDatabase _db=getDb();
		String txtMethod="GetDatamversionAppPostData";
		linkAPI dtlinkAPI=new linkAPI();
		clsHelper _help =new clsHelper();
		dtlinkAPI=new linkAPI();
		dtlinkAPI.set_txtMethod(txtMethod);
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(_db);
		dtlinkAPI.set_txtParam("|||");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionName);
		String strVal2="";
		mconfigDA _mconfigDA =new mconfigDA(_db);
		mconfigData dataAPI = _mconfigDA.getData(db,enumConfigData.ApiKalbe.getidConfigData());
		strVal2 = dataAPI.get_txtValue();
		if (dataAPI.get_txtValue() == "") {
			strVal2 = dataAPI.get_txtDefaultValue();
		}

		String TimeOut = new clsMainBL().getBackGroundServiceOnline();
		String strLinkAPI= dtlinkAPI.QueryString(strVal2);
		JSONObject resJson = new JSONObject();
		resJson.put("intVersionApp", "");
		resJson.put("txtTypeApp", new clsMainBL().getTypeMobile());
		resJson.put("txtVersion", "");
		resJson.put("status", "1");
		String JsonData=_help.pushtData(strLinkAPI, String.valueOf(resJson), Integer.valueOf(TimeOut));
		//String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
		org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(JsonData);
		APIData dtAPIDATA=new APIData();
		Iterator i = JsonArray.iterator();
		_db.close();
		return JsonArray;
	}

	public void callPushData(String versionName,String strJson,HashMap<String, String> ListOfDataFile) throws Exception{
		SQLiteDatabase _db=getDb();
		Boolean flag=true;
		String ErrorMess="";
		String txtMethod="PushDataKBN";
		linkAPI dtlinkAPI=new linkAPI();
		clsHelper _help =new clsHelper();
		dtlinkAPI=new linkAPI();
		dtlinkAPI.set_txtMethod(txtMethod);
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(_db);
		tUserLoginData _dataUserLogin = _tUserLoginDA.getData(_db, 1);
		dtlinkAPI.set_txtParam(_dataUserLogin.get_txtUserId()+"|||");
		dtlinkAPI.set_txtToken(new clsHardCode().txtTokenAPI);
		dtlinkAPI.set_txtVesion(versionName);
		String strVal2="";
		mconfigDA _mconfigDA =new mconfigDA(_db);
		mconfigData dataAPI = _mconfigDA.getData(db,enumConfigData.ApiKalbe.getidConfigData());
		strVal2 = dataAPI.get_txtValue();
		if (dataAPI.get_txtValue() == "") {
			strVal2 = dataAPI.get_txtDefaultValue();
		}
		dataAPI = _mconfigDA.getData(_db,enumConfigData.BackGroundServiceOnline.getidConfigData());
		String TimeOut = dataAPI.get_txtValue();
		String strLinkAPI= dtlinkAPI.QueryString(strVal2);
		String JsonData=_help.PushDataWithFile(strLinkAPI, strJson, Integer.valueOf(TimeOut), ListOfDataFile);
		//String JsonData= _help.ResultJsonData(_help.getHTML(strLinkAPI));
		org.json.simple.JSONArray JsonArray= _help.ResultJsonArray(JsonData);
		APIData dtAPIDATA=new APIData();
		Iterator i = JsonArray.iterator();
		mCounterNumberDA _mCounterNumberDA=new mCounterNumberDA(_db);
		while (i.hasNext()) {
			org.json.simple.JSONObject innerObj = (org.json.simple.JSONObject) i.next();
			int boolValid= Integer.valueOf(String.valueOf( innerObj.get(dtAPIDATA.boolValid)));
			if(boolValid == Integer.valueOf(new clsHardCode().intSuccess)){
				mCounterNumberData _data =new mCounterNumberData();
				_data.set_intId(enumCounterData.dtPushKBN.getidCounterData());
				_data.set_txtDeskripsi((String) innerObj.get("_pstrMethodRequest"));
				_data.set_txtName((String) innerObj.get("_pstrMethodRequest"));
				_data.set_txtValue((String) innerObj.get("_pstrArgument"));
				_mCounterNumberDA.SaveDataMConfig(_db, _data);
			}else{
				flag=false;
				ErrorMess=(String) innerObj.get(dtAPIDATA.strMessage);
				break;
			}
		}
		_db.close();
	}
	public clsPushData pushData(){
		clsPushData dtclsPushData = new clsPushData();
		dataJson dtPush=new dataJson();
		SQLiteDatabase db=getDb();
		tUserLoginDA _tUserLoginDA=new tUserLoginDA(db);
		HashMap<String, String> FileUpload=null;
		if(_tUserLoginDA.getContactsCount(db)> 0){
			tUserLoginData _tUserLoginData=_tUserLoginDA.getData(db, 1);
			dtPush.set_txtUserId(_tUserLoginData.get_txtUserId());
			try {
				DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    		Calendar cal = Calendar.getInstance();
				mCounterNumberDA _mCounterNumberDA=new mCounterNumberDA(db);
				mCounterNumberData _data =new mCounterNumberData();
				_data.set_intId(enumCounterData.MonitorSchedule.getidCounterData());
				_data.set_txtDeskripsi("value menunjukan waktu terakhir menjalankan services");
				_data.set_txtName("Monitor Service");
				_data.set_txtValue(dateFormat.format(cal.getTime()));
				_mCounterNumberDA.SaveDataMConfig(db, _data);
				
				//new clsInit().PushData(db,versionName);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			tAbsenUserDA _tAbsenUserDA =new tAbsenUserDA (db);
			tActivityDA _tActivityDA =new tActivityDA (db);
			tLeaveMobileDA _tLeaveMobileDA =new tLeaveMobileDA(db);
			tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(db);
			tSalesProductDetailDA _tSalesProductDetailDA = new tSalesProductDetailDA(db);
			tCustomerBasedMobileHeaderDA _tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(db);
			tUserCheckinDA _tUserCheckinDA = new tUserCheckinDA(db);

			List<tCustomerBasedMobileHeaderData> ListOftCustomerBasedMobileHeader = _tCustomerBasedMobileHeaderDA.getPushData(db);
			List<tSalesProductHeaderData> ListOfSalesProductHeader = _tSalesProductHeaderDA.getAllDataToPushData(db);
			List<tLeaveMobileData> ListOftLeaveData=_tLeaveMobileDA.getAllDataPushData(db);
			List<tAbsenUserData> ListOftAbsenUserData=_tAbsenUserDA.getAllDataToPushData(db);
			List<tActivityData> ListOftActivityData=_tActivityDA.getAllDataToPushData(db);
			List<tUserCheckinData> ListOftUserCheckinData = _tUserCheckinDA.getAllDataToPushData(db);

			FileUpload=new HashMap<String, String>();
			if(ListOftAbsenUserData!= null){
				dtPush.setListOftAbsenUserData(ListOftAbsenUserData);
//				for (tAbsenUserData dttAbsenUserData : ListOftAbsenUserData) {
//					if(dttAbsenUserData.get_txtImg1().equals("null")==false){
//						FileUpload.put("FUAbsen"+dttAbsenUserData.get_intId()+"-1", String.valueOf(dttAbsenUserData.get_txtImg1()));
//					}
//					if(dttAbsenUserData.get_txtImg2().equals("null")==false){
//						FileUpload.put("FUAbsen"+dttAbsenUserData.get_intId()+"-2", String.valueOf(dttAbsenUserData.get_txtImg2()));
//					}
//				}
			}
			if(ListOftActivityData!=null){
				dtPush.setListOftActivityData(ListOftActivityData);
//				for (tActivityData dttActivityData : ListOftActivityData) {
//					FileUpload.put("idUploadActivity", "FUAbsen"+dttActivityData.get_intId());
//					if(dttActivityData.get_txtImg1()!=null){
//						FileUpload.put("FUActivity"+dttActivityData.get_intId()+"-1", "file://"+dttActivityData.get_txtImg1());
//					}
//					if(dttActivityData.get_txtImg2()!=null){
//						FileUpload.put("FUActivity"+dttActivityData.get_intId()+"-2", "file://"+dttActivityData.get_txtImg2());
//					}
//				}
			}

			if(ListOftLeaveData!=null){
				dtPush.setListOftLeaveMobileData(ListOftLeaveData);
			}

			if(ListOfSalesProductHeader!=null){
				dtPush.setListOftSalesProductHeaderData(ListOfSalesProductHeader);
			}

			if(ListOftCustomerBasedMobileHeader!=null){
				dtPush.set_ListOftCustomerBasedMobileHeaderData(ListOftCustomerBasedMobileHeader);
			}

			if(ListOftUserCheckinData!=null){
				dtPush.setListOftUserCheckinData(ListOftUserCheckinData);
			}
		}
		else{
			dtPush=null;
		}
		db.close();
		dtclsPushData.setDtdataJson(dtPush);
		dtclsPushData.setFileUpload(FileUpload);
		return dtclsPushData;
	}
	public void saveDataPush(dataJson dtJson, org.json.simple.JSONArray JsonResult) {
		SQLiteDatabase db = getDb();

		if (dtJson.getListOftAbsenUserData() != null) {
			Iterator j = null;
			j = JsonResult.iterator();
			while (j.hasNext()) {
				org.json.simple.JSONObject innerObj_Header = (org.json.simple.JSONObject) j.next();
				org.json.simple.JSONArray JsonArray_Detail = (JSONArray) innerObj_Header.get("ListOftAbsenUser_mobile");
				Iterator jDetail = JsonArray_Detail.iterator();
				while (jDetail.hasNext()) {
					org.json.simple.JSONObject innerObj_Detail = (org.json.simple.JSONObject) jDetail.next();
					for (tAbsenUserData dt : dtJson.getListOftAbsenUserData()) {
						tAbsenUserDA _tAbsenUserDA = new tAbsenUserDA(db);
						if (String.valueOf(innerObj_Detail.get("TxtDataIdFromSource")).equals(dt.get_intId())) {
							dt.set_intSync("1");
							dt.set_txtAbsen(String.valueOf(innerObj_Detail.get("TxtDataId")));
							_tAbsenUserDA.SaveDatatAbsenUserData(db, dt);
						}
					}
				}
			}
		}

		if (dtJson.getListOftActivityData() != null) {
			for (tActivityData dt : dtJson.getListOftActivityData()) {
				tActivityDA _tActivityDA = new tActivityDA(db);
				dt.set_intIdSyn("1");
				_tActivityDA.SaveDatatActivityData(db, dt);
			}
		}

		if (dtJson.getListOftLeaveMobileData() != null) {
			for (tLeaveMobileData dt : dtJson.getListOftLeaveMobileData()) {
				tLeaveMobileDA _tLeaveMobileDA = new tLeaveMobileDA(db);
				dt.set_intLeaveIdSync("1");
				_tLeaveMobileDA.SaveDataMConfig(db, dt);
			}
		}

		if (dtJson.getListOftSalesProductHeaderData() != null) {
			for (tSalesProductHeaderData dt : dtJson.getListOftSalesProductHeaderData()) {
				tSalesProductHeaderDA _tSalesProductHeaderDA = new tSalesProductHeaderDA(db);
				dt.set_intSync("1");
				_tSalesProductHeaderDA.SaveDatatSalesProductHeaderData(db, dt);
			}
		}


		if (dtJson.get_ListOftCustomerBasedMobileHeaderData() != null) {
			for (tCustomerBasedMobileHeaderData dt : dtJson.get_ListOftCustomerBasedMobileHeaderData()) {
				tCustomerBasedMobileHeaderDA _tCustomerBasedMobileHeaderDA = new tCustomerBasedMobileHeaderDA(db);
				dt.set_intSync("1");
				_tCustomerBasedMobileHeaderDA.SaveDatatCustomerBasedMobileHeaderData(db, dt);
			}
		}
		if (dtJson.getListOftUserCheckinData() != null) {
			for (tUserCheckinData dt : dtJson.getListOftUserCheckinData()) {
				tUserCheckinDA _tUserCheckinDA = new tUserCheckinDA(db);
				dt.set_intSync("1");
				_tUserCheckinDA.SaveDatatUserCheckinData(db, dt);
			}
		}

		db.close();
	}
}
