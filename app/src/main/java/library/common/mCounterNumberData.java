package library.common;

public class mCounterNumberData {
	public synchronized int get_intId() {
		return _intId;
	}
	public synchronized void set_intId(int _intId) {
		this._intId = _intId;
	}
	public synchronized String get_txtName() {
		return _txtName;
	}
	public synchronized void set_txtName(String _txtName) {
		this._txtName = _txtName;
	}
	public synchronized String get_txtValue() {
		return _txtValue;
	}
	public synchronized void set_txtValue(String _txtValue) {
		this._txtValue = _txtValue;
	}
	public synchronized String get_txtDeskripsi() {
		return _txtDeskripsi;
	}
	public synchronized void set_txtDeskripsi(String _txtDeskripsi) {
		this._txtDeskripsi = _txtDeskripsi;
	}

	public mCounterNumberData() {
		// TODO Auto-generated constructor stub
	}
	public mCounterNumberData(int _intId, String _txtDeskripsi, String _txtName, String _txtValue) {
		this._intId = _intId;
		this._txtName = _txtName;
		this._txtValue = _txtValue;
		this._txtDeskripsi = _txtDeskripsi;
	}
	private int _intId;
	private String _txtName;
	private String _txtValue;
	private String _txtDeskripsi;
	public String Property_intId="intId";
	public String Property_txtName="txtName";
	public String Property_txtValue="txtValue";
	public String Property_txtDeskripsi="txtDeskripsi";
	public String Property_ListOfmCounterNumber="ListOfmCounterNumber";
	public String Property_All=Property_intId + "," +
			Property_txtDeskripsi + "," +
			Property_txtName + "," +
			Property_txtValue ;
}
