package library.common;

public class mEmployeeAreaData {
	public synchronized String get_intEmployeeId() {
		return _intEmployeeId;
	}
	public synchronized void set_intEmployeeId(String _intEmployeeId) {
		this._intEmployeeId = _intEmployeeId;
	}
	public synchronized String get_txtNIK() {
		return _txtNIK;
	}
	public synchronized void set_txtNIK(String _txtNIK) {
		this._txtNIK = _txtNIK;
	}
	public synchronized String get_txtName() {
		return _txtName;
	}
	public synchronized void set_txtName(String _txtName) {
		this._txtName = _txtName;
	}
	public synchronized String get_intID() {
		return _intID;
	}
	public synchronized void set_intID(String _intID) {
		this._intID = _intID;
	}
	/*
	public synchronized String get_intRowStatus() {
		return _intRowStatus;
	}
	public synchronized void set_intRowStatus(String _intRowStatus) {
		this._intRowStatus = _intRowStatus;
	}
	*/
	public synchronized String get_intRegionId() {
		return _intRegionId;
	}
	public synchronized void set_intRegionId(String _intRegionId) {
		this._intRegionId = _intRegionId;
	}
	public synchronized String get_txtRegionName() {
		return _txtRegionName;
	}
	public synchronized void set_txtRegionName(String _txtRegionName) {
		this._txtRegionName = _txtRegionName;
	}
	public synchronized String get_intBranchId() {
		return _intBranchId;
	}
	public synchronized void set_intBranchId(String _intBranchId) {
		this._intBranchId = _intBranchId;
	}
	public synchronized String get_txtBranchCode() {
		return _txtBranchCode;
	}
	public synchronized void set_txtBranchCode(String _txtBranchCode) {
		this._txtBranchCode = _txtBranchCode;
	}
	public synchronized String get_txtBranchName() {
		return _txtBranchName;
	}
	public synchronized void set_txtBranchName(String _txtBranchName) {
		this._txtBranchName = _txtBranchName;
	}
	public synchronized String get_intRayonId() {
		return _intRayonId;
	}
	public synchronized void set_intRayonId(String _intRayonId) {
		this._intRayonId = _intRayonId;
	}
	public synchronized String get_txtRayonCode() {
		return _txtRayonCode;
	}
	public synchronized void set_txtRayonCode(String _txtRayonCode) {
		this._txtRayonCode = _txtRayonCode;
	}
	public synchronized String get_txtRayonName() {
		return _txtRayonName;
	}
	public synchronized void set_txtRayonName(String _txtRayonName) {
		this._txtRayonName = _txtRayonName;
	}
	public synchronized String get_intChannelId() {
		return _intChannelId;
	}
	public synchronized void set_intChannelId(String _intChannelId) {
		this._intChannelId = _intChannelId;
	}
	public synchronized String get_intOutletId() {
		return _intOutletId;
	}
	public synchronized void set_intOutletId(String _intOutletId) {
		this._intOutletId = _intOutletId;
	}
	public synchronized String get_txtOutletCode() {
		return _txtOutletCode;
	}
	public synchronized void set_txtOutletCode(String _txtOutletCode) {
		this._txtOutletCode = _txtOutletCode;
	}
	public synchronized String get_txtOutletName() {
		return _txtOutletName;
	}
	public synchronized void set_txtOutletName(String _txtOutletName) {
		this._txtOutletName = _txtOutletName;
	}

	public synchronized String get_txtLatitude() {
		return _txtLatitude;
	}

	public synchronized void set_txtLatitude(String _txtLatitude) {
		this._txtLatitude = _txtLatitude;
	}

	public synchronized String get_txtLongitude() {
		return _txtLongitude;
	}

	public synchronized void set_txtLongitude(String _txtLongitude) {
		this._txtLongitude = _txtLongitude;
	}

	private String _intEmployeeId;
	private String _txtNIK;
	private String _txtName;
	private String _intID;
	//private String _intRowStatus;
	private String _intRegionId;
	private String _txtRegionName;
	private String _intBranchId;
	private String _txtBranchCode;
	private String _txtBranchName;
	private String _intRayonId;
	private String _txtRayonCode;
	private String _txtRayonName;
	private String _intChannelId;
	private String _intOutletId;
	private String _txtOutletCode;
	private String _txtOutletName;
	private String _txtLatitude;
	private String _txtLongitude;

	public String Property_intEmployeeId="intEmployeeId";
	public String Property_txtNIK="txtNIK";
	public String Property_txtName="txtName";
	public String Property_intID="intID";
	//public String Property_intRowStatus="intRowStatus";
	public String Property_intRegionId="intRegionId";
	public String Property_txtRegionName="txtRegionName";
	public String Property_intBranchId="intBranchId";
	public String Property_txtBranchCode="txtBranchCode";
	public String Property_txtBranchName="txtBranchName";
	public String Property_intRayonId="intRayonId";
	public String Property_txtRayonCode="txtRayonCode";
	public String Property_txtRayonName="txtRayonName";
	public String Property_intChannelId="intChannelId";
	public String Property_intOutletId="intOutletId";
	public String Property_txtOutletCode="txtOutletCode";
	public String Property_txtOutletName="txtOutletName";
	public String Property_txtLatitude="txtLatitude";
	public String Property_txtLongitude ="txtLongitude";
	public String Property_ListOfmEmployeeAreaData="ListOfmEmployeeAreaData";
	public String Property_All=Property_intID +","+ Property_intBranchId 
			+","+ Property_intChannelId 
			+","+ Property_intEmployeeId 
			+","+ Property_intOutletId
			+","+ Property_intRayonId
			+","+ Property_intRegionId
			+","+ Property_txtBranchCode
			+","+ Property_txtBranchName
			+","+ Property_txtName
			+","+ Property_txtNIK
			+","+ Property_txtLatitude
			+","+ Property_txtLongitude
			+","+ Property_txtOutletCode
			+","+ Property_txtOutletName
			+","+ Property_txtRayonCode
			+","+ Property_txtRayonName
			+","+ Property_txtRegionName;
	
	public mEmployeeAreaData(String _intEmployeeId, String _txtNIK,
			String _txtName, String _intID,
			String _intRegionId, String _txtRegionName, String _intBranchId,
			String _txtBranchCode, String _txtBranchName, String _intRayonId,
			String _txtRayonCode, String _txtRayonName, String _intChannelId,
			String _intOutletId, String _txtOutletCode, String _txtOutletName, String _txtLatitude,String _txtLongitude) {
		this._intEmployeeId = _intEmployeeId;
		this._txtNIK = _txtNIK;
		this._txtName = _txtName;
		this._intID = _intID;
		//this._intRowStatus = _intRowStatus;
		this._intRegionId = _intRegionId;
		this._txtRegionName = _txtRegionName;
		this._intBranchId = _intBranchId;
		this._txtBranchCode = _txtBranchCode;
		this._txtBranchName = _txtBranchName;
		this._intRayonId = _intRayonId;
		this._txtRayonCode = _txtRayonCode;
		this._txtRayonName = _txtRayonName;
		this._intChannelId = _intChannelId;
		this._intOutletId = _intOutletId;
		this._txtOutletCode = _txtOutletCode;
		this._txtOutletName = _txtOutletName;
		this._txtLatitude = _txtLatitude;
		this._txtLongitude = _txtLongitude;
	}
	public mEmployeeAreaData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
