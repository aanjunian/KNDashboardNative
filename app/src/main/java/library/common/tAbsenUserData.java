package library.common;

public class tAbsenUserData {
	public synchronized byte[] get_txtImg1() {
		return _txtImg1;
	}
	public synchronized void set_txtImg1(byte[] _txtImg1) {
		this._txtImg1 = _txtImg1;
	}
	public synchronized byte[] get_txtImg2() {
		return _txtImg2;
	}
	public synchronized void set_txtImg2(byte[] _txtImg2) {
		this._txtImg2 = _txtImg2;
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
	public synchronized String get_txtDeviceId() {
		return _txtDeviceId;
	}
	public synchronized void set_txtDeviceId(String _txtDeviceId) {
		this._txtDeviceId = _txtDeviceId;
	}
	public synchronized String get_intSync() {
		return _intSync;
	}
	public synchronized void set_intSync(String _intSync) {
		this._intSync = _intSync;
	}
	public synchronized String get_intId() {
		return _intId;
	}
	public synchronized void set_intId(String _intId) {
		this._intId = _intId;
	}
	public synchronized String get_txtAbsen() {
		return _txtAbsen;
	}
	public synchronized void set_txtAbsen(String _txtAbsen) {
		this._txtAbsen = _txtAbsen;
	}
	public synchronized String get_txtUserId() {
		return _txtUserId;
	}
	public synchronized void set_txtUserId(String _txtUserId) {
		this._txtUserId = _txtUserId;
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
	public synchronized String get_txtAccuracy() {
		return _txtAccuracy;
	}
	public synchronized void set_txtAccuracy(String _txtAccuracy) {
		this._txtAccuracy = _txtAccuracy;
	}
	public synchronized String get_intSubmit() {
		return _intSubmit;
	}
	public synchronized void set_intSubmit(String _intSubmit) {
		this._intSubmit = _intSubmit;
	}
	private String _intId;
	private String _txtAbsen;
	private String _txtUserId;
	private String _txtBranchCode;
	private String _txtBranchName;
	private String _txtOutletCode;
	private String _txtOutletName;
	private String _dtDateCheckIn;
	private String _dtDateCheckOut;
	private String _txtLongitude;
	private String _txtLatitude;
	private String _txtAccuracy;
	private String _intSubmit;
	private String _intSync;
	private String _txtDeviceId;
	private byte[] _txtImg1;
	private byte[] _txtImg2;

	public String Property_txtImg1="txtImg1";
	public String Property_txtImg2="txtImg2";
	public String Property_intId="intId";
	public String Property_txtDeviceId="txtDeviceId";
	public String Property_txtAbsen="txtAbsen";
	public String Property_txtUserId="txtUserId";
	public String Property_txtBranchCode="txtBranchCode";
	public String Property_txtBranchName="txtBranchName";
	public String Property_txtOutletCode="txtOutletCode";
	public String Property_txtOutletName="txtOutletName";
	public String Property_dtDateCheckIn="dtDateCheckIn";
	public String Property_dtDateCheckOut="dtDateCheckOut";
	public String Property_txtLongitude="txtLongitude";
	public String Property_txtLatitude="txtLatitude";
	public String Property_txtAccuracy="txtAccuracy";
	public String Property_intSubmit="intSubmit";
	public String Property_intSync="Sync";
	public String Property_ListOftAbsenUser="ListOftAbsenUser";
	public String Property_All=Property_intId+","+Property_dtDateCheckIn+","+Property_intSubmit+","+Property_intSync+","+Property_txtAbsen+","+
			Property_txtAccuracy+","+Property_txtBranchCode+","+Property_txtBranchName+","+Property_txtLatitude+","+Property_txtLongitude+","+
			Property_txtOutletCode+","+Property_txtOutletName+","+Property_txtDeviceId+","+Property_txtUserId+","+Property_dtDateCheckOut
			+","+Property_txtImg1+","+Property_txtImg2
			;
	public tAbsenUserData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public tAbsenUserData(String _intId, String _txtAbsen, String _txtUserId,
						  String _txtBranchCode, String _txtBranchName,
						  String _txtOutletCode, String _txtOutletName, String _dtDateCheckIn,
						  String _txtLongitude, String _txtLatitude, String _txtAccuracy,
						  String _intSubmit) {
		this._intId = _intId;
		this._txtAbsen = _txtAbsen;
		this._txtUserId = _txtUserId;
		this._txtBranchCode = _txtBranchCode;
		this._txtBranchName = _txtBranchName;
		this._txtOutletCode = _txtOutletCode;
		this._txtOutletName = _txtOutletName;
		this._dtDateCheckIn = _dtDateCheckIn;
		this._txtLongitude = _txtLongitude;
		this._txtLatitude = _txtLatitude;
		this._txtAccuracy = _txtAccuracy;
		this._intSubmit = _intSubmit;
	}

}