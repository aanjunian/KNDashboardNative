package library.common;

public class tCustomerBasedMobileDetailData {
	private String _intTrCustomerIdDetail;
	private String _intTrCustomerId;
	private String _txtNamaDepan;
	private String _intNo;
	private String _intPIC;
	private String _bitActive;
	private String _dtInserted;
	private String _dtUpdated;
	private String _txtInsertedBy;
	private String _txtUpdatedBy;
	
	public String Property_intTrCustomerIdDetail ="intTrCustomerIdDetail";
	public String Property_intTrCustomerId = "intTrCustomerId";
	public String Property_txtNamaDepan = "txtNamaDepan";
	public String Property_intNo="intNo";
	public String Property_intPIC="intPIC";
	public String Property_bitActive="bitActive";
	public String Property_dtInserted="dtInserted";
	public String Property_dtUpdated="dtUpdated";
	public String Property_txtInsertedBy="txtInsertedBy";
	public String Property_txtUpdatedBy="txtUpdatedBy";
	
	public String Property_ListOftCustomerBasedMobileDetailData="ListOftCustomerBasedMobileDetailData";
	public String Property_ALL=Property_intTrCustomerIdDetail + "," + 
							   Property_intTrCustomerId + "," +
							   Property_txtNamaDepan + "," + 
							   Property_intNo + "," + 
							   Property_intPIC + "," + 
							   Property_bitActive + "," + 
							   Property_dtInserted + "," + 
							   Property_dtUpdated + "," + 
							   Property_txtInsertedBy + "," + 
							   Property_txtUpdatedBy;
	
	public String get_intTrCustomerIdDetail() {
		return _intTrCustomerIdDetail;
	}
	public void set_intTrCustomerIdDetail(String _intTrCustomerIdDetail) {
		this._intTrCustomerIdDetail = _intTrCustomerIdDetail;
	}
	public String get_intTrCustomerId() {
		return _intTrCustomerId;
	}
	public void set_intTrCustomerId(String _intTrCustomerId) {
		this._intTrCustomerId = _intTrCustomerId;
	}
	public String get_txtNamaDepan() {
		return _txtNamaDepan;
	}
	public void set_txtNamaDepan(String _txtNamaDepan) {
		this._txtNamaDepan = _txtNamaDepan;
	}
	public String get_intNo() {
		return _intNo;
	}
	public void set_intNo(String _intNo) {
		this._intNo = _intNo;
	}
	public String get_intPIC() {
		return _intPIC;
	}
	public void set_intPIC(String _intPIC) {
		this._intPIC = _intPIC;
	}
	public String get_bitActive() {
		return _bitActive;
	}
	public void set_bitActive(String _bitActive) {
		this._bitActive = _bitActive;
	}
	public String get_dtInserted() {
		return _dtInserted;
	}
	public void set_dtInserted(String _dtInserted) {
		this._dtInserted = _dtInserted;
	}
	public String get_dtUpdated() {
		return _dtUpdated;
	}
	public void set_dtUpdated(String _dtUpdated) {
		this._dtUpdated = _dtUpdated;
	}
	public String get_txtInsertedBy() {
		return _txtInsertedBy;
	}
	public void set_txtInsertedBy(String _txtInsertedBy) {
		this._txtInsertedBy = _txtInsertedBy;
	}
	public String get_txtUpdatedBy() {
		return _txtUpdatedBy;
	}
	public void set_txtUpdatedBy(String _txtUpdatedBy) {
		this._txtUpdatedBy = _txtUpdatedBy;
	}
}