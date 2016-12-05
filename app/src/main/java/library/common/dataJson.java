package library.common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class dataJson {

	public synchronized List<tEmployeeBRWithLOBData> getListOftEmployeeBRWithLOBData() {
		return ListOftEmployeeBRWithLOBData;
	}
	public synchronized void setListOftEmployeeBRWithLOBData(
			List<tEmployeeBRWithLOBData> listOftEmployeeBRWithLOBData) {
		ListOftEmployeeBRWithLOBData = listOftEmployeeBRWithLOBData;
	}
	public synchronized List<tLeaveMobileData> getListOftLeaveMobileData() {
		return ListOftLeaveMobileData;
	}
	public synchronized void setListOftLeaveMobileData(
			List<tLeaveMobileData> listOftLeaveMobileData) {
		ListOftLeaveMobileData = listOftLeaveMobileData;
	}
	public synchronized List<mTypeLeaveMobileData> getListOfmTypeLeaveMobileData() {
		return ListOfmTypeLeaveMobileData;
	}
	public synchronized void setListOfmTypeLeaveMobileData(
			List<mTypeLeaveMobileData> listOfmTypeLeaveMobileData) {
		ListOfmTypeLeaveMobileData = listOfmTypeLeaveMobileData;
	}
	public synchronized List<mProductBrandHeaderData> getListOfmProductBrandHeaderData() {
		return ListOfmProductBrandHeaderData;
	}
	public synchronized void setListOfmProductBrandHeaderData(
			List<mProductBrandHeaderData> listOfmProductBrandHeaderData) {
		ListOfmProductBrandHeaderData = listOfmProductBrandHeaderData;
	}
	public synchronized List<tActivityData> getListOftActivityData() {
		return ListOftActivityData;
	}
	public synchronized void setListOftActivityData(
			List<tActivityData> listOftActivityData) {
		ListOftActivityData = listOftActivityData;
	}
	public synchronized List<mGeolocationOutletSPGData> getListOfmGeolocationOutletSPGData() {
		return ListOfmGeolocationOutletSPGData;
	}
	public synchronized void setListOfmGeolocationOutletSPGData(
			List<mGeolocationOutletSPGData> listOfmGeolocationOutletSPGData) {
		ListOfmGeolocationOutletSPGData = listOfmGeolocationOutletSPGData;
	}
	public synchronized List<tAbsenUserData> getListOftAbsenUserData() {
		return ListOftAbsenUserData;
	}
	public synchronized void setListOftAbsenUserData(
			List<tAbsenUserData> listOftAbsenUserData) {
		ListOftAbsenUserData = listOftAbsenUserData;
	}



	public synchronized List<mEmployeeAreaData> getListOfmEmployeeAreaData() {
		return ListOfmEmployeeAreaData;
	}
	public synchronized void setListOfmEmployeeAreaData(
			List<mEmployeeAreaData> listOfmEmployeeAreaData) {
		ListOfmEmployeeAreaData = listOfmEmployeeAreaData;
	}
	public synchronized List<mEmployeeBranchData> getListOfmEmployeeBranchData() {
		return ListOfmEmployeeBranchData;
	}
	public synchronized void setListOfmEmployeeBranchData(
			List<mEmployeeBranchData> listOfmEmployeeBranchData) {
		ListOfmEmployeeBranchData = listOfmEmployeeBranchData;
	}
	public synchronized List<mEmployeeSalesProductData> getListOfmEmployeeSalesProductData() {
		return ListOfmEmployeeSalesProductData;
	}
	public synchronized void setListOfmEmployeeSalesProductData(
			List<mEmployeeSalesProductData> listOfmEmployeeSalesProductData) {
		ListOfmEmployeeSalesProductData = listOfmEmployeeSalesProductData;
	}
	public synchronized List<mNotificationData> getListOfmNotificationData() {
		return ListOfmNotificationData;
	}
	public synchronized void setListOfmNotificationData(
			List<mNotificationData> listOfmNotificationData) {
		ListOfmNotificationData = listOfmNotificationData;
	}
	public synchronized List<tErrorLogData> getListOftErrorLogData() {
		return ListOftErrorLogData;
	}
	public synchronized void setListOftErrorLogData(
			List<tErrorLogData> listOftErrorLogData) {
		ListOftErrorLogData = listOftErrorLogData;
	}
	public synchronized List<tSalesProductDetailData> getListOftSalesProductDetailData() {
		return ListOftSalesProductDetailData;
	}
	public synchronized void setListOftSalesProductDetailData(
			List<tSalesProductDetailData> listOftSalesProductDetailData) {
		ListOftSalesProductDetailData = listOftSalesProductDetailData;
	}
	public synchronized List<tSalesProductHeaderData> getListOftSalesProductHeaderData() {
		return ListOftSalesProductHeaderData;
	}
	public synchronized void setListOftSalesProductHeaderData(
			List<tSalesProductHeaderData> listOftSalesProductHeaderData) {
		ListOftSalesProductHeaderData = listOftSalesProductHeaderData;
	}
	public synchronized List<tDeviceInfoUserData> getListDatatDeviceInfoUser() {
		return ListDatatDeviceInfoUser;
	}
	public synchronized void setListDatatDeviceInfoUser(
			List<tDeviceInfoUserData> listDatatDeviceInfoUser) {
		ListDatatDeviceInfoUser = listDatatDeviceInfoUser;
	}
	public synchronized List<mMenuData> getListOfmMenuData() {
		return ListOfmMenuData;
	}
	public synchronized void setListOfmMenuData(List<mMenuData> listOfmMenuData) {
		ListOfmMenuData = listOfmMenuData;
	}
	public synchronized String getTxtValue() {
		return txtValue;
	}
	public synchronized void setTxtValue(String txtValue) {
		this.txtValue = txtValue;
	}
	public String getTxtMethod() {
		return txtMethod;
	}
	public void setTxtMethod(String txtMethod) {
		this.txtMethod = txtMethod;
	}
	
	public List<tUserLoginData> getListDatatUserLogin() {
		return ListDatatUserLogin;
	}
	public void setListDatatUserLogin(List<tUserLoginData> listDatatUserLogin) {
		ListDatatUserLogin = listDatatUserLogin;
	}
	public JSONObject txtJSON() throws JSONException {
		JSONObject resJson = new JSONObject();
		Collection<JSONObject> itemsListJquey = new ArrayList<JSONObject>();
		try
		{
			if(this.getListDatamConfig() != null){
				mconfigData dtConfig=new mconfigData();
				for (mconfigData data : this.getListDatamConfig()) {
					JSONObject item1 = new JSONObject();
					item1.put(dtConfig.Property_intEditAdmin, String.valueOf(data.get_intEditAdmin()));
					item1.put(dtConfig.Property_intId, String.valueOf(data.get_intId()));
					item1.put(dtConfig.Property_txtName, String.valueOf(data.get_txtName()));
					item1.put(dtConfig.Property_txtValue, String.valueOf(data.get_txtValue()));
					itemsListJquey.add(item1);
				}
				resJson.put(dtConfig.Property_ListOfMconfig, new JSONArray(itemsListJquey));
			}
			
			if(this.get_ListOfmProductBarcodeData()!=null){
				mProductBarcodeData dt=new mProductBarcodeData();
				for (mProductBarcodeData data : this.get_ListOfmProductBarcodeData()) {
					JSONObject item1 = new JSONObject();
					item1.put(data.Property_intProductCode, String.valueOf(data.get_intProductCode()));
					item1.put(data.Property_txtBarcode, String.valueOf(data.get_txtBarcode()));
					item1.put(data.Property_txtProductCode, String.valueOf(data.get_txtProductCode()));
					item1.put(data.Property_txtProductName, String.valueOf(data.get_txtProductName()));
					itemsListJquey.add(item1);
				}
				resJson.put(dt.Property_ListOfmProductBarcodeData, new JSONArray(itemsListJquey));
			}
			
			if(this.getListDatatDeviceInfoUser()!=null){
				tDeviceInfoUserData dttDeviceInfoUserData=new tDeviceInfoUserData();
				itemsListJquey = new ArrayList<JSONObject>();
				for (tDeviceInfoUserData data : this.getListDatatDeviceInfoUser()) {
					JSONObject item1 = new JSONObject();
					item1.put(dttDeviceInfoUserData.Property_intId, String.valueOf(data.get_intId()));
					item1.put(dttDeviceInfoUserData.Property_txtDeviceId, String.valueOf(data.get_txtDeviceId()));
					item1.put(dttDeviceInfoUserData.Property_txtDevice, String.valueOf(data.get_txtDevice()));
					item1.put(dttDeviceInfoUserData.Property_txtModel, String.valueOf(data.get_txtModel()));
					item1.put(dttDeviceInfoUserData.Property_txtUserId , String.valueOf(data.get_txtUserId()));
					item1.put(dttDeviceInfoUserData.Property_txtVersion , String.valueOf(data.get_txtVersion()));
					itemsListJquey.add(item1);
				}
				resJson.put(dttDeviceInfoUserData.Property_ListOftDeviceInfoUserData, new JSONArray(itemsListJquey));
			}
			
			if(this.getListOfmMenuData()!=null){
				mMenuData dtmMenuData=new mMenuData();
				itemsListJquey = new ArrayList<JSONObject>();
				for (mMenuData data : this.getListOfmMenuData()) {
					JSONObject item1 = new JSONObject();
					item1.put(dtmMenuData.Property_intId, Long.valueOf(data.get_intId()));
					item1.put(dtmMenuData.Property_IntOrder, Long.valueOf(data.get_IntOrder()));
					item1.put(dtmMenuData.Property_IntParentID, Long.valueOf(data.get_IntParentID()));
					item1.put(dtmMenuData.Property_TxtDescription , String.valueOf(data.get_TxtDescription()));
					item1.put(dtmMenuData.Property_TxtLink , String.valueOf(data.get_TxtLink()));
					item1.put(dtmMenuData.Property_TxtMenuName , String.valueOf(data.get_TxtMenuName()));
					itemsListJquey.add(item1);
				}
				resJson.put(dtmMenuData.Property_ListOfMMenuData, new JSONArray(itemsListJquey));
			}
			
			
			if(this.getListOftSalesProductDetailData()!=null){
				tSalesProductDetailData dttSalesProductDetailData=new tSalesProductDetailData();
				itemsListJquey = new ArrayList<JSONObject>();
				for (tSalesProductDetailData data : this.getListOftSalesProductDetailData()) {
					JSONObject item1 = new JSONObject();
					item1.put(dttSalesProductDetailData.Property_intId, String.valueOf(data.get_intId()));
					item1.put(dttSalesProductDetailData.Property_dtDate, String.valueOf(data.get_dtDate()));
					item1.put(dttSalesProductDetailData.Property_intPrice, String.valueOf(data.get_intPrice()));
					item1.put(dttSalesProductDetailData.Property_intQty , String.valueOf(data.get_intQty()));
					item1.put(dttSalesProductDetailData.Property_txtCodeProduct , String.valueOf(data.get_txtCodeProduct()));
					item1.put(dttSalesProductDetailData.Property_txtKeterangan , String.valueOf(data.get_txtKeterangan()));
					item1.put(dttSalesProductDetailData.Property_txtNameProduct , String.valueOf(data.get_txtNameProduct()));
					item1.put(dttSalesProductDetailData.Property_txtNIK , String.valueOf(data.get_txtNIK()));
					item1.put(dttSalesProductDetailData.Property_intTotal , String.valueOf(data.get_intTotal()));
					item1.put(dttSalesProductDetailData.Property_txtNoSo , String.valueOf(data.get_txtNoSo()));
					itemsListJquey.add(item1);
				}
				resJson.put(dttSalesProductDetailData.Property_ListOftSalesProductDetailData, new JSONArray(itemsListJquey));
			}

			if(this.getListOftUserCheckinData()!=null){
				tUserCheckinData dttUserCheckinData=new tUserCheckinData();
				itemsListJquey = new ArrayList<JSONObject>();
				for (tUserCheckinData data : this.getListOftUserCheckinData()) {
					JSONObject item1 = new JSONObject();
					item1.put(dttUserCheckinData.Property_txtGType, String.valueOf(data.get_txtGType()));
					item1.put(dttUserCheckinData.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
					item1.put(dttUserCheckinData.Property_txtUserID, String.valueOf(data.get_txtUserID()));
					item1.put(dttUserCheckinData.Property_txtLat , String.valueOf(data.get_txtLat()));
					item1.put(dttUserCheckinData.Property_txtLong , String.valueOf(data.get_txtLong()));
					item1.put(dttUserCheckinData.Property_txtOutletId , String.valueOf(data.get_txtOutletId()));
					item1.put(dttUserCheckinData.Property_intFlag , String.valueOf(data.get_intFlag()));
					item1.put(dttUserCheckinData.Property_txtAcc , String.valueOf(data.get_txtAcc()));
//					item1.put(dttUserCheckinData.Property_intTotal , String.valueOf(data.get_intTotal()));
//					item1.put(dttUserCheckinData.Property_txtNoSo , String.valueOf(data.get_txtNoSo()));
					itemsListJquey.add(item1);
				}
				resJson.put(dttUserCheckinData.Property_ListOftUserCheckinData, new JSONArray(itemsListJquey));
			}
			
			
			if(this.getListOftSalesProductHeaderData()!=null){
				tSalesProductHeaderData dttSalesProductHeaderData=new tSalesProductHeaderData();
				itemsListJquey = new ArrayList<JSONObject>();
				for (tSalesProductHeaderData data : this.getListOftSalesProductHeaderData()) {
					JSONObject item1 = new JSONObject();
					item1.put(dttSalesProductHeaderData.Property_intId, String.valueOf(data.get_intId()));
					item1.put(dttSalesProductHeaderData.Property_OutletCode, String.valueOf(data.get_OutletCode()));
					item1.put(dttSalesProductHeaderData.Property_OutletName, String.valueOf(data.get_OutletName()));
					item1.put(dttSalesProductHeaderData.Property_txtDate , String.valueOf(data.get_dtDate()));
					item1.put(dttSalesProductHeaderData.Property_intIdAbsenUser , String.valueOf(data.get_intIdAbsenUser()));
					item1.put(dttSalesProductHeaderData.Property_intSubmit , String.valueOf(data.get_intSubmit()));
					item1.put(dttSalesProductHeaderData.Property_intSumAmount , String.valueOf(data.get_intSumAmount()));
					item1.put(dttSalesProductHeaderData.Property_intSumItem , String.valueOf(data.get_intSumItem()));
					item1.put(dttSalesProductHeaderData.Property_intSync , String.valueOf(data.get_intSync()));
					item1.put(dttSalesProductHeaderData.Property_txtBranchCode , String.valueOf(data.get_txtBranchCode()));
					item1.put(dttSalesProductHeaderData.Property_txtBranchName , String.valueOf(data.get_txtBranchName()));
					item1.put(dttSalesProductHeaderData.Property_UserId , String.valueOf(data.get_UserId()));
					item1.put(dttSalesProductHeaderData.Property_txtKeterangan , String.valueOf(data.get_txtKeterangan()));
					item1.put(dttSalesProductHeaderData.Property_txtNIK , String.valueOf(data.get_txtNIK()));
					itemsListJquey.add(item1);
				}
				resJson.put(dttSalesProductHeaderData.Property_ListOftSalesProductHeaderData, new JSONArray(itemsListJquey));
			}
			
			if(this.getListOfmEmployeeAreaData()!=null){
				mEmployeeAreaData dtmEmployeeAreaData=new mEmployeeAreaData();
				itemsListJquey = new ArrayList<JSONObject>();
				for (mEmployeeAreaData data : this.getListOfmEmployeeAreaData()) {
					JSONObject item1 = new JSONObject();
					item1.put(dtmEmployeeAreaData.Property_intBranchId, String.valueOf(data.get_intBranchId()));
					item1.put(dtmEmployeeAreaData.Property_intChannelId, String.valueOf(data.get_intChannelId()));
					item1.put(dtmEmployeeAreaData.Property_intEmployeeId, String.valueOf(data.get_intEmployeeId()));
					item1.put(dtmEmployeeAreaData.Property_intID , String.valueOf(data.get_intID()));
					item1.put(dtmEmployeeAreaData.Property_intOutletId , String.valueOf(data.get_intOutletId()));
					item1.put(dtmEmployeeAreaData.Property_intRayonId , String.valueOf(data.get_intRayonId()));
					item1.put(dtmEmployeeAreaData.Property_intRegionId , String.valueOf(data.get_intRegionId()));
					item1.put(dtmEmployeeAreaData.Property_txtBranchCode , String.valueOf(data.get_txtBranchCode()));
					item1.put(dtmEmployeeAreaData.Property_txtBranchName , String.valueOf(data.get_txtBranchName()));
					item1.put(dtmEmployeeAreaData.Property_txtName , String.valueOf(data.get_txtName()));
					item1.put(dtmEmployeeAreaData.Property_txtNIK , String.valueOf(data.get_txtNIK()));
					item1.put(dtmEmployeeAreaData.Property_txtOutletCode , String.valueOf(data.get_txtOutletCode()));
					item1.put(dtmEmployeeAreaData.Property_txtOutletName , String.valueOf(data.get_txtOutletName()));
					item1.put(dtmEmployeeAreaData.Property_txtRayonName , String.valueOf(data.get_txtRayonCode()));
					item1.put(dtmEmployeeAreaData.Property_txtRayonName , String.valueOf(data.get_txtRayonName()));
					item1.put(dtmEmployeeAreaData.Property_txtRegionName , String.valueOf(data.get_txtRegionName()));
					itemsListJquey.add(item1);
				}
				resJson.put(dtmEmployeeAreaData.Property_ListOfmEmployeeAreaData, new JSONArray(itemsListJquey));
			}
			
			
			if(this.getListOfmEmployeeBranchData()!=null){
				mEmployeeBranchData dtmEmployeeBranchData=new mEmployeeBranchData();
				itemsListJquey = new ArrayList<JSONObject>();
				for (mEmployeeBranchData data : this.getListOfmEmployeeBranchData()) {
					JSONObject item1 = new JSONObject();
					item1.put(dtmEmployeeBranchData.Property_EmpId, String.valueOf(data.get_EmpId()));
					item1.put(dtmEmployeeBranchData.Property_intID, String.valueOf(data.get_intID()));
					item1.put(dtmEmployeeBranchData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
					item1.put(dtmEmployeeBranchData.Property_txtBranchName , String.valueOf(data.get_txtBranchName()));
					item1.put(dtmEmployeeBranchData.Property_txtName , String.valueOf(data.get_txtName()));
					item1.put(dtmEmployeeBranchData.Property_txtNIK , String.valueOf(data.get_txtNIK()));
					itemsListJquey.add(item1);
				}
				resJson.put(dtmEmployeeBranchData.Property_ListOfEmployeeBranchData, new JSONArray(itemsListJquey));
			}

			if(this.getListOfmEmployeeSalesProductData()!=null){
				mEmployeeSalesProductData dtmEmployeeSalesProductData=new mEmployeeSalesProductData();
				itemsListJquey = new ArrayList<JSONObject>();
				for (mEmployeeSalesProductData data : this.getListOfmEmployeeSalesProductData()) {
					JSONObject item1 = new JSONObject();
					item1.put(dtmEmployeeSalesProductData.Property_decBobot, String.valueOf(data.get_decBobot()));
					item1.put(dtmEmployeeSalesProductData.Property_decHJD, String.valueOf(data.get_decHJD()));
					item1.put(dtmEmployeeSalesProductData.Property_intId, String.valueOf(data.get_intId()));
					item1.put(dtmEmployeeSalesProductData.Property_txtBrandDetailGramCode , String.valueOf(data.get_txtBrandDetailGramCode()));
					item1.put(dtmEmployeeSalesProductData.Property_txtName , String.valueOf(data.get_txtName()));
					item1.put(dtmEmployeeSalesProductData.Property_txtNIK , String.valueOf(data.get_txtNIK()));
					item1.put(dtmEmployeeSalesProductData.Property_txtProductBrandDetailGramName , String.valueOf(data.get_txtProductBrandDetailGramName()));
					itemsListJquey.add(item1);
				}
				resJson.put(dtmEmployeeSalesProductData.Property_ListOfmEmployeeSalesProductData, new JSONArray(itemsListJquey));
			}
			
			
			if(this.getListOfmNotificationData()!=null){
				mNotificationData dtmNotificationData=new mNotificationData();
				itemsListJquey = new ArrayList<JSONObject>();
				for (mNotificationData data : this.getListOfmNotificationData()) {
					JSONObject item1 = new JSONObject();
					item1.put(dtmNotificationData.Property_dtPublishEnd, String.valueOf(data.get_dtPublishEnd()));
					item1.put(dtmNotificationData.Property_dtPublishStart, String.valueOf(data.get_dtPublishStart()));
					item1.put(dtmNotificationData.Property_dtStatus, String.valueOf(data.get_dtStatus()));
					item1.put(dtmNotificationData.Property_intSync , String.valueOf(data.get_intSync()));
					item1.put(dtmNotificationData.Property_txtDataId , String.valueOf(data.get_txtDataId()));
					item1.put(dtmNotificationData.Property_txtDescription , String.valueOf(data.get_txtDescription()));
					item1.put(dtmNotificationData.Property_txtImage , String.valueOf(data.get_txtImage()));
					item1.put(dtmNotificationData.Property_txtNotifId , String.valueOf(data.get_txtNotifId()));
					item1.put(dtmNotificationData.Property_txtStatus , String.valueOf(data.get_txtStatus()));
					item1.put(dtmNotificationData.Property_txtTitle , String.valueOf(data.get_txtTitle()));
					item1.put(dtmNotificationData.Property_txtUserID , String.valueOf(data.get_txtUserID()));
					item1.put(dtmNotificationData.Property_txtLinkImage , String.valueOf(data.get_txtLinkImage()));
					item1.put(dtmNotificationData.Property_intSuccessDLFile , String.valueOf(data.get_intSuccessDLFile()));
					itemsListJquey.add(item1);
				}
				resJson.put(dtmNotificationData.Property_ListOfmNotificationData, new JSONArray(itemsListJquey));
			}
			
			if(this.getListOftAbsenUserData()!=null){
				tAbsenUserData dttAbsenUserData=new tAbsenUserData();
				itemsListJquey = new ArrayList<JSONObject>();
				for (tAbsenUserData data : this.getListOftAbsenUserData()) {
					JSONObject item1 = new JSONObject();
					item1.put(dttAbsenUserData.Property_dtDateCheckIn, String.valueOf(data.get_dtDateCheckIn()));
					item1.put(dttAbsenUserData.Property_dtDateCheckOut, String.valueOf(data.get_dtDateCheckOut()));
					item1.put(dttAbsenUserData.Property_intId, String.valueOf(data.get_intId()));
					item1.put(dttAbsenUserData.Property_intSubmit, String.valueOf(data.get_intSubmit()));
					item1.put(dttAbsenUserData.Property_intSync , String.valueOf(data.get_intSync()));
					item1.put(dttAbsenUserData.Property_txtAbsen , String.valueOf(data.get_txtAbsen()));
					item1.put(dttAbsenUserData.Property_txtAccuracy , String.valueOf(data.get_txtAccuracy()));
					item1.put(dttAbsenUserData.Property_txtBranchCode , String.valueOf(data.get_txtBranchCode()));
					item1.put(dttAbsenUserData.Property_txtBranchName , String.valueOf(data.get_txtBranchName()));
					item1.put(dttAbsenUserData.Property_txtLatitude , String.valueOf(data.get_txtLatitude()));
					item1.put(dttAbsenUserData.Property_txtLongitude , String.valueOf(data.get_txtLongitude()));
					item1.put(dttAbsenUserData.Property_txtOutletCode , String.valueOf(data.get_txtOutletCode()));
					item1.put(dttAbsenUserData.Property_txtOutletName , String.valueOf(data.get_txtOutletName()));
					item1.put(dttAbsenUserData.Property_txtUserId , String.valueOf(data.get_txtUserId()));
					item1.put(dttAbsenUserData.Property_txtDeviceId , String.valueOf(data.get_txtDeviceId()));
					item1.put(dttAbsenUserData.Property_txtImg1 , String.valueOf(data.get_txtImg1()));
					item1.put(dttAbsenUserData.Property_txtImg2 , String.valueOf(data.get_txtImg2()));
					itemsListJquey.add(item1);
				}
				resJson.put(dttAbsenUserData.Property_ListOftAbsenUser, new JSONArray(itemsListJquey));
			}
			
			
			if(this.getListOfmGeolocationOutletSPGData()!=null){
				mGeolocationOutletSPGData dtmGeolocationOutletSPGData=new mGeolocationOutletSPGData();
				itemsListJquey = new ArrayList<JSONObject>();
				for (mGeolocationOutletSPGData data : this.getListOfmGeolocationOutletSPGData()) {
					JSONObject item1 = new JSONObject();
					item1.put(dtmGeolocationOutletSPGData.Property_IntId, String.valueOf(data.get_intId()));
					item1.put(dtmGeolocationOutletSPGData.Property_txtAcc, String.valueOf(data.get_txtAcc()));
					item1.put(dtmGeolocationOutletSPGData.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
					item1.put(dtmGeolocationOutletSPGData.Property_txtLatitude , String.valueOf(data.get_txtLatitude()));
					item1.put(dtmGeolocationOutletSPGData.Property_txtLongitude , String.valueOf(data.get_txtLongitude()));
					item1.put(dtmGeolocationOutletSPGData.Property_txtOutletCode , String.valueOf(data.get_txtOutletCode()));
					itemsListJquey.add(item1);
				}
				resJson.put(dtmGeolocationOutletSPGData.Property_ListOfmGeolocationOutletSPG, new JSONArray(itemsListJquey));
			}

			if(this.getListOftActivityData()!=null){
				tActivityData dttActivityData=new tActivityData();
				itemsListJquey = new ArrayList<JSONObject>();
				for (tActivityData data : this.getListOftActivityData()) {
					JSONObject item1 = new JSONObject();
					item1.put(dttActivityData.Property_dtActivity, String.valueOf(data.get_dtActivity()));
					item1.put(dttActivityData.Property_intActive, String.valueOf(data.get_intActive()));
					item1.put(dttActivityData.Property_intId, String.valueOf(data.get_intId()));
					item1.put(dttActivityData.Property_intIdSyn , String.valueOf(data.get_intIdSyn()));
					item1.put(dttActivityData.Property_intFlag , String.valueOf(data.get_intFlag()));
					item1.put(dttActivityData.Property_intSubmit , String.valueOf(data.get_intSubmit()));
					item1.put(dttActivityData.Property_txtDesc , String.valueOf(data.get_txtDesc()));
					item1.put(dttActivityData.Property_txtDeviceId , String.valueOf(data.get_txtDeviceId()));
					item1.put(dttActivityData.Property_txtImg1 , String.valueOf(data.get_txtImg1()));
					item1.put(dttActivityData.Property_txtImg2 , String.valueOf(data.get_txtImg2()));
					item1.put(dttActivityData.Property_txtOutletCode , String.valueOf(data.get_txtOutletCode()));
					item1.put(dttActivityData.Property_txtOutletName , String.valueOf(data.get_txtOutletName()));
					item1.put(dttActivityData.Property_txtUserId , String.valueOf(data.get_txtUserId()));
					item1.put(dttActivityData.Property_txtBranch , String.valueOf(data.get_txtBranch()));
					itemsListJquey.add(item1);
				}
				resJson.put(dttActivityData.Property_ListOfTActivity, new JSONArray(itemsListJquey));
			}

			if(this.getListOfmProductBrandHeaderData()!=null){
				mProductBrandHeaderData dtmProductBrandHeaderData=new mProductBrandHeaderData();
				itemsListJquey = new ArrayList<JSONObject>();
				for (mProductBrandHeaderData data : this.getListOfmProductBrandHeaderData()) {
					JSONObject item1 = new JSONObject();
					item1.put(dtmProductBrandHeaderData.Property_intmProductUmbrandId ,String.valueOf(data.get_intmProductUmbrandId()));
					item1.put(dtmProductBrandHeaderData.Property_txtAliasName , String.valueOf(data.get_txtAliasName()));
					item1.put(dtmProductBrandHeaderData.Property_txtProductBrandCode , String.valueOf(data.get_txtProductBrandCode()));
					item1.put(dtmProductBrandHeaderData.Property_txtProductBrandName , String.valueOf(data.get_txtProductBrandName()));
					itemsListJquey.add(item1);
				}
				resJson.put(dtmProductBrandHeaderData.Property_ListOfmProductBrandHeader, new JSONArray(itemsListJquey));
			}
			
			
			if(this.getListOfmTypeLeaveMobileData()!=null){
				mTypeLeaveMobileData dtmTypeLeaveMobileData=new mTypeLeaveMobileData();
				itemsListJquey = new ArrayList<JSONObject>();
				for (mTypeLeaveMobileData data : this.getListOfmTypeLeaveMobileData()) {
					JSONObject item1 = new JSONObject();
					item1.put(dtmTypeLeaveMobileData.Property_intTipeLeave ,String.valueOf(data.get_intTipeLeave()));
					item1.put(dtmTypeLeaveMobileData.Property_txtTipeLeaveName , String.valueOf(data.get_txtTipeLeaveName()));
					itemsListJquey.add(item1);
				}
				resJson.put(dtmTypeLeaveMobileData.PropertyListOfmTypeLeaveMobileData, new JSONArray(itemsListJquey));
			}
			
			
			if(this.getListOftLeaveMobileData()!=null){
				List<mTypeLeaveMobileData> _ListOfDatamTypeLeaveMobileData=getListOfmTypeLeaveMobileData();
				tLeaveMobileData dttLeaveMobileData=new tLeaveMobileData();
				itemsListJquey = new ArrayList<JSONObject>();
				for (tLeaveMobileData data : this.getListOftLeaveMobileData()) {
					JSONObject item1 = new JSONObject();
					item1.put(dttLeaveMobileData.Property_dtLeave ,String.valueOf(data.get_dtLeave()));
					item1.put(dttLeaveMobileData.Property_intLeaveId , String.valueOf(data.get_intLeaveId()));
					item1.put(dttLeaveMobileData.Property_intLeaveIdSync , String.valueOf(data.get_intLeaveIdSync()));
					item1.put(dttLeaveMobileData.Property_intSubmit , String.valueOf(data.get_intSubmit()));
					item1.put(dttLeaveMobileData.Property_txtAlasan , String.valueOf(data.get_txtAlasan()));
					item1.put(dttLeaveMobileData.Property_txtDeviceId , String.valueOf(data.get_txtDeviceId()));
					item1.put(dttLeaveMobileData.Property_txtTypeAlasan , String.valueOf(data.get_txtTypeAlasan()));
					if(_ListOfDatamTypeLeaveMobileData!=null){
						for(mTypeLeaveMobileData d : _ListOfDatamTypeLeaveMobileData){
							if(Integer.valueOf(d.get_intTipeLeave())== Integer.valueOf(data.get_txtTypeAlasan())){
								item1.put(dttLeaveMobileData.Property_txtTypeAlasanName , String.valueOf(d.get_txtTipeLeaveName()));
								break;
							}
						}	
					}
					item1.put(dttLeaveMobileData.Property_txtUserId , String.valueOf(data.get_txtUserId()));
					itemsListJquey.add(item1);
				}
				resJson.put(dttLeaveMobileData.PropertyListOftLeaveMobileData, new JSONArray(itemsListJquey));
			}
			
			
						
			if(this.getListOftEmployeeBRWithLOBData()!=null){
				tEmployeeBRWithLOBData dttEmployeeBRWithLOBData=new tEmployeeBRWithLOBData();
				itemsListJquey = new ArrayList<JSONObject>();
				for (tEmployeeBRWithLOBData data : this.getListOftEmployeeBRWithLOBData()) {
					JSONObject item1 = new JSONObject();
					item1.put(dttEmployeeBRWithLOBData.Property_IntEmployeeDetailId ,String.valueOf(data.get_IntEmployeeDetailId()));
					item1.put(dttEmployeeBRWithLOBData.Property_IntEmployeeId , String.valueOf(data.get_IntEmployeeId()));
					item1.put(dttEmployeeBRWithLOBData.Property_IntlobId , String.valueOf(data.get_IntlobId()));
					item1.put(dttEmployeeBRWithLOBData.Property_TxtLobName , String.valueOf(data.get_TxtLobName()));
					itemsListJquey.add(item1);
				}
				resJson.put(dttEmployeeBRWithLOBData.Property_ListOftEmployeeBRWithLOBData, new JSONArray(itemsListJquey));
			}
		}
		catch(Exception ex)
		{
		
		}
		resJson.put(Property_intResult, getIntResult());
		resJson.put(Property_txtDescription, getTxtDescription());
		resJson.put(Property_txtMessage, getTxtMessage());
		resJson.put(Property_txtMethod, getTxtMethod());
		resJson.put(Property_txtValue, getTxtValue());
		resJson.put(Property_txtSessionLoginId, get_txtSessionLoginId());
		resJson.put(Property_txtUserId, get_txtUserId());
		return resJson;
	}
	public JSONObject GenerateJsontSalesProductDetailData(tSalesProductDetailData data) throws JSONException{
		JSONObject item1 = new JSONObject();
		item1.put(data.Property_dtDate, String.valueOf(data.get_dtDate()));
		item1.put(data.Property_intActive, String.valueOf(data.get_intActive()));
		item1.put(data.Property_intId, String.valueOf(data.get_intId()));
		item1.put(data.Property_intPrice, String.valueOf(data.get_intPrice()));
		item1.put(data.Property_intQty, String.valueOf(data.get_intQty()));
		item1.put(data.Property_intTotal, String.valueOf(data.get_intTotal()));
		item1.put(data.Property_txtCodeProduct, String.valueOf(data.get_txtCodeProduct()));
		item1.put(data.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
		item1.put(data.Property_txtNameProduct, String.valueOf(data.get_txtNameProduct()));
		item1.put(data.Property_txtNIK, String.valueOf(data.get_txtNIK()));
		item1.put(data.Property_txtNoSo, String.valueOf(data.get_txtNoSo()));
		return item1;
	}
	public JSONObject GenerateJsontSalesProductHeaderData(tSalesProductHeaderData data) throws JSONException{
		JSONObject item1 = new JSONObject();
		item1.put(data.Property_intId, String.valueOf(data.get_intId()));
		item1.put(data.Property_txtKeterangan, String.valueOf(data.get_txtKeterangan()));
		item1.put(data.Property_txtNIK, String.valueOf(data.get_txtNIK()));
		item1.put(data.Property_txtBranchCode, String.valueOf(data.get_txtBranchCode()));
		item1.put(data.Property_txtBranchName, String.valueOf(data.get_txtBranchName()));
		item1.put(data.Property_txtDate, String.valueOf(data.get_dtDate()));
		item1.put(data.Property_intIdAbsenUser, String.valueOf(data.get_intIdAbsenUser()));
		item1.put(data.Property_intSumAmount, String.valueOf(data.get_intSumAmount()));
		item1.put(data.Property_intSumItem, String.valueOf(data.get_intSumItem()));
		item1.put(data.Property_OutletCode, String.valueOf(data.get_OutletCode()));
		item1.put(data.Property_OutletName, String.valueOf(data.get_OutletName()));
		item1.put(data.Property_UserId, String.valueOf(data.get_UserId()));
		return item1;
	}
	
	public String getIntResult() {
		return intResult;
	}
	public void setIntResult(String intResult) {
		this.intResult = intResult;
	}
	public String getTxtMessage() {
		return txtMessage;
	}
	public void setTxtMessage(String txtMessage) {
		this.txtMessage = txtMessage;
	}
	public String getTxtDescription() {
		return txtDescription;
	}
	public void setTxtDescription(String txtDescription) {
		this.txtDescription = txtDescription;
	}
	public List<mconfigData> getListDatamConfig() {
		return ListDatamConfig;
	}
	public void setListDatamConfig(List<mconfigData> listDatamConfig) {
		ListDatamConfig = listDatamConfig;
	}
	public dataJson() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	private String intResult;
	private String txtMessage;
	private String txtMethod;
	private String txtValue;
	private String txtDescription;
	private List<mconfigData> ListDatamConfig;
	private List<tUserLoginData> ListDatatUserLogin;
	private List<tDeviceInfoUserData> ListDatatDeviceInfoUser;
	private List<mMenuData> ListOfmMenuData;
	private List<tSalesProductDetailData> ListOftSalesProductDetailData;
	private List<tSalesProductHeaderData> ListOftSalesProductHeaderData;
	private List<mEmployeeAreaData> ListOfmEmployeeAreaData;
	private List<mEmployeeBranchData> ListOfmEmployeeBranchData;
	private List<mEmployeeSalesProductData> ListOfmEmployeeSalesProductData;
	private List<mNotificationData> ListOfmNotificationData;
	private List<tErrorLogData> ListOftErrorLogData;
	private List<tAbsenUserData> ListOftAbsenUserData;
	private List<mGeolocationOutletSPGData> ListOfmGeolocationOutletSPGData;
	private List<tActivityData> ListOftActivityData;
	private List<mProductBrandHeaderData> ListOfmProductBrandHeaderData;
	private List<tLeaveMobileData> ListOftLeaveMobileData;
	private List<mTypeLeaveMobileData> ListOfmTypeLeaveMobileData;
	private List<tEmployeeBRWithLOBData> ListOftEmployeeBRWithLOBData;

	public List<tUserCheckinData> getListOftUserCheckinData() {
		return ListOftUserCheckinData;
	}

	public void setListOftUserCheckinData(List<tUserCheckinData> listOftUserCheckinData) {
		ListOftUserCheckinData = listOftUserCheckinData;
	}

	private List<tUserCheckinData> ListOftUserCheckinData;

	private List<mProductBarcodeData> _ListOfmProductBarcodeData;

	public List<tCustomerBasedMobileHeaderData> get_ListOftCustomerBasedMobileHeaderData() {
		return _ListOftCustomerBasedMobileHeaderData;
	}

	public void set_ListOftCustomerBasedMobileHeaderData(List<tCustomerBasedMobileHeaderData> _ListOftCustomerBasedMobileHeaderData) {
		this._ListOftCustomerBasedMobileHeaderData = _ListOftCustomerBasedMobileHeaderData;
	}

	private List<tCustomerBasedMobileHeaderData> _ListOftCustomerBasedMobileHeaderData;

	public List<mProductBarcodeData> get_ListOfmProductBarcodeData() {
		return _ListOfmProductBarcodeData;
	}
	public void set_ListOfmProductBarcodeData(List<mProductBarcodeData> _ListOfmProductBarcodeData) {
		this._ListOfmProductBarcodeData = _ListOfmProductBarcodeData;
	}

	private String _txtSessionLoginId;
	public String get_txtSessionLoginId() {
		return _txtSessionLoginId;
	}
	public void set_txtSessionLoginId(String _txtSessionLoginId) {
		this._txtSessionLoginId = _txtSessionLoginId;
	}

	private String _txtUserId;
	public String get_txtUserId() {
		return _txtUserId;
	}
	public void set_txtUserId(String _txtUserId) {
		this._txtUserId = _txtUserId;
	}

	private String Property_txtUserId="txtUserId";
	private String Property_txtSessionLoginId="txtSessionLoginId";
	public String Property_intResult="intResult";
	public String Property_txtMessage="txtMessage";
	public String Property_txtDescription="txtDescription";
	public String Property_txtMethod="txtMethod";
	public String Property_txtValue="txtValue";
}
