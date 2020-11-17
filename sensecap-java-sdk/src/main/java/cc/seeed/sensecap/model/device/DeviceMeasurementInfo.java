package cc.seeed.sensecap.model.device;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/17 09:33
 * @Version V1.0
 */
public class DeviceMeasurementInfo {

    @JsonProperty(value = "sensor_type", access = JsonProperty.Access.WRITE_ONLY)
    private String sensorType;

    @JsonProperty(value = "sensor_name", access = JsonProperty.Access.WRITE_ONLY)
    private String sensorName;

    @JsonProperty(value = "sensor_measurement", access = JsonProperty.Access.WRITE_ONLY)
    private List<MeasurementInfo> sensorMeasurement;

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getSensorName() {
        return sensorName;
    }

    public void setSensorName(String sensorName) {
        this.sensorName = sensorName;
    }

    public List<MeasurementInfo> getSensorMeasurement() {
        return sensorMeasurement;
    }

    public void setSensorMeasurement(List<MeasurementInfo> sensorMeasurement) {
        this.sensorMeasurement = sensorMeasurement;
    }

    @Override
    public String toString() {
        return "DeviceMeasurementInfo{" +
                "sensorType='" + sensorType + '\'' +
                ", sensorName='" + sensorName + '\'' +
                ", sensorMeasurement=" + sensorMeasurement +
                '}';
    }
}
