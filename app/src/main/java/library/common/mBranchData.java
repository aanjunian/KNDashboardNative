package library.common;

/**
 * Created by ASUS ZE on 20/09/2016.
 */
public class mBranchData {

    public mBranchData(String _uuId, String _txtBranchID, String _txtBranchName) {
        this._uuId = _uuId;
        this._txtBranchID = _txtBranchID;
        this._txtBranchName = _txtBranchName;
    }

    public mBranchData() {
        super();
        // TODO Auto-generated constructor stub
    }

    private String _uuId;
    private String _txtBranchID;
    private String _txtBranchName;
    private String _txtRegion;

    public String Property_uuId = "uuId";
    public String Property_txtBranchID = "txtBranchID";
    public String Property_txtBranchName = "txtBranchName";
    public String Property_txtRegion = "txtRegion";
    public String Property_All=Property_uuId+","+Property_txtBranchID+","+Property_txtBranchName+","+Property_txtRegion;

    public String get_txtBranchName() {
        return _txtBranchName;
    }

    public void set_txtBranchName(String _txtBranchName) {
        this._txtBranchName = _txtBranchName;
    }

    public String get_txtBranchID() {
        return _txtBranchID;
    }

    public void set_txtBranchID(String _txtBranchID) {
        this._txtBranchID = _txtBranchID;
    }

    public String get_uuId() {
        return _uuId;
    }

    public void set_uuId(String _uuId) {
        this._uuId = _uuId;
    }

    public String get_txtRegion() {
        return _txtRegion;
    }

    public void set_txtRegion(String _txtRegion) {
        this._txtRegion = _txtRegion;
    }
}
