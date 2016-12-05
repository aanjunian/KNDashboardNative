package library.common;

public class tUserLoginData {

	public synchronized String get_txtUserName() {
		return _txtUserName;
	}
	public synchronized void set_txtUserName(String _txtUserName) {
		this._txtUserName = _txtUserName;
	}
	public tUserLoginData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public tUserLoginData(int _intId, String _txtUserId, String _txtRoleId,
						  String _txtRoleName, String _txtUserName, String _dtLastLogin, String _txtCabang, String _txtEmail, String _txtEmpId, String _txtRegion, String _txtCabangIdMenu, String _txtCabangMenu, String _txtRegionMenu) {
		this._intId = _intId;
		this._txtUserId = _txtUserId;
		this._txtRoleId = _txtRoleId;
		this._txtRoleName = _txtRoleName;
		this._txtUserName = _txtUserName;
		this._dtLastLogin=_dtLastLogin;
        this._txtCabang = _txtCabang;
        this._txtEmail = _txtEmail;
        this._txtEmpId = _txtEmpId;
        this._txtRegion = _txtRegion;
		this._txtCabangIdMenu = _txtCabangIdMenu;
		this._txtCabangMenu = _txtCabangMenu;
		this._txtRegionMenu = _txtRegionMenu;
	}
	public int get_intId() {
		return _intId;
	}
	public void set_intId(int _intId) {
		this._intId = _intId;
	}
	public String get_txtUserId() {
		return _txtUserId;
	}
	public void set_txtUserId(String _txtUserId) {
		this._txtUserId = _txtUserId;
	}
	public String get_txtRoleId() {
		return _txtRoleId;
	}
	public void set_txtRoleId(String _txtRoleId) {
		this._txtRoleId = _txtRoleId;
	}
	public String get_txtRoleName() {
		return _txtRoleName;
	}
	public void set_txtRoleName(String _txtRoleName) {
		this._txtRoleName = _txtRoleName;
	}

	private int _intId;
	private String _txtUserId;
	private String _txtUserName;
	private String _txtRoleId;
	private String _txtRoleName;
	private String _dtLastLogin;
    private String _txtCabang;
    private String _txtEmail;
    private String _txtEmpId;
    private String _txtRegion;
	private String _txtCabangIdMenu;
	private String _txtCabangMenu;
	private String _txtRegionMenu;

	public String Property_intId="intId";
	public String Property_txtUserId="txtUserId";
	public String Property_txtRoleId="txtRoleId";
	public String Property_txtRoleName="txtRoleName";
	public String Property_txtUserName="txtUserName";
	public String Property_DtLastLogin = "DtLastLogin";
    public String Property_txtCabang = "txtCabang";
    public String Property_txtEmail = "txtEmail";
    public String Property_txtEmpId = "txtEmpId";
    public String Property_txtRegion = "txtRegion";
	public String Property_txtCabangIdMenu = "txtCabangIdMenu";
	public String Property_txtCabangMenu = "txtCabangMenu";
	public String Property_txtRegionMenu = "txtRegionMenu";
	public String Property_All=Property_intId +","+
			Property_txtUserId +","+
			Property_txtRoleId +","+
			Property_txtRoleName +","+
			Property_txtUserName +","+
			Property_DtLastLogin +","+
            Property_txtCabang +","+
            Property_txtEmail +","+
            Property_txtEmpId +","+
            Property_txtRegion +","+
			Property_txtCabangIdMenu +","+
			Property_txtCabangMenu +","+
			Property_txtRegionMenu;


    public String get_txtCabang() {
        return _txtCabang;
    }

    public void set_txtCabang(String _txtCabang) {
        this._txtCabang = _txtCabang;
    }

    public String get_txtEmail() {
        return _txtEmail;
    }

    public void set_txtEmail(String _txtEmail) {
        this._txtEmail = _txtEmail;
    }

    public String get_txtEmpId() {
        return _txtEmpId;
    }

    public void set_txtEmpId(String _txtEmpId) {
        this._txtEmpId = _txtEmpId;
    }

    public String get_txtRegion() {
        return _txtRegion;
    }

    public void set_txtRegion(String _txtRegion) {
        this._txtRegion = _txtRegion;
    }

	public String get_dtLastLogin() {
		return _dtLastLogin;
	}

	public void set_dtLastLogin(String _dtLastLogin) {
		this._dtLastLogin = _dtLastLogin;
	}

	public String get_txtCabangMenu() {
		return _txtCabangMenu;
	}

	public void set_txtCabangMenu(String _txtCabangMenu) {
		this._txtCabangMenu = _txtCabangMenu;
	}

	public String get_txtCabangIdMenu() {
		return _txtCabangIdMenu;
	}

	public void set_txtCabangIdMenu(String _txtCabangIdMenu) {
		this._txtCabangIdMenu = _txtCabangIdMenu;
	}
	public String get_txtRegionMenu() {
		return _txtRegionMenu;
	}

	public void set_txtRegionMenu(String _txtRegionMenu) {
		this._txtRegionMenu = _txtRegionMenu;
	}
}
