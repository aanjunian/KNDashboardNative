package library.common;

public class mNotificationData {
	public synchronized String get_txtLinkImage() {
		return _txtLinkImage;
	}
	public synchronized void set_txtLinkImage(String _txtLinkImage) {
		this._txtLinkImage = _txtLinkImage;
	}
	public synchronized String get_intSuccessDLFile() {
		return _intSuccessDLFile;
	}
	public synchronized void set_intSuccessDLFile(String _intSuccessDLFile) {
		this._intSuccessDLFile = _intSuccessDLFile;
	}
	public synchronized String get_dtUpdated() {
		return _dtUpdated;
	}
	public synchronized void set_dtUpdated(String _dtUpdated) {
		this._dtUpdated = _dtUpdated;
	}
	public synchronized String get_intSync() {
		return _intSync;
	}
	public synchronized void set_intSync(String _intSync) {
		this._intSync = _intSync;
	}
	public synchronized String get_txtDataId() {
		return _txtDataId;
	}
	public synchronized void set_txtDataId(String _txtDataId) {
		this._txtDataId = _txtDataId;
	}
	public synchronized String get_txtNotifId() {
		return _txtNotifId;
	}
	public synchronized void set_txtNotifId(String _txtNotifId) {
		this._txtNotifId = _txtNotifId;
	}
	public synchronized String get_dtPublishStart() {
		return _dtPublishStart;
	}
	public synchronized void set_dtPublishStart(String _dtPublishStart) {
		this._dtPublishStart = _dtPublishStart;
	}
	public synchronized String get_dtPublishEnd() {
		return _dtPublishEnd;
	}
	public synchronized void set_dtPublishEnd(String _dtPublishEnd) {
		this._dtPublishEnd = _dtPublishEnd;
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
	public synchronized String get_txtUserID() {
		return _txtUserID;
	}
	public synchronized void set_txtUserID(String _txtUserID) {
		this._txtUserID = _txtUserID;
	}
	public synchronized String get_txtStatus() {
		return _txtStatus;
	}
	public synchronized void set_txtStatus(String _txtStatus) {
		this._txtStatus = _txtStatus;
	}
	public synchronized String get_dtStatus() {
		return _dtStatus;
	}
	public synchronized void set_dtStatus(String _dtStatus) {
		this._dtStatus = _dtStatus;
	}
	private String _txtDataId;
	private String _txtNotifId;
	private String _dtPublishStart;
	private String _dtPublishEnd;
	private String _txtTitle;
	private String _txtDescription;
	private String _txtImage;
	private String _txtLinkImage;
	private String _txtUserID;
	private String _txtStatus;
	private String _dtStatus;
	private String _intSync;
	private String _dtUpdated;
	private String _intSuccessDLFile;
	
	public String Property_txtDataId="txtDataId";
	public String Property_txtNotifId="txtNotifId";
	public String Property_dtPublishStart="dtPublishStart";
	public String Property_dtPublishEnd="dtPublishEnd";
	public String Property_txtTitle="txtTitle";
	public String Property_txtDescription="txtDescription";
	public String Property_txtImage="txtImage";
	public String Property_txtUserID="txtUserID";
	public String Property_txtLinkImage="TxtLinkImage";
	public String Property_txtStatus="txtStatus";
	public String Property_dtStatus="dtStatus";
	public String Property_intSync="intSync";
	public String Property_intSuccessDLFile="intSuccessDLFile";
	public String Property_dtUpdated="dtUpdated";
	
	public String Property_ListOfmNotificationData="ListOfmNotificationData";
	public String Property_All=Property_txtDataId +","+ Property_dtPublishEnd 
			+","+ Property_dtPublishStart 
			+","+ Property_dtStatus
			+","+ Property_intSync
			+","+ Property_txtDescription
			+","+ Property_txtImage
			+","+ Property_txtNotifId
			+","+ Property_txtStatus
			+","+ Property_txtTitle
			+","+ Property_txtUserID+","+ Property_dtUpdated+","+Property_intSuccessDLFile;
	
	public mNotificationData(String _txtDataId, String _txtNotifId,
			String _dtPublishStart, String _dtPublishEnd, String _txtTitle,
			String _txtDescription, String _txtImage, String _txtUserID,
			String _txtStatus, String _dtStatus) {
		this._txtDataId = _txtDataId;
		this._txtNotifId = _txtNotifId;
		this._dtPublishStart = _dtPublishStart;
		this._dtPublishEnd = _dtPublishEnd;
		this._txtTitle = _txtTitle;
		this._txtDescription = _txtDescription;
		this._txtImage = _txtImage;
		this._txtUserID = _txtUserID;
		this._txtStatus = _txtStatus;
		this._dtStatus = _dtStatus;
	}
	public mNotificationData() {
		// TODO Auto-generated constructor stub
	}
}
