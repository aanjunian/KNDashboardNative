package library.common;

public class tCheckinOutletMDData {
	public synchronized String get_intSync() {
		return _intSync;
	}
	public synchronized void set_intSync(String _intSync) {
		this._intSync = _intSync;
	}
	public synchronized String get_dtDate() {
		return _dtDate;
	}
	public synchronized void set_dtDate(String _dtDate) {
		this._dtDate = _dtDate;
	}
	public synchronized String get_txtDataId() {
		return _txtDataId;
	}
	public synchronized void set_txtDataId(String _txtDataId) {
		this._txtDataId = _txtDataId;
	}
	public synchronized String get_txtName() {
		return _txtName;
	}
	public synchronized void set_txtName(String _txtName) {
		this._txtName = _txtName;
	}
	public synchronized String get_txtAddress() {
		return _txtAddress;
	}
	public synchronized void set_txtAddress(String _txtAddress) {
		this._txtAddress = _txtAddress;
	}
	public synchronized String get_txtNoTelp() {
		return _txtNoTelp;
	}
	public synchronized void set_txtNoTelp(String _txtNoTelp) {
		this._txtNoTelp = _txtNoTelp;
	}
	public synchronized String get_txtLongitude() {
		return _txtLongitude;
	}
	public synchronized void set_txtLongitude(String _txtLongitude) {
		this._txtLongitude = _txtLongitude;
	}
	public synchronized String get_txtLatitude() {
		return _txtLatitude;
	}
	public synchronized void set_txtLatitude(String _txtLatitude) {
		this._txtLatitude = _txtLatitude;
	}
	public synchronized String get_txtAcc() {
		return _txtAcc;
	}
	public synchronized void set_txtAcc(String _txtAcc) {
		this._txtAcc = _txtAcc;
	}
	public synchronized String get_txtBranchCode() {
		return _txtBranchCode;
	}
	public synchronized void set_txtBranchCode(String _txtBranchCode) {
		this._txtBranchCode = _txtBranchCode;
	}
	public synchronized String get_txtRegion() {
		return _txtRegion;
	}
	public synchronized void set_txtRegion(String _txtRegion) {
		this._txtRegion = _txtRegion;
	}

	private String _txtDataId;
	private String _txtName;
	private String _txtAddress;
	private String _txtNoTelp;
	private String _txtLongitude;
	private String _txtLatitude;
	private String _txtAcc;
	private String _txtBranchCode;
	private String _txtRegion;
	private String _dtDate;
	private String _intSync;
	
	public String Property_txtDataId="txtDataId";
	public String Property_txtName="txtName";
	public String Property_txtAddress="txtAddress";
	public String Property_txtNoTelp="txtNoTelp";
	public String Property_txtLongitude="txtLongitude";
	public String Property_txtLatitude="txtLatitude";
	public String Property_txtAcc="txtAcc";
	public String Property_txtBranchCode="txtBranchCode";
	public String Property_txtRegion="txtRegion";
	public String Property_dtDate="dtDate";
	public String Property_intSync="intSync";
	public String Property_ListOftCheckinOutletMD="ListOftCheckinOutletMD";
	public String Property_ALL=Property_txtDataId+","+Property_txtName+","+Property_txtAddress+","+Property_txtNoTelp+","+
			Property_txtLongitude+","+Property_txtLatitude+","+Property_txtAcc+","+Property_txtBranchCode+","+Property_txtRegion+","+
			Property_dtDate+","+Property_intSync;
}
