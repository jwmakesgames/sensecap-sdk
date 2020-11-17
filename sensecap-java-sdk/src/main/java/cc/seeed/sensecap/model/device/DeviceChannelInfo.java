package cc.seeed.sensecap.model.device;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/17 09:31
 * @Version V1.0
 */
public class DeviceChannelInfo {
    @JsonProperty(value = "device_eui", access = JsonProperty.Access.WRITE_ONLY)
    private String deviceEui;
    @JsonProperty(value = "channels", access = JsonProperty.Access.WRITE_ONLY)
    private List<ChannelInfo> channels;


    public String getDeviceEui() {
        return deviceEui;
    }

    public void setDeviceEui(String deviceEui) {
        this.deviceEui = deviceEui;
    }

    public List<ChannelInfo> getChannels() {
        return channels;
    }

    public void setChannels(List<ChannelInfo> channels) {
        this.channels = channels;
    }

    @Override
    public String toString() {
        return "DeviceChannelInfo{" +
                "deviceEui='" + deviceEui + '\'' +
                ", channels=" + channels +
                '}';
    }
}
