package library.common;

/**
 * Created by ASUS ZE on 08/11/2016.
 */

public class mBannerData {
    public mBannerData(String _uuId, String _IntBannerID, String _TxtFileName, String _TxtName, String _TxtPathActual, String _TxtPathThumbnail, String _txtDesc) {
        this._uuId = _uuId;
        this._IntBannerID = _IntBannerID;
        this._TxtFileName = _TxtFileName;
        this._TxtName = _TxtName;
        this._TxtPathActual = _TxtPathActual;
        this._TxtPathThumbnail = _TxtPathThumbnail;
        this._txtDesc = _txtDesc;


    }

    public mBannerData() {
        super();
        // TODO Auto-generated constructor stub
    }

    private String _uuId;

    public String get_IntBannerID() {
        return _IntBannerID;
    }

    public void set_IntBannerID(String _IntBannerID) {
        this._IntBannerID = _IntBannerID;
    }

    public String get_txtDesc() {
        return _txtDesc;
    }

    public void set_txtDesc(String _txtDesc) {
        this._txtDesc = _txtDesc;
    }

    public String get_TxtFileName() {
        return _TxtFileName;
    }

    public void set_TxtFileName(String _TxtFileName) {
        this._TxtFileName = _TxtFileName;
    }

    public String get_TxtName() {
        return _TxtName;
    }

    public void set_TxtName(String _TxtName) {
        this._TxtName = _TxtName;
    }

    public String get_TxtPathActual() {
        return _TxtPathActual;
    }

    public void set_TxtPathActual(String _TxtPathActual) {
        this._TxtPathActual = _TxtPathActual;
    }

    public String get_TxtPathThumbnail() {
        return _TxtPathThumbnail;
    }

    public void set_TxtPathThumbnail(String _TxtPathThumbnail) {
        this._TxtPathThumbnail = _TxtPathThumbnail;
    }

    public String get_uuId() {
        return _uuId;
    }

    public void set_uuId(String _uuId) {
        this._uuId = _uuId;
    }

    private String _IntBannerID;
    private String _TxtFileName;
    private String _TxtName;
    private String _TxtPathActual;
    private String _TxtPathThumbnail;
    private String _txtDesc;

    public String Property_uuId = "uuId";
    public String Property_IntBannerID = "IntBannerID";
    public String Property_TxtFileName = "TxtFileName";
    public String Property_TxtName = "TxtName";
    public String Property_TxtPathActual = "TxtPathActual";
    public String Property_TxtPathThumbnail = "TxtPathThumbnail";
    public String Property_txtDesc = "txtDesc";

    public String Property_All=Property_uuId+","+Property_IntBannerID+","+Property_TxtFileName+","+Property_TxtName+","+Property_TxtPathActual+","+Property_TxtPathThumbnail+","+Property_txtDesc;

    }
