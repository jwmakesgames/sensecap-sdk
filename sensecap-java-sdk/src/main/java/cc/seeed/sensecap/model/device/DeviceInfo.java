package cc.seeed.sensecap.model.device;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/17 09:25
 * @Version V1.0
 */
public class DeviceInfo {
    private String frequency;

    @JsonProperty(value = "device_eui",access = JsonProperty.Access.WRITE_ONLY)
    private String deviceEui;
    @JsonProperty(value = "device_name",access = JsonProperty.Access.WRITE_ONLY)
    private String deviceName;
    /**
     * 1:LoRaWAN，2：NB-IoT，3：2G , 4:LoRaPP
     */
    @JsonProperty(value = "device_network",access = JsonProperty.Access.WRITE_ONLY)
    private int deviceNetwork;
    private PositionInfo position;

    /**
     * GPS位置来源，0-手动设置的位置，1-设备上报的位置
     */
    @JsonProperty(value = "position_source",access = JsonProperty.Access.WRITE_ONLY)
    private int positionSource;
    @JsonProperty(value = "hardware_version",access = JsonProperty.Access.WRITE_ONLY)
    private String hardwareVersion;
    @JsonProperty(value = "software_version",access = JsonProperty.Access.WRITE_ONLY)
    private String softwareVersion;
    private SimInfo sim;

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

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

    public int getDeviceNetwork() {
        return deviceNetwork;
    }

    public void setDeviceNetwork(int deviceNetwork) {
        this.deviceNetwork = deviceNetwork;
    }

    public PositionInfo getPosition() {
        return position;
    }

    public void setPosition(PositionInfo position) {
        this.position = position;
    }

    public int getPositionSource() {
        return positionSource;
    }

    public void setPositionSource(int positionSource) {
        this.positionSource = positionSource;
    }

    public String getHardwareVersion() {
        return hardwareVersion;
    }

    public void setHardwareVersion(String hardwareVersion) {
        this.hardwareVersion = hardwareVersion;
    }

    public String getSoftwareVersion() {
        return softwareVersion;
    }

    public void setSoftwareVersion(String softwareVersion) {
        this.softwareVersion = softwareVersion;
    }

    public SimInfo getSim() {
        return sim;
    }

    public void setSim(SimInfo sim) {
        this.sim = sim;
    }

    @Override
    public String toString() {
        return "DeviceInfo{" +
                "frequency='" + frequency + '\'' +
                ", deviceEui='" + deviceEui + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceNetwork=" + deviceNetwork +
                ", position=" + position.toString() +
                ", positionSource=" + positionSource +
                ", hardwareVersion='" + hardwareVersion + '\'' +
                ", softwareVersion='" + softwareVersion + '\'' +
                ", sim=" + sim.toString() +
                '}';
    }
}
