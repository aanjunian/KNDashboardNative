package library.common;

import java.io.Serializable;

public class clsSwipeList implements Serializable{
	
	private String _txtId;
	private String _txtTitle;
	private String _txtDescription;
	
	public String Property_txtId="txtId";
	public String Property_txtTitle="txtTitle";
	public String Property_txtDescription="txtDescription";
	
	public String PropertyAll=Property_txtId+","+Property_txtTitle+ "," + Property_txtDescription;

	public String get_txtId() {
		return _txtId;
	}

	public void set_txtId(String _txtId) {
		this._txtId = _txtId;
	}

	public String get_txtTitle() {
		return _txtTitle;
	}

	public void set_txtTitle(String _txtTitle) {
		this._txtTitle = _txtTitle;
	}

	public String get_txtDescription() {
		return _txtDescription;
	}

	public void set_txtDescription(String _txtDescription) {
		this._txtDescription = _txtDescription;
	}
	
}
