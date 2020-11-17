package cc.seeed.sensecap.common.enums;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/13 16:58
 * @Version V1.0
 */
public enum RegionType {
    SENSECAP_CN(1, "https://sensecap.seeed.cn/openapi"),
    SENSECAP_CC(2, "https://sensecap.seeed.cc/openapi");
    private int region;
    private String domain;

    RegionType(int region, String domain) {
        this.region = region;
        this.domain = domain;
    }

    public int getRegion() {
        return region;
    }

    public void setRegion(int region) {
        this.region = region;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }


    public static String getDomainByRegion(int region) {
        RegionType[] values = RegionType.values();
        for (RegionType regionType : values) {
            if (regionType.getRegion() == region) {
                return regionType.getDomain();
            }
        }
        return "";
    }
}
