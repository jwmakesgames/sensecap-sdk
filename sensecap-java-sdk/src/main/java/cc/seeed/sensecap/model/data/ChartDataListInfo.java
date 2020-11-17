package cc.seeed.sensecap.model.data;


import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/11/17 11:12
 * @Version V1.0
 */
public class ChartDataListInfo {
    private int channelIndex;
    private int measurementId;

    List values;

    public int getChannelIndex() {
        return channelIndex;
    }

    public ChartDataListInfo setChannelIndex(int channelIndex) {
        this.channelIndex = channelIndex;
        return this;
    }

    public int getMeasurementId() {
        return measurementId;
    }

    public ChartDataListInfo setMeasurementId(int measurementId) {
        this.measurementId = measurementId;
        return this;
    }

    public List getValues() {
        return values;
    }

    public ChartDataListInfo setValues(List values) {
        this.values = values;
        return this;
    }
}
