package cc.seeed.sensecap.model.device;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/19 11:11
 * @Version V1.0
 */
public class PositionInfo {

    private double latitude;
    private double longitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "PositionInfo{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
