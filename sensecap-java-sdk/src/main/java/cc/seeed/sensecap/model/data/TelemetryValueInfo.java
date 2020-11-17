package cc.seeed.sensecap.model.data;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/24 14:21
 * @Version V1.0
 */
public class TelemetryValueInfo {
    private double value;
    @JsonIgnore
    private String time;
    private long timestamp;


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "{value=" + value +
                ", time='" + time + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
