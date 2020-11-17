package cc.seeed.sensecap.queries.device;

import cc.seeed.sensecap.actions.Device;
import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.exception.BaseException;

/**
 * @Author AG
 * @Description
 * @Date 2020/11/13 17:54
 * @Version V1.0
 */
public class DeviceBinder {
    private String eui;
    private String code;
    private String deviceName;
    private String longitude;
    private String latitude;
    private String groupUUID;

    private OpenApiConfig openApiConfig;

    private Device device;

    public DeviceBinder(DeviceBinderBuilder builder) {
        this.groupUUID = builder.getGroupUUID();
        this.eui = builder.getEui();
        this.code = builder.getCode();
        this.deviceName = builder.getDeviceName();
        this.longitude = builder.getLongitude();
        this.latitude = builder.getLatitude();
        this.openApiConfig = builder.getOpenApiConfig();
        this.device = new Device(builder.openApiConfig);
    }

    public static DeviceBinderBuilder newBuilder(OpenApiConfig openApiConfig) {
        return new DeviceBinderBuilder(openApiConfig);
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


    public String getGroupUUID() {
        return groupUUID;
    }


    public void bind() throws BaseException {
        device.bindDevice(eui, code, deviceName, groupUUID, longitude, latitude);
    }


    public static class DeviceBinderBuilder {
        private String eui;
        private String code;
        private String deviceName;
        private String longitude;
        private String latitude;
        private String groupUUID;

        private OpenApiConfig openApiConfig;


        public DeviceBinderBuilder(OpenApiConfig openApiConfig) {
            this.openApiConfig = openApiConfig;
        }

        public String getEui() {
            return eui;
        }

        public DeviceBinderBuilder eui(String eui) {
            this.eui = eui;
            return this;
        }

        public String getCode() {
            return code;
        }

        public DeviceBinderBuilder code(String code) {
            this.code = code;
            return this;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public DeviceBinderBuilder deviceName(String deviceName) {
            this.deviceName = deviceName;
            return this;
        }

        public String getLongitude() {
            return longitude;
        }

        public DeviceBinderBuilder longitude(String longitude) {
            this.longitude = longitude;
            return this;
        }

        public String getLatitude() {
            return latitude;
        }

        public DeviceBinderBuilder latitude(String latitude) {
            this.latitude = latitude;
            return this;
        }

        public String getGroupUUID() {
            return groupUUID;
        }

        public DeviceBinderBuilder groupUUID(String groupUUID) {
            this.groupUUID = groupUUID;
            return this;
        }

        public OpenApiConfig getOpenApiConfig() {
            return openApiConfig;
        }

        public DeviceBinderBuilder setOpenApiConfig(OpenApiConfig openApiConfig) {
            this.openApiConfig = openApiConfig;
            return this;
        }


        public DeviceBinder build() {
            return new DeviceBinder(this);
        }
    }
}
