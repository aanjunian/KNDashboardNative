package library.common;

public class mPriceInOutletData {
	private String _intIdItemPrice;
	private String _intProductCode;
	private String _txtProductName;
	private String _decPriceHJD;
	private String _txtOutletCode;
	private String _txtOutletName;
	private String _txtBranchCode;

	public String Property_intIdItemPrice="intIdItemPrice";
	public String Property_intProductCode="intProductCode";
	public String Property_txtProductName="txtProductName";
	public String Property_decPriceHJD="decPriceHJD";
	public String Property_txtOutletCode="txtOutletCode";
	public String Property_txtOutletName="txtOutletName";
	public String Property_txtBranchCode="txtBranchCode";
	public String Property_All=Property_decPriceHJD+","+Property_intIdItemPrice+","+
	Property_intProductCode+","+Property_txtBranchCode+","+Property_txtOutletCode+","+
			Property_txtOutletName+","+Property_txtProductName;
	public String get_intIdItemPrice() {
		return _intIdItemPrice;
	}
	public void set_intIdItemPrice(String _intIdItemPrice) {
		this._intIdItemPrice = _intIdItemPrice;
	}
	public String get_intProductCode() {
		return _intProductCode;
	}
	public void set_intProductCode(String _intProductCode) {
		this._intProductCode = _intProductCode;
	}
	public String get_txtProductName() {
		return _txtProductName;
	}
	public void set_txtProductName(String _txtProductName) {
		this._txtProductName = _txtProductName;
	}
	public String get_decPriceHJD() {
		return _decPriceHJD;
	}
	public void set_decPriceHJD(String _decPriceHJD) {
		this._decPriceHJD = _decPriceHJD;
	}
	public String get_txtOutletCode() {
		return _txtOutletCode;
	}
	public void set_txtOutletCode(String _txtOutletCode) {
		this._txtOutletCode = _txtOutletCode;
	}
	public String get_txtOutletName() {
		return _txtOutletName;
	}
	public void set_txtOutletName(String _txtOutletName) {
		this._txtOutletName = _txtOutletName;
	}
	public String get_txtBranchCode() {
		return _txtBranchCode;
	}
	public void set_txtBranchCode(String _txtBranchCode) {
		this._txtBranchCode = _txtBranchCode;
	}
	
}
