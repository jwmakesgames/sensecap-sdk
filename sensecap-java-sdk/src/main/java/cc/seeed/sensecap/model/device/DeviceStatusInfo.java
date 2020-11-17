package cc.seeed.sensecap.model.device;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/17 09:32
 * @Version V1.0
 */
public class DeviceStatusInfo {

    @JsonProperty(value = "device_eui", access = JsonProperty.Access.WRITE_ONLY)
    private String deviceEui;

    @JsonProperty(value = "latest_message_time", access = JsonProperty.Access.WRITE_ONLY)
    private String latestMessageTime;

    @JsonProperty(value = "online_status", access = JsonProperty.Access.WRITE_ONLY)
    private int onlineStatus;

    @JsonProperty(value = "battery_status", access = JsonProperty.Access.WRITE_ONLY)
    private int batteryStatus;

    @JsonProperty(value = "report_frequency", access = JsonProperty.Access.WRITE_ONLY)
    private int reportFrequency;

    public String getDeviceEui() {
        return deviceEui;
    }

    public void setDeviceEui(String deviceEui) {
        this.deviceEui = deviceEui;
    }

    public String getLatestMessageTime() {
        return latestMessageTime;
    }

    public void setLatestMessageTime(String latestMessageTime) {
        this.latestMessageTime = latestMessageTime;
    }

    public int getOnlineStatus() {
        return onlineStatus;
    }

    public void setOnlineStatus(int onlineStatus) {
        this.onlineStatus = onlineStatus;
    }

    public int getBatteryStatus() {
        return batteryStatus;
    }

    public void setBatteryStatus(int batteryStatus) {
        this.batteryStatus = batteryStatus;
    }

    public int getReportFrequency() {
        return reportFrequency;
    }

    public void setReportFrequency(int reportFrequency) {
        this.reportFrequency = reportFrequency;
    }

    @Override
    public String toString() {
        return "DeviceStatusInfo{" +
                "deviceEui='" + deviceEui + '\'' +
                ", latestMessageTime='" + latestMessageTime + '\'' +
                ", onlineStatus=" + onlineStatus +
                ", batteryStatus=" + batteryStatus +
                ", reportFrequency=" + reportFrequency +
                '}';
    }
}
