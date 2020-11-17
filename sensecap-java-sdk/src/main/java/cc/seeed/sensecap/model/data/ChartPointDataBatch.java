package cc.seeed.sensecap.model.data;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/8 15:38
 * @Version V1.0
 */
public class ChartPointDataBatch {

    private String deviceEui;
    List<ChartPointDataInfo> chartPointDataInfos;

    public String getDeviceEui() {
        return deviceEui;
    }

    public void setDeviceEui(String deviceEui) {
        this.deviceEui = deviceEui;
    }

    public List<ChartPointDataInfo> getChartPointDataInfos() {
        return chartPointDataInfos;
    }

    public void setChartPointDataInfos(List<ChartPointDataInfo> chartPointDataInfos) {
        this.chartPointDataInfos = chartPointDataInfos;
    }
}
