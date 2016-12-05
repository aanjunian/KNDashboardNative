package library.common;

public class tActivityData {
	public synchronized String get_intFlag() {
		return _intFlag;
	}
	public synchronized void set_intFlag(String _intFlag) {
		this._intFlag = _intFlag;
	}
	public synchronized String get_txtBranch() {
		return _txtBranch;
	}
	public synchronized void set_txtBranch(String _txtBranch) {
		this._txtBranch = _txtBranch;
	}
	public synchronized String get_txtUserId() {
		return _txtUserId;
	}
	public synchronized void set_txtUserId(String _txtUserId) {
		this._txtUserId = _txtUserId;
	}
	public synchronized String get_intSubmit() {
		return _intSubmit;
	}
	public synchronized void set_intSubmit(String _intSubmit) {
		this._intSubmit = _intSubmit;
	}
	public synchronized String get_txtDeviceId() {
		return _txtDeviceId;
	}
	public synchronized void set_txtDeviceId(String _txtDeviceId) {
		this._txtDeviceId = _txtDeviceId;
	}
	public synchronized String get_intId() {
		return _intId;
	}
	public synchronized void set_intId(String _intId) {
		this._intId = _intId;
	}
	public synchronized String get_intIdSyn() {
		return _intIdSyn;
	}
	public synchronized void set_intIdSyn(String _intIdSyn) {
		this._intIdSyn = _intIdSyn;
	}
	
	public tActivityData() {
		// TODO Auto-generated constructor stub
	}
	public tActivityData(String _intId, String _intIdSyn, String _txtDesc,
						 byte[] _txtImg1, byte[] _txtImg2, String _txtOutletCode,
			String _txtOutletName, String _intActive, String _dtActivity) {
		this._intId = _intId;
		this._intIdSyn = _intIdSyn;
		this._txtDesc = _txtDesc;
		this._txtImg1 = _txtImg1;
		this._txtImg2 = _txtImg2;
		this._txtOutletCode = _txtOutletCode;
		this._txtOutletName = _txtOutletName;
		this._intActive = _intActive;
		this._dtActivity = _dtActivity;
	}
	public synchronized String get_txtDesc() {
		return _txtDesc;
	}
	public synchronized void set_txtDesc(String _txtDesc) {
		this._txtDesc = _txtDesc;
	}
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
	public synchronized String get_intActive() {
		return _intActive;
	}
	public synchronized void set_intActive(String _intActive) {
		this._intActive = _intActive;
	}
	public synchronized String get_dtActivity() {
		return _dtActivity;
	}
	public synchronized void set_dtActivity(String _dtActivity) {
		this._dtActivity = _dtActivity;
	}
	private String _intId;
	private String _intIdSyn;
	private String _txtDesc;
	private byte[] _txtImg1;
	private byte[] _txtImg2;
	private String _txtOutletCode;
	private String _txtOutletName;
	private String _intActive;
	private String _dtActivity;
	private String _txtDeviceId;
	private String _intSubmit;
	private String _txtUserId;
	private String _intFlag;
	private String _txtBranch;
	
	public String Property_intFlag="intFlag";
	public String Property_txtBranch="txtBranch";
	public String Property_intId="intId";
	public String Property_intIdSyn="intIdSyn";
	public String Property_intSubmit="intSubmit";
	public String Property_txtDesc="txtDesc";
	public String Property_txtImg1="txtImg1";
	public String Property_txtImg2="txtImg2";
	public String Property_txtOutletCode="txtOutletCode";
	public String Property_txtDeviceId="txtDeviceId";
	public String Property_txtOutletName="txtOutletName";
	public String Property_intActive="intActive";
	public String Property_dtActivity="dtActivity";
	public String Property_txtUserId="txtUserId";
	public String Property_ListOfTActivity="ListOfTActivity";
	
	public String Property_All=Property_dtActivity+","+Property_intActive+","+Property_intId+","+Property_intIdSyn+","+Property_intSubmit+","+Property_txtDesc+","+
	Property_txtDeviceId+","+Property_txtImg1+","+Property_txtImg2+","+Property_txtOutletCode+","+Property_txtOutletName+","+
			Property_txtUserId+","+Property_intFlag+","+Property_txtBranch;
}
