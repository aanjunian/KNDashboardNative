package library.common;

import java.io.Serializable;

public class mProductBarcodeData implements Serializable {
	private String _intProductCode;
	private String _txtProductCode;
	private String _txtProductName;
	private String _txtBarcode;
	private String _intSubmit;
	private String _intSync;
	public String Property_intProductCode="intProductCode";
	public String Property_txtProductCode="txtProductCode";
	public String Property_txtProductName="txtProductName";
	public String Property_txtBarcode="txtBarcode";
	public String Property_intSubmit="intSubmit";
	public String Property_intSync="intSync";
	public String Property_ListOfmProductBarcodeData="ListOfmProductBarcodeData";
	public String PropertyAll=Property_intProductCode+","+Property_intSubmit+","+Property_intSync+","+Property_txtBarcode+","+
	Property_txtProductCode+","+Property_txtProductName;
	public String get_intProductCode() {
		return _intProductCode;
	}
	public void set_intProductCode(String _intProductCode) {
		this._intProductCode = _intProductCode;
	}
	public String get_txtProductCode() {
		return _txtProductCode;
	}
	public void set_txtProductCode(String _txtProductCode) {
		this._txtProductCode = _txtProductCode;
	}
	public String get_txtProductName() {
		return _txtProductName;
	}
	public void set_txtProductName(String _txtProductName) {
		this._txtProductName = _txtProductName;
	}
	public String get_txtBarcode() {
		return _txtBarcode;
	}
	public void set_txtBarcode(String _txtBarcode) {
		this._txtBarcode = _txtBarcode;
	}
	public String get_intSubmit() {
		return _intSubmit;
	}
	public void set_intSubmit(String _intSubmit) {
		this._intSubmit = _intSubmit;
	}
	public String get_intSync() {
		return _intSync;
	}
	public void set_intSync(String _intSync) {
		this._intSync = _intSync;
	}
	
}
