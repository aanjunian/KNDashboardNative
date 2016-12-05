package library.common;

public class mUserCabangData {
	public synchronized String get_intRoleId() {
		return _intRoleId;
	}
	public synchronized void set_intRoleId(String _intRoleId) {
		this._intRoleId = _intRoleId;
	}
	public synchronized String get_intId() {
		return _intId;
	}
	public synchronized void set_intId(String _intId) {
		this._intId = _intId;
	}
	public synchronized String get_txtNik() {
		return _txtNik;
	}
	public synchronized void set_txtNik(String _txtNik) {
		this._txtNik = _txtNik;
	}
	public synchronized String get_txtCabangId() {
		return _txtCabangId;
	}
	public synchronized void set_txtCabangId(String _txtCabangId) {
		this._txtCabangId = _txtCabangId;
	}
	public synchronized String get_txtCabangName() {
		return _txtCabangName;
	}
	public synchronized void set_txtCabangName(String _txtCabangName) {
		this._txtCabangName = _txtCabangName;
	}
	private String _intId;
	private String _txtNik;
	private String _txtCabangId;
	private String _txtCabangName;
	private String _intRoleId;
	public String Property_intId="intId";
	public String Property_txtNik="txtNik";
	public String Property_txtCabangId="txtCabangId";
	public String Property_txtCabangName="txtCabangName";
	public String Property_intRoleId="intRoleId";
	public String Property_All= Property_intId +","+Property_txtCabangId+","+
	Property_txtCabangName+","+Property_txtNik+","+Property_intRoleId;
	public String Property_ListOfMuserCabang="ListOfMuserCabang";
}
