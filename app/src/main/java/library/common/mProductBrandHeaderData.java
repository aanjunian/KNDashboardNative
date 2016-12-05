package library.common;

public class mProductBrandHeaderData {
	public synchronized String get_intmProductUmbrandId() {
		return _intmProductUmbrandId;
	}
	public synchronized void set_intmProductUmbrandId(String _intmProductUmbrandId) {
		this._intmProductUmbrandId = _intmProductUmbrandId;
	}
	public synchronized String get_txtProductBrandCode() {
		return _txtProductBrandCode;
	}
	public synchronized void set_txtProductBrandCode(String _txtProductBrandCode) {
		this._txtProductBrandCode = _txtProductBrandCode;
	}
	public synchronized String get_txtProductBrandName() {
		return _txtProductBrandName;
	}
	public synchronized void set_txtProductBrandName(String _txtProductBrandName) {
		this._txtProductBrandName = _txtProductBrandName;
	}
	public synchronized String get_txtAliasName() {
		return _txtAliasName;
	}
	public synchronized void set_txtAliasName(String _txtAliasName) {
		this._txtAliasName = _txtAliasName;
	}
	private String _intmProductUmbrandId;
	private String _txtProductBrandCode;
	private String _txtProductBrandName;
	private String _txtAliasName;
	public mProductBrandHeaderData() {
		// TODO Auto-generated constructor stub
	}
	
	public String Property_intmProductUmbrandId="intmProductUmbrandId";
	public String Property_txtProductBrandCode="txtProductBrandCode";
	public String Property_txtProductBrandName="txtProductBrandName";
	public String Property_txtAliasName="txtAliasName";
	public String Property_ListOfmProductBrandHeader="ListOfmProductBrandHeader";
	public String Property_All=Property_intmProductUmbrandId+","+Property_txtAliasName+","+Property_txtProductBrandCode+","+
	Property_txtProductBrandName;
	
}
