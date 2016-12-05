package library.common;

public class mUserRoleData {
	public synchronized String get_intId() {
		return _intId;
	}
	public synchronized void set_intId(String _intId) {
		this._intId = _intId;
	}
	public synchronized String get_txtUserId() {
		return _txtUserId;
	}
	public synchronized void set_txtUserId(String _txtUserId) {
		this._txtUserId = _txtUserId;
	}
	public synchronized String get_intRoleId() {
		return _intRoleId;
	}
	public synchronized void set_intRoleId(String _intRoleId) {
		this._intRoleId = _intRoleId;
	}
	public synchronized String get_txtRoleName() {
		return _txtRoleName;
	}
	public synchronized void set_txtRoleName(String _txtRoleName) {
		this._txtRoleName = _txtRoleName;
	}
	private String _intId;
	private String _txtUserId;
	private String _intRoleId;
	private String _txtRoleName;
	
	public String Property_intId="intId";
	public String Property_txtUserId="txtUserId";
	public String Property_intRoleId="intRoleId";
	public String Property_txtRoleName="txtRoleName";
	public String Property_All=Property_intId+","+Property_intRoleId+","+Property_txtUserId+
			","+Property_txtRoleName;
	public String Property_ListOfMuserRole="ListMuserRole";
}
