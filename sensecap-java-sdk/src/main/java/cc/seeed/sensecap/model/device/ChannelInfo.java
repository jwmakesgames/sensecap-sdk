package cc.seeed.sensecap.model.device;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/19 14:25
 * @Version V1.0
 */
public class ChannelInfo {

    @JsonProperty(value = "channel_index", access = JsonProperty.Access.WRITE_ONLY)
    private int channelIndex;
    @JsonProperty(value = "sensor_id", access = JsonProperty.Access.WRITE_ONLY)
    private String sensorId;
    @JsonProperty(value = "sensor_status", access = JsonProperty.Access.WRITE_ONLY)
    private int sensorStatus;
    @JsonProperty(value = "sensor_type", access = JsonProperty.Access.WRITE_ONLY)
    private String sensorType;
    @JsonProperty(value = "channel_name", access = JsonProperty.Access.WRITE_ONLY)
    private String channelName;
    @JsonProperty(value = "measurement_ids", access = JsonProperty.Access.WRITE_ONLY)
    private List<Integer> measurementIds;


    public int getChannelIndex() {
        return channelIndex;
    }

    public void setChannelIndex(int channelIndex) {
        this.channelIndex = channelIndex;
    }

    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public int getSensorStatus() {
        return sensorStatus;
    }

    public void setSensorStatus(int sensorStatus) {
        this.sensorStatus = sensorStatus;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public List<Integer> getMeasurementIds() {
        return measurementIds;
    }

    public void setMeasurementIds(List<Integer> measurementIds) {
        this.measurementIds = measurementIds;
    }

    @Override
    public String toString() {
        return "ChannelInfo{" +
                "channelIndex=" + channelIndex +
                ", sensorId='" + sensorId + '\'' +
                ", sensorStatus=" + sensorStatus +
                ", sensorType='" + sensorType + '\'' +
                ", channelName='" + channelName + '\'' +
                ", measurementIds=" + measurementIds +
                '}';
    }
}
