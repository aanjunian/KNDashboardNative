package library.common;
public class tSalesProductDetailData {
	public synchronized String get_intActive() {
		return _intActive;
	}
	public synchronized void set_intActive(String _intActive) {
		this._intActive = _intActive;
	}
	public synchronized String get_txtNoSo() {
		return _txtNoSo;
	}
	public synchronized void set_txtNoSo(String _txtNoSo) {
		this._txtNoSo = _txtNoSo;
	}
	public synchronized String get_intTotal() {
		return _intTotal;
	}
	public synchronized void set_intTotal(String _intTotal) {
		this._intTotal = _intTotal;
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
	public synchronized String get_txtCodeProduct() {
		return _txtCodeProduct;
	}
	public synchronized void set_txtCodeProduct(String _txtCodeProduct) {
		this._txtCodeProduct = _txtCodeProduct;
	}
	public synchronized String get_txtNameProduct() {
		return _txtNameProduct;
	}
	public synchronized void set_txtNameProduct(String _txtNameProduct) {
		this._txtNameProduct = _txtNameProduct;
	}
	public synchronized String get_intQty() {
		return _intQty;
	}
	public synchronized void set_intQty(String _intQty) {
		this._intQty = _intQty;
	}
	public synchronized String get_intPrice() {
		return _intPrice;
	}
	public synchronized void set_intPrice(String _intPrice) {
		this._intPrice = _intPrice;
	}
	private String _intId;
	private String _txtNIK;
	private String _txtKeterangan;
	private String _dtDate;
	private String _txtCodeProduct;
	private String _txtNameProduct;
	private String _intQty;
	private String _intPrice;
	private String _intTotal;
	private String _txtNoSo;
	private String _intActive;
	
	public String Property_intId="intId";
	public String Property_intTotal="intTotal";
	public String Property_txtNIK="txtNIK";
	public String Property_txtKeterangan="txtKeterangan";
	public String Property_dtDate="dtDate";
	public String Property_txtCodeProduct="txtCodeProduct";
	public String Property_txtNameProduct="txtNameProduct";
	public String Property_intQty="intQty";
	public String Property_txtNoSo="txtNoSo";
	public String Property_intPrice="intPrice";
	public String Property_intActive="intActive";
	public String Property_ListOftSalesProductDetailData="ListOftSalesProductDetailData";
	
	public String Property_All=Property_dtDate +","+
			Property_intId +","+
			Property_intPrice +","+
			Property_intQty +","+
			Property_txtCodeProduct +","+
			Property_txtKeterangan +","+
			Property_txtNameProduct +","+
			Property_intTotal +","+
			Property_txtNIK+","+
			Property_txtNoSo+","+
			Property_intActive;
	
	public tSalesProductDetailData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public tSalesProductDetailData(String _intId, String _dtDate,
			String _intPrice, String _intQty, String _txtCodeProduct, String _txtKeterangan,
			String _txtNameProduct, String _txtNIK, String _intTotal, String _txtNoSo,String _intActive) {
		this._intId = _intId;
		this._txtNIK = _txtNIK;
		this._txtKeterangan = _txtKeterangan;
		this._dtDate = _dtDate;
		this._txtCodeProduct = _txtCodeProduct;
		this._txtNameProduct = _txtNameProduct;
		this._intQty = _intQty;
		this._intPrice = _intPrice;
		this._intTotal = _intTotal;
		this._txtNoSo = _txtNoSo;
		this._intActive = _intActive;
	}
}
