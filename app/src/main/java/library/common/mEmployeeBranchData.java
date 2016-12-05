package library.common;

public class mEmployeeBranchData {
	public synchronized String get_EmpId() {
		return _EmpId;
	}
	public synchronized void set_EmpId(String _EmpId) {
		this._EmpId = _EmpId;
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
	public synchronized String get_intID() {
		return _intID;
	}
	public synchronized void set_intID(String _intID) {
		this._intID = _intID;
	}
	private String _EmpId;
	private String _txtNIK;
	private String _txtName;
	private String _txtBranchCode;
	private String _txtBranchName;
	private String _intID;

	public String Property_EmpId="EmpId";
	public String Property_txtNIK="txtNIK";
	public String Property_txtName="txtName";
	public String Property_txtBranchCode="txtBranchCode";
	public String Property_txtBranchName="txtBranchName";
	public String Property_intID="intID";
	public String Property_ListOfEmployeeBranchData="ListOfEmployeeBranchData";
	public String Property_ALL=Property_EmpId+","+Property_intID+","+Property_txtBranchCode
			+","+Property_txtBranchName+","+Property_txtName+","+Property_txtNIK;
	public mEmployeeBranchData(String _EmpId,String _intID,String _txtBranchCode, 
			String _txtBranchName,	String _txtName, String _txtNIK) {
		this._EmpId = _EmpId;
		this._txtNIK = _txtNIK;
		this._txtName = _txtName;
		this._txtBranchCode = _txtBranchCode;
		this._txtBranchName = _txtBranchName;
		this._intID = _intID;
	}
	public mEmployeeBranchData() {
		// TODO Auto-generated constructor stub
	}
}
