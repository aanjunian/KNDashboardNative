package library.common;

/**
 * Created by ASUS ZE on 08/11/2016.
 */

public class mRegionData {
    public mRegionData(String _uuId, String _txtRegionID, String _txtRegionName) {
        this._uuId = _uuId;
        this._txtRegionID = _txtRegionID;
        this._txtRegionName = _txtRegionName;
    }

    public mRegionData() {
        super();
        // TODO Auto-generated constructor stub
    }

    private String _uuId;
    private String _txtRegionID;
    private String _txtRegionName;

    public String Property_uuId = "uuId";
    public String Property_txtRegionID = "txtRegionID";
    public String Property_txtRegionName = "txtRegionName";
    public String Property_All=Property_uuId+","+Property_txtRegionID+","+Property_txtRegionName;

    public String get_txtRegionName() {
        return _txtRegionName;
    }

    public void set_txtRegionName(String _txtBranchName) {
        this._txtRegionName = _txtBranchName;
    }

    public String get_txtRegionID() {
        return _txtRegionID;
    }

    public void set_txtRegionID(String _txtBranchID) {
        this._txtRegionID = _txtBranchID;
    }

    public String get_uuId() {
        return _uuId;
    }

    public void set_uuId(String _uuId) {
        this._uuId = _uuId;
    }
}
