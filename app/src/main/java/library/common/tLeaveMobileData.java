package library.common;

public class tLeaveMobileData {

	public synchronized String get_txtTypeAlasanName() {
		return _txtTypeAlasanName;
	}
	public synchronized void set_txtTypeAlasanName(String _txtTypeAlasanName) {
		this._txtTypeAlasanName = _txtTypeAlasanName;
	}
	public synchronized String get_intLeaveIdSync() {
		return _intLeaveIdSync;
	}
	public synchronized void set_intLeaveIdSync(String _intLeaveIdSync) {
		this._intLeaveIdSync = _intLeaveIdSync;
	}
	public synchronized String get_intSubmit() {
		return _intSubmit;
	}
	public synchronized void set_intSubmit(String _intSubmit) {
		this._intSubmit = _intSubmit;
	}
	public synchronized String get_intLeaveId() {
		return _intLeaveId;
	}
	public synchronized void set_intLeaveId(String _intLeaveId) {
		this._intLeaveId = _intLeaveId;
	}
	public synchronized String get_txtAlasan() {
		return _txtAlasan;
	}
	public synchronized void set_txtAlasan(String _txtAlasan) {
		this._txtAlasan = _txtAlasan;
	}
	public synchronized String get_txtTypeAlasan() {
		return _txtTypeAlasan;
	}
	public synchronized void set_txtTypeAlasan(String _txtTypeAlasan) {
		this._txtTypeAlasan = _txtTypeAlasan;
	}
	public synchronized String get_dtLeave() {
		return _dtLeave;
	}
	public synchronized void set_dtLeave(String _dtLeave) {
		this._dtLeave = _dtLeave;
	}
	public synchronized String get_txtUserId() {
		return _txtUserId;
	}
	public synchronized void set_txtUserId(String _txtUserId) {
		this._txtUserId = _txtUserId;
	}
	public synchronized String get_txtDeviceId() {
		return _txtDeviceId;
	}
	public synchronized void set_txtDeviceId(String _txtDeviceId) {
		this._txtDeviceId = _txtDeviceId;
	}
	private String _intLeaveId;
	private String _intLeaveIdSync;
	private String _intSubmit;
	private String _txtAlasan;
	private String _txtTypeAlasan;
	private String _txtTypeAlasanName;
	private String _dtLeave;
	private String _txtUserId;
	private String _txtDeviceId;
	
	public String Property_txtTypeAlasanName="txtTypeAlasanName";
	public String Property_intSubmit="intSubmit";
	public String Property_intLeaveIdSync="intLeaveIdSync";
	public String Property_intLeaveId="intLeaveId";
	public String Property_txtAlasan="txtAlasan";
	public String Property_txtTypeAlasan="txtTypeAlasan";
	public String Property_dtLeave="dtLeave";
	public String Property_txtUserId="txtUserId";
	public String Property_txtDeviceId="txtDeviceId";
	public String PropertyListOftLeaveMobileData="ListOftLeaveMobileData";
	public String PropertyAll=Property_dtLeave+","+Property_intLeaveId+","+Property_intLeaveIdSync+","+Property_intSubmit+","+
			Property_txtAlasan+","+Property_txtDeviceId+","+Property_txtTypeAlasan+","+Property_txtUserId+","+Property_txtTypeAlasanName;
}
