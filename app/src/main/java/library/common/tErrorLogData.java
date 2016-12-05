package library.common;

public class tErrorLogData {
	public synchronized String get_txtDeviceId() {
		return _txtDeviceId;
	}
	public synchronized void set_txtDeviceId(String _txtDeviceId) {
		this._txtDeviceId = _txtDeviceId;
	}
	public synchronized String get_UserId() {
		return _UserId;
	}
	public synchronized void set_UserId(String _UserId) {
		this._UserId = _UserId;
	}
	public synchronized int get_intId() {
		return _intId;
	}
	public synchronized void set_intId(int _intId) {
		this._intId = _intId;
	}
	public synchronized String get_Warning() {
		return _Warning;
	}
	public synchronized void set_Warning(String _Warning) {
		this._Warning = _Warning;
	}
	public synchronized String get_dtDate() {
		return _dtDate;
	}
	public synchronized void set_dtDate(String _dtDate) {
		this._dtDate = _dtDate;
	}
	private int _intId;
	private String _UserId;
	private String _Warning;
	private String _dtDate;
	private String _txtDeviceId;
	
	public String Property_intId="intId";
	public String Property_UserId="UserId";
	public String Property_Warning="Warning";
	public String Property_dtDate="dtDate";
	public String Property_txtDeviceId="txtDeviceId";
	public String Property_ListOftErrorLogData = "ListOftErrorLogData";
	public String Property_All=Property_intId+","+Property_dtDate+","+Property_UserId+","+Property_Warning+","+Property_txtDeviceId;
	public tErrorLogData() {
		// TODO Auto-generated constructor stub
	}
	public tErrorLogData(int _intId, String _dtDate, String _UserId, String _Warning, String _txtDeviceId) {
		super();
		this._intId = _intId;
		this._Warning = _Warning;
		this._dtDate = _dtDate;
		this._UserId = _UserId;
		this._txtDeviceId = _txtDeviceId;
	}
}
