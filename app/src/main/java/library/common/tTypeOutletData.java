package library.common;

/**
 * Created by ASUS ZE on 26/09/2016.
 */

public class tTypeOutletData {

    String _txtSumberDataID;
    String _txtNamaInstitusi;
    String _txtNamaPropinsi;
    String _txtAlamat;
    String _txtTelepon;
    String _tipeSumberData;
    String _txtNamaKabKota;

    public String get_txtNamaKabKota() {
        return _txtNamaKabKota;
    }

    public void set_txtNamaKabKota(String _txtNamaKabKota) {
        this._txtNamaKabKota = _txtNamaKabKota;
    }

    public String get_txtSumberDataID() {
        return _txtSumberDataID;
    }

    public void set_txtSumberDataID(String _txtSumberDataID) {
        this._txtSumberDataID = _txtSumberDataID;
    }

    public String get_txtNamaInstitusi() {
        return _txtNamaInstitusi;
    }

    public void set_txtNamaInstitusi(String _txtNamaInstitusi) {
        this._txtNamaInstitusi = _txtNamaInstitusi;
    }

    public String get_txtNamaPropinsi() {
        return _txtNamaPropinsi;
    }

    public void set_txtNamaPropinsi(String _txtNamaPropinsi) {
        this._txtNamaPropinsi = _txtNamaPropinsi;
    }

    public String get_txtAlamat() {
        return _txtAlamat;
    }

    public void set_txtAlamat(String _txtAlamat) {
        this._txtAlamat = _txtAlamat;
    }

    public String get_txtTelepon() {
        return _txtTelepon;
    }

    public void set_txtTelepon(String _txtTelepon) {
        this._txtTelepon = _txtTelepon;
    }

    public String get_tipeSumberData() {
        return _tipeSumberData;
    }

    public void set_tipeSumberData(String _tipeSumberData) {
        this._tipeSumberData = _tipeSumberData;
    }

    public tTypeOutletData(String _txtSumberDataID, String _tipeSumberData, String _txtNamaInstitusi, String _txtNamaPropinsi, String _txtAlamat, String _txtTelepon, String _txtNamaKabKota) {
        this._txtSumberDataID = _txtSumberDataID;
        this._tipeSumberData = _tipeSumberData;
        this._txtNamaInstitusi = _txtNamaInstitusi;
        this._txtNamaPropinsi = _txtNamaPropinsi;
        this._txtAlamat = _txtAlamat;
        this._txtTelepon = _txtTelepon;
        this._txtNamaKabKota = _txtNamaKabKota;
    }
}
