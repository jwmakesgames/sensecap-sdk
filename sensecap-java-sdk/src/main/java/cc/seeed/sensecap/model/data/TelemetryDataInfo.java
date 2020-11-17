package cc.seeed.sensecap.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/24 14:05
 * @Version V1.0
 */
public class TelemetryDataInfo {

    @JsonIgnore
    private String deviceEui;
    private int  channelIndex;
    private int measurementId;

    List<TelemetryValueInfo> valueList;


    public String getDeviceEui() {
        return deviceEui;
    }

    public void setDeviceEui(String deviceEui) {
        this.deviceEui = deviceEui;
    }

    public int getChannelIndex() {
        return channelIndex;
    }

    public void setChannelIndex(int channelIndex) {
        this.channelIndex = channelIndex;
    }

    public int getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
    }

    public List<TelemetryValueInfo> getValueList() {
        return valueList;
    }

    public void setValueList(List<TelemetryValueInfo> valueList) {
        this.valueList = valueList;
    }

    @Override
    public String toString() {
        return "TelemetryDataInfo{" +
                "deviceEui='" + deviceEui + '\'' +
                ", channelIndex=" + channelIndex +
                ", measurementId=" + measurementId +
                ", valueList=" + valueList +
                '}';
    }
}
