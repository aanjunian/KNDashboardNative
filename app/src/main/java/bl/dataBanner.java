package bl;

/**
 * Created by ASUS ZE on 28/10/2016.
 */

public class dataBanner {
    private String IntBannerID;
    private String TxtFileName;
    private String TxtName;
    private String TxtPathActual;
    private String TxtPathThumbnail;
    private String txtDesc;

    public String get_IntBannerID() {
        return IntBannerID;
    }
    public String get_TxtFileName() {
        return TxtFileName;
    }
    public String get_TxtName(){
        return TxtName;
    }
    public String get_TxtPathActual(){
        return TxtPathActual;
    }
    public String get_TxtPathThumbnail(){
        return TxtPathThumbnail;
    }
    public String get_txtDesc(){
        return txtDesc;
    }

    public dataBanner(String IntBannerID, String TxtFileName, String TxtName, String TxtPathActual, String TxtPathThumbnail,String txtDesc) {
        this.IntBannerID = IntBannerID;
        this.TxtFileName = TxtFileName;
        this.TxtName = TxtName;
        this.TxtPathActual = TxtPathActual;
        this.TxtPathThumbnail = TxtPathThumbnail;
        this.txtDesc = txtDesc;
    }
}
