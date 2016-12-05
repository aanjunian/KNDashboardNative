package library.common;

public class mTypeLeaveMobileData {
	public synchronized String get_intTipeLeave() {
		return _intTipeLeave;
	}
	public synchronized void set_intTipeLeave(String _intTipeLeave) {
		this._intTipeLeave = _intTipeLeave;
	}
	public synchronized String get_txtTipeLeaveName() {
		return _txtTipeLeaveName;
	}
	public synchronized void set_txtTipeLeaveName(String _txtTipeLeaveName) {
		this._txtTipeLeaveName = _txtTipeLeaveName;
	}
	private String _intTipeLeave;
	private String _txtTipeLeaveName;
	public mTypeLeaveMobileData() {
		// TODO Auto-generated constructor stub
	}
	public String Property_intTipeLeave="intTipeLeave";
	public String Property_txtTipeLeaveName="txtTipeLeaveName";
	public String PropertyListOfmTypeLeaveMobileData="ListOfmTypeLeaveMobileData";
	public String PropertyAll=Property_intTipeLeave+","+Property_txtTipeLeaveName;
}
