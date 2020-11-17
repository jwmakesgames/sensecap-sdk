package cc.seeed.sensecap.queries.device;

import cc.seeed.sensecap.config.OpenApiConfig;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/11/13 14:33
 * @Version V1.0
 */
public class DeviceQueryBuilder {
    private String groupUUID;
    private List<String> deviceEuis;
    private int deviceType;
    private String eui;
    private String code;
    private String deviceName;
    private String longitude;
    private String latitude;

    private OpenApiConfig openApiConfig;


    public DeviceQueryBuilder(OpenApiConfig openApiConfig) {
        this.openApiConfig=openApiConfig;
    }

    public String getGroupUUID() {
        return groupUUID;
    }

    public List<String> getDeviceEuis() {
        return deviceEuis;
    }

    public int getDeviceType() {
        return deviceType;
    }

    public String getEui() {
        return eui;
    }

    public String getCode() {
        return code;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public OpenApiConfig getOpenApiConfig() {
        return openApiConfig;
    }

    public DeviceQueryBuilder groupUUID(String groupUUID) {
        this.groupUUID = groupUUID;
        return this;
    }

    public DeviceQueryBuilder deviceEuis(List<String> deviceEuis) {
        this.deviceEuis = deviceEuis;
        return this;
    }

    public DeviceQueryBuilder deviceType(int deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public DeviceQueryBuilder eui(String eui) {
        this.eui = eui;
        return this;
    }

    public DeviceQueryBuilder code(String code) {
        this.code = code;
        return this;
    }

    public DeviceQueryBuilder deviceName(String deviceName) {
        this.deviceName = deviceName;
        return this;
    }

    public DeviceQueryBuilder longitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public DeviceQueryBuilder latitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public DeviceQueryBuilder openApiConfig(OpenApiConfig openApiConfig) {
        this.openApiConfig = openApiConfig;
        return this;
    }

    public DeviceQuery build(){
        return new DeviceQuery(this);
    }
}
