package cc.seeed.sensecap.model.data;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/19 17:27
 * @Version V1.0
 */
public class PointsInfo {

    @JsonProperty(value = "measurement_value", access = JsonProperty.Access.WRITE_ONLY)
    private double measurementValue;

    @JsonProperty(value = "measurement_id", access = JsonProperty.Access.WRITE_ONLY)
    private int measurementId;

    private String time;


    public double getMeasurementValue() {
        return measurementValue;
    }

    public void setMeasurementValue(double measurementValue) {
        this.measurementValue = measurementValue;
    }

    public int getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "PointsInfo{" +
                "measurementValue=" + measurementValue +
                ", measurementId=" + measurementId +
                ", time='" + time + '\'' +
                '}';
    }
}
