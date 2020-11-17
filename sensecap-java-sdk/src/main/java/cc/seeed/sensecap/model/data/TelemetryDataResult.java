package cc.seeed.sensecap.model.data;


/**
 * @Author AG
 * @Description
 * @Date 2020/9/4 15:41
 * @Version V1.0
 */
public class TelemetryDataResult {

    private String deviceEui;
    private int channelIndex;
    private int measurementId;
    private double value;
    private long timestamp;

    public String getDeviceEui() {
        return deviceEui;
    }

    public TelemetryDataResult setDeviceEui(String deviceEui) {
        this.deviceEui = deviceEui;
        return this;
    }

    public int getChannelIndex() {
        return channelIndex;
    }

    public TelemetryDataResult setChannelIndex(int channelIndex) {
        this.channelIndex = channelIndex;
        return this;
    }

    public int getMeasurementId() {
        return measurementId;
    }

    public TelemetryDataResult setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
        return this;
    }

    public double getValue() {
        return value;
    }

    public TelemetryDataResult setValue(double value) {
        this.value = value;
        return this;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public TelemetryDataResult setTimestamp(long timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public String toString() {
        return "TelemetryDataResult{" +
                "deviceEui='" + deviceEui + '\'' +
                ", channelIndex=" + channelIndex +
                ", measurementId=" + measurementId +
                ", value=" + value +
                ", timestamp=" + timestamp +
                '}';
    }
}
