package library.common;

import java.io.Serializable;

public class clsRowItem implements Serializable{
	private String _imageId;
    private String _title;
    private String _desc;
    private String _txtId;
    private String _txtLink;
    
	//private String _txtId;
	//private String _txtTitle;
	//private String _txtDescription;
    public String Property_txtId="txtId";
	public String Property_imageId="imageId";
	public String Property_title="title";
	public String Property_desc="desc";
	public String Property_txtLink="txtLink";
	
	public String PropertyAll=Property_txtId+","+Property_imageId+","+Property_title+ "," + Property_desc+ "," + Property_txtLink;
     
//    public clsRowItem(int imageId, String title, String desc) {
//       this._imageId = imageId;
//        this._title = title;
//        this._desc = desc;
//    }
	public String get_txtId() {
		return _txtId;
	}

	public void set_txtId(String _txtId) {
		this._txtId = _txtId;
	}
    public String get_imageId() {
        return _imageId;
    }
    public void set_imageId(String _imageId) {
        this._imageId = _imageId;
    }
    public String get_txtLink() {
        return _txtLink;
    }
    public void set_txtLink(String _txtLink) {
        this._txtLink = _txtLink;
    }
    public String get_desc() {
        return _desc;
    }
    public void set_desc(String _desc) {
        this._desc = _desc;
    }
    public String get_title() {
        return _title;
    }
    public void set_title(String _title) {
        this._title = _title;
    }
    @Override
    public String toString() {
        return _title + "\n" + _desc;
    }

}
