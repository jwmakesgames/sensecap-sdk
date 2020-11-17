package cc.seeed.sensecap.model.data;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/17 09:57
 * @Version V1.0
 */
public class ChartPointDataInfo {

    private List value;
    private int chanelIndex;
    private int measurementId;


    public List getValue() {
        return value;
    }

    public ChartPointDataInfo setValue(List value) {
        this.value = value;
        return this;
    }

    public int getChanelIndex() {
        return chanelIndex;
    }

    public ChartPointDataInfo setChanelIndex(int chanelIndex) {
        this.chanelIndex = chanelIndex;
        return this;
    }

    public int getMeasurementId() {
        return measurementId;
    }

    public ChartPointDataInfo setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
        return this;
    }

    @Override
    public String toString() {
        return "ChartPointDataInfo{" +
                "value=" + value +
                ", chanelIndex=" + chanelIndex +
                ", measurementId=" + measurementId +
                '}';
    }
}
