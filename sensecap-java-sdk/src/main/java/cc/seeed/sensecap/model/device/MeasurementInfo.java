package cc.seeed.sensecap.model.device;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/19 15:42
 * @Version V1.0
 */
public class MeasurementInfo {
    @JsonProperty(value = "measurement_id", access = JsonProperty.Access.WRITE_ONLY)
    private int measurementId;
    @JsonProperty(value = "measurement_name", access = JsonProperty.Access.WRITE_ONLY)
    private String measurementName;
    @JsonProperty(value = "measurement_unit", access = JsonProperty.Access.WRITE_ONLY)
    private String measurementUnit;

    public int getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
    }

    public String getMeasurementName() {
        return measurementName;
    }

    public void setMeasurementName(String measurementName) {
        this.measurementName = measurementName;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    @Override
    public String toString() {
        return "MeasurementInfo{" +
                "measurementId=" + measurementId +
                ", measurementName='" + measurementName + '\'' +
                ", measurementUnit='" + measurementUnit + '\'' +
                '}';
    }
}
