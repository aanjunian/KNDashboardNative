package library.common;

public class mEmployeeSalesProductData {
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
	public synchronized String get_txtName() {
		return _txtName;
	}
	public synchronized void set_txtName(String _txtName) {
		this._txtName = _txtName;
	}
	public synchronized String get_txtBrandDetailGramCode() {
		return _txtBrandDetailGramCode;
	}
	public synchronized void set_txtBrandDetailGramCode(
			String _txtBrandDetailGramCode) {
		this._txtBrandDetailGramCode = _txtBrandDetailGramCode;
	}
	public synchronized String get_txtProductBrandDetailGramName() {
		return _txtProductBrandDetailGramName;
	}
	public synchronized void set_txtProductBrandDetailGramName(
			String _txtProductBrandDetailGramName) {
		this._txtProductBrandDetailGramName = _txtProductBrandDetailGramName;
	}
	public synchronized String get_decHJD() {
		return _decHJD;
	}
	public synchronized void set_decHJD(String _decHJD) {
		this._decHJD = _decHJD;
	}
	public synchronized String get_decBobot() {
		return _decBobot;
	}
	public synchronized void set_decBobot(String _decBobot) {
		this._decBobot = _decBobot;
	}
	
	public mEmployeeSalesProductData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public mEmployeeSalesProductData(String _txtNIK, String _txtName,
			String _txtBrandDetailGramCode,
			String _txtProductBrandDetailGramName, String _decHJD,
			String _decBobot) {
		this._txtNIK = _txtNIK;
		this._txtName = _txtName;
		this._txtBrandDetailGramCode = _txtBrandDetailGramCode;
		this._txtProductBrandDetailGramName = _txtProductBrandDetailGramName;
		this._decHJD = _decHJD;
		this._decBobot = _decBobot;
	}
	
	private String _intId;
	private String _txtNIK;
	private String _txtName;
	private String _txtBrandDetailGramCode;
	private String _txtProductBrandDetailGramName;
	private String _decHJD;
	private String _decBobot;
	
	public String Property_txtNIK="txtNIK";
	public String Property_intId="intId";
	public String Property_txtName="txtName";
	public String Property_txtBrandDetailGramCode="txtBrandDetailGramCode";
	public String Property_txtProductBrandDetailGramName="txtProductBrandDetailGramName";
	public String Property_decHJD="decHJD";
	public String Property_decBobot="decBobot";
	public String Property_ListOfmEmployeeSalesProductData="ListOfmEmployeeSalesProductData";
	public String Property_All= Property_intId +","+ Property_decBobot +","+Property_decHJD+","+Property_txtBrandDetailGramCode+","+
	Property_txtName+","+Property_txtNIK+","+Property_txtProductBrandDetailGramName;
}
