package library.common;

public class mAbsenCheckInData {
	public synchronized String get_IntId() {
		return _IntId;
	}
	public synchronized void set_IntId(String _IntId) {
		this._IntId = _IntId;
	}
	public synchronized String get_dtDateCheckIn() {
		return _dtDateCheckIn;
	}
	public synchronized void set_dtDateCheckIn(String _dtDateCheckIn) {
		this._dtDateCheckIn = _dtDateCheckIn;
	}
	public synchronized String get_dtDateCheckOut() {
		return _dtDateCheckOut;
	}
	public synchronized void set_dtDateCheckOut(String _dtDateCheckOut) {
		this._dtDateCheckOut = _dtDateCheckOut;
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
	public synchronized String get_txtNik() {
		return _txtNik;
	}
	public synchronized void set_txtNik(String _txtNik) {
		this._txtNik = _txtNik;
	}
	public synchronized String get_txtUserId() {
		return _txtUserId;
	}
	public synchronized void set_txtUserId(String _txtUserId) {
		this._txtUserId = _txtUserId;
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
	public synchronized String get_txtDeviceId() {
		return _txtDeviceId;
	}
	public synchronized void set_txtDeviceId(String _txtDeviceId) {
		this._txtDeviceId = _txtDeviceId;
	}
	private String _IntId;
	private String _txtBranchCode;
	private String _txtOutletCode;
	private String _txtNik;
	private String _txtUserId;
	private String _txtLongitude;
	private String _txtLatitude;
	private String _txtAcc;
	private String _txtDeviceId;
	private String _dtDateCheckIn;
	private String _dtDateCheckOut;

	public String Property_IntId="IntId";
	public String Property_txtBranchCode="txtBranchCode";
	public String Property_txtOutletCode="txtOutletCode";
	public String Property_txtNik="txtNik";
	public String Property_txtUserId="txtUserId";
	public String Property_txtLongitude="txtLongitude";
	public String Property_txtLatitude="txtLatitude";
	public String Property_txtAcc="txtAcc";
	public String Property_txtDeviceId="txtDeviceId";
	public String Property_dtDateCheckIn="dtDateCheckIn";
	public String Property_dtDateCheckOut="dtDateCheckOut";
	public String Property_All=Property_dtDateCheckIn+","+Property_dtDateCheckOut+","+Property_IntId+","+Property_txtAcc
			+","+Property_txtBranchCode+","+Property_txtDeviceId+","+Property_txtLatitude+","+Property_txtLongitude+","+Property_txtNik
			+","+Property_txtOutletCode+","+Property_txtUserId
			;
}
