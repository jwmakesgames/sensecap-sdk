package cc.seeed.sensecap.model.device;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/17 09:24
 * @Version V1.0
 */
public class DeviceBaseInfo {

    @JsonProperty(value = "device_eui",access = JsonProperty.Access.WRITE_ONLY)
    private String deviceEui;

    @JsonProperty(value = "device_name",access = JsonProperty.Access.WRITE_ONLY)
    private String deviceName;

    public String getDeviceEui() {
        return deviceEui;
    }

    public void setDeviceEui(String deviceEui) {
        this.deviceEui = deviceEui;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    @Override
    public String toString() {
        return "DeviceBaseInfo{" +
                "deviceEui='" + deviceEui + '\'' +
                ", deviceName='" + deviceName + '\'' +
                '}';
    }
}
