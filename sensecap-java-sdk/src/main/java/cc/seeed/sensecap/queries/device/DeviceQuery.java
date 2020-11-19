package cc.seeed.sensecap.queries.device;

import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.result.DeviceResult;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/11/13 14:33
 * @Version V1.0
 */
public class DeviceQuery {
    private String groupUUID;
    private List<String> deviceEuis;
    private int deviceType;
    private String eui;
    private String code;
    private String deviceName;
    private String longitude;
    private String latitude;

    private OpenApiConfig openApiConfig;

    public DeviceQuery(DeviceQueryBuilder builder) {
        this.groupUUID = builder.getGroupUUID();
        this.deviceEuis = builder.getDeviceEuis();
        this.deviceType = builder.getDeviceType();
        this.eui = builder.getEui();
        this.code = builder.getCode();
        this.deviceName = builder.getDeviceName();
        this.longitude = builder.getLongitude();
        this.latitude = builder.getLatitude();
        this.openApiConfig = builder.getOpenApiConfig();
    }


    public static DeviceQueryBuilder newBuilder(OpenApiConfig openApiConfig) {
        return new DeviceQueryBuilder(openApiConfig);
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


    public DeviceResult execute() {
        return new DeviceResult(this, openApiConfig);
    }


}
