package cc.seeed.sensecap.model.data;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/8 15:33
 * @Version V1.0
 */
public class DeviceChartPointParam {

    private String deviceEui;
    private int channelIndex;
    private int measurementId;
    private int interval;
    private long startTime;
    private long endTime;


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

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public long getStartTime() {
        return startTime;
    }

    public void setStartTime(long startTime) {
        this.startTime = startTime;
    }

    public long getEndTime() {
        return endTime;
    }

    public void setEndTime(long endTime) {
        this.endTime = endTime;
    }
}
