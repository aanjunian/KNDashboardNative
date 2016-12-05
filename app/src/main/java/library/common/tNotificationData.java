package library.common;

public class tNotificationData {
	
	public synchronized String get_guiID() {
		return _guiID;
	}
	public synchronized void set_guiID(String _guiID) {
		this._guiID = _guiID;
	}
	public synchronized String get_intIndex() {
		return _intIndex;
	}
	public synchronized void set_intIndex(String _intIndex) {
		this._intIndex = _intIndex;
	}
	
	public synchronized String get_tPublishStart() {
		return _tPublishStart;
	}
	public synchronized void set_tPublishStart(String _tPublishStart) {
		this._tPublishStart = _tPublishStart;
	}
	
	public synchronized String get_dtPublishEnd() {
		return _dtPublishEnd;
	}
	public synchronized void set_dtPublishEnd(String _dtPublishEnd) {
		this._dtPublishEnd = _dtPublishEnd;
	}
	
	public synchronized String get_bitActive() {
		return _bitActive;
	}
	public synchronized void set_bitActive(String _bitActive) {
		this._bitActive = _bitActive;
	}
	
	public synchronized String get_txtTitle() {
		return _txtTitle;
	}
	public synchronized void set_txtTitle(String _txtTitle) {
		this._txtTitle = _txtTitle;
	}
	public synchronized String get_txtDescription() {
		return _txtDescription;
	}
	public synchronized void set_txtDescription(String _txtDescription) {
		this._txtDescription = _txtDescription;
	}
	public synchronized String get_txtImage() {
		return _txtImage;
	}
	public synchronized void set_txtImage(String _txtImage) {
		this._txtImage = _txtImage;
	}
	public synchronized String get_txtLink() {
		return _txtLink;
	}
	public synchronized void set_txtLink(String _txtLink) {
		this._txtLink = _txtLink;
	}
	public synchronized String get_txtOutlet() {
		return _txtOutlet;
	}
	public synchronized void set_txtOutlet(String _txtOutlet) {
		this._txtOutlet = _txtOutlet;
	}
	public synchronized String get_txtOutletName() {
		return _txtOutletName;
	}
	public synchronized void set_txtOutletName(String _txtOutletName) {
		this._txtOutletName = _txtOutletName;
	}
	public synchronized String get_txtBranchCode() {
		return _txtBranchCode;
	}
	public synchronized void set_txtBranchCode(String _txtBranchCode) {
		this._txtBranchCode = _txtBranchCode;
	}
	public synchronized String get_txtStatus() {
		return _txtStatus;
	}
	public synchronized void set_txtStatus(String _txtStatus) {
		this._txtStatus = _txtStatus;
	}
	public synchronized String get_dtUpdate() {
		return _dtUpdate;
	}
	public synchronized void set_dtUpdate(String _dtUpdate) {
		this._dtUpdate = _dtUpdate;
	}
	public synchronized String get_intSubmit() {
		return _intSubmit;
	}
	public synchronized void set_intSubmit(String _intSubmit) {
		this._intSubmit = _intSubmit;
	}
	public synchronized String get_intSync() {
		return _intSync;
	}
	public synchronized void set_intSync(String _intSync) {
		this._intSync = _intSync;
	}
	public String get_intPriority() {
		return _intPriority;
	}
	public void set_intPriority(String _intPriority) {
		this._intPriority = _intPriority;
	}
	
	private String _guiID;
	private String _intIndex;
	private String _tPublishStart;
	private String _dtPublishEnd;
	private String _bitActive;
	private String _txtTitle;
	private String _txtDescription;
	private String _txtImage;
	private String _txtLink;
	private String _txtOutlet;
	private String _txtOutletName;
	private String _txtBranchCode;
	private String _intPriority;
	private String _txtStatus;
	private String _dtUpdate;
	private String _intSubmit;
	private String _intSync;
	
	public String Property_guiID="guiID";
	public String Property_intIndex="intIndex";
	public String Property_tPublishStart="tPublishStart";
	public String Property_dtPublishEnd="dtPublishEnd";
	public String Property_bitActive="bitActive";
	public String Property_txtTitle="txtTitle";
	public String Property_txtDescription="txtDescription";
	public String Property_txtImage="txtImage";
	public String Property_txtLink="txtLink";
	public String Property_txtOutlet="txtOutlet";
	public String Property_txtOutletName="txtOutletName";
	public String Property_txtBranchCode="txtBranchCode";
	public String Property_intPriority="intPriority";
	public String Property_txtStatus="txtStatus";
	public String Property_dtUpdate="dtUpdate";
	public String Property_intSubmit="intSubmit";
	public String Property_intSync="intSync";
	
	public String PropertyAll=Property_intIndex+","+Property_guiID+","+Property_tPublishStart+","+Property_dtPublishEnd+","+Property_bitActive+","+
			Property_txtTitle+","+Property_txtDescription+","+Property_txtImage+","+Property_txtLink+","+Property_txtOutlet+","+Property_txtOutletName+","+Property_txtBranchCode
			+","+Property_txtStatus+","+Property_dtUpdate
			+","+Property_intSubmit+","+Property_intSync+","+Property_intPriority;

}
