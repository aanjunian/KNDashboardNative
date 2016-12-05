package bl;

/**
 * Created by ASUS ZE on 28/10/2016.
 */

public class UserRewardData {
    private String imageResourceId;
    private String profileName;
    private String jabatan;
    private String region;
    private String cabang;
    private String points;
    private String ranking;

    public String getImageResourceId() {
        return imageResourceId;
    }
    public String getProfileName() {
        return profileName;
    }
    public String getJabatan(){
        return jabatan;
    }
    public String getRegion(){
        return region;
    }

    public String getCabang(){
        return cabang;
    }
    public String getPoints(){
        return points;
    }

    public String getRanking(){
        return ranking;
    }

    public UserRewardData(String imageResourceId, String profileName, String jabatan, String region, String cabang, String points, String ranking) {
        this.imageResourceId = imageResourceId;
        this.profileName = profileName;
        this.jabatan = jabatan;
        this.region = region;
        this.cabang = cabang;
        this.points = points;
        this.ranking = ranking;
    }
}
