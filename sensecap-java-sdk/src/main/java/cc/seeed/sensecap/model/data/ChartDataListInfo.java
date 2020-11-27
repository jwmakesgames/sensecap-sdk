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

    List<Object[]> values;

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

    public List<Object[]> getValues() {
        return values;
    }

    public void setValues(List<Object[]> values) {
        this.values = values;
    }
}
