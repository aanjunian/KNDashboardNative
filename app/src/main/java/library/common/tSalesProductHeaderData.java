package library.common;

public class tSalesProductHeaderData {
	public synchronized String get_intIdAbsenUser() {
		return _intIdAbsenUser;
	}
	public synchronized void set_intIdAbsenUser(String _intIdAbsenUser) {
		this._intIdAbsenUser = _intIdAbsenUser;
	}
	public synchronized String get_intSync() {
		return _intSync;
	}
	public synchronized void set_intSync(String _intSync) {
		this._intSync = _intSync;
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
	public synchronized String get_intSubmit() {
		return _intSubmit;
	}
	public synchronized void set_intSubmit(String _intSubmit) {
		this._intSubmit = _intSubmit;
	}
	public synchronized String get_intSumItem() {
		return _intSumItem;
	}
	public synchronized void set_intSumItem(String _intSumItem) {
		this._intSumItem = _intSumItem;
	}
	public synchronized String get_intSumAmount() {
		return _intSumAmount;
	}
	public synchronized void set_intSumAmount(String _intSumAmount) {
		this._intSumAmount = _intSumAmount;
	}
	public synchronized String get_UserId() {
		return _UserId;
	}
	public synchronized void set_UserId(String _UserId) {
		this._UserId = _UserId;
	}
	public synchronized String get_intId() {
		return _intId;
	}
	public synchronized void set_intId(String _intId) {
		this._intId = _intId;
	}
	public synchronized String get_txtNIK() {
		return _txtNIK;
	}
	public synchronized void set_txtNIK(String _txtNIK) {
		this._txtNIK = _txtNIK;
	}
	public synchronized String get_txtKeterangan() {
		return _txtKeterangan;
	}
	public synchronized void set_txtKeterangan(String _txtKeterangan) {
		this._txtKeterangan = _txtKeterangan;
	}
	public synchronized String get_dtDate() {
		return _dtDate;
	}
	public synchronized void set_dtDate(String _dtDate) {
		this._dtDate = _dtDate;
	}
	public synchronized String get_OutletCode() {
		return _OutletCode;
	}
	public synchronized void set_OutletCode(String _OutletCode) {
		this._OutletCode = _OutletCode;
	}
	public synchronized String get_OutletName() {
		return _OutletName;
	}
	public synchronized void set_OutletName(String _OutletName) {
		this._OutletName = _OutletName;
	}
	private String _intId;
	private String _txtNIK;
	private String _txtKeterangan;
	private String _dtDate;
	private String _OutletCode;
	private String _OutletName;
	private String _intSumItem;
	private String _intSumAmount;
	private String _UserId;
	private String _intSubmit;
	private String _intSync;
	private String _txtBranchCode;
	private String _txtBranchName;
	private String _intIdAbsenUser;

	public String Property_UserId="UserId";
	public String Property_intSumItem="intSumItem";
	public String Property_intSumAmount="intSumAmount";
	public String Property_intId="intId";
	public String Property_txtNIK="txtNIK";
	public String Property_txtKeterangan="txtKeterangan";
	public String Property_txtDate="dtDate";
	public String Property_OutletCode="OutletCode";
	public String Property_OutletName="OutletName";
	public String Property_intSubmit="intSubmit";
	public String Property_intSync="intSync";
	public String Property_txtBranchCode="txtBranchCode";
	public String Property_txtBranchName="txtBranchName";
	public String Property_intIdAbsenUser="intIdAbsenUser";
	public String Property_ListOftSalesProductHeaderData="ListOftSalesProductHeaderData";
	
	public tSalesProductHeaderData() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public String Property_All=Property_intId +","+
			Property_OutletCode +","+
			Property_OutletName +","+
			Property_txtDate +","+
			Property_txtKeterangan +","+
			Property_txtNIK +","+
			Property_intSumAmount +","+
			Property_intSumItem +","+
			Property_UserId +","+
			Property_intSubmit +","+
			Property_intSync +","+
			Property_txtBranchCode +","+
			Property_txtBranchName+","+Property_intIdAbsenUser
			;
}
