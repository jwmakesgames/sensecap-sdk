package cc.seeed.sensecap.model.auth;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/18 10:11
 * @Version V1.0
 */
public class OrganizationAuth {
    private int region;
    private String accesskey;
    private String accessId;

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getAccessId() {
        return accessId;
    }

    public void setAccessId(String accessId) {
        this.accessId = accessId;
    }
}
