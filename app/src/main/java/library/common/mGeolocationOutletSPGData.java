package library.common;

public class mGeolocationOutletSPGData {
	public synchronized String get_intId() {
		return _intId;
	}
	public synchronized void set_intId(String _intId) {
		this._intId = _intId;
	}
	public synchronized String get_txtBranchCode() {
		return _txtBranchCode;
	}
	public synchronized void set_txtBranchCode(String _txtBranchCode) {
		this._txtBranchCode = _txtBranchCode;
	}
	public synchronized String get_txtOutletCode() {
		return _txtOutletCode;
	}
	public synchronized void set_txtOutletCode(String _txtOutletCode) {
		this._txtOutletCode = _txtOutletCode;
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
	private String _intId;
	private String _txtBranchCode;
	private String _txtOutletCode;
	private String _txtLongitude;
	private String _txtLatitude;
	private String _txtAcc;
	
	public String Property_IntId="intId";
	public String Property_txtBranchCode="txtBranchCode";
	public String Property_txtOutletCode="txtOutletCode";
	public String Property_txtLongitude="txtLongitude";
	public String Property_txtLatitude="txtLatitude";
	public String Property_txtAcc="txtAcc";
	public String Property_ListOfmGeolocationOutletSPG="ListOfmGeolocationOutletSPG";
	public String Property_All=Property_IntId+","+Property_txtAcc+","+Property_txtBranchCode
			+","+Property_txtLatitude+","+Property_txtLongitude+","+Property_txtOutletCode
			;
}
