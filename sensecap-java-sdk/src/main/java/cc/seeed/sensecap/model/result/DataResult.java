package cc.seeed.sensecap.model.result;

import cc.seeed.sensecap.actions.TelemetryData;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.data.ChartDataListInfo;
import cc.seeed.sensecap.model.data.ChartPointDataInfo;
import cc.seeed.sensecap.model.data.LatestTelemetryDataInfo;
import cc.seeed.sensecap.model.data.TelemetryDataInfo;
import cc.seeed.sensecap.queries.data.DataQuery;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/11/16 10:25
 * @Version V1.0
 */
public class DataResult {

    private DataQuery dataQuery;
    private TelemetryData telemetryData;

    public DataResult(DataQuery dataQuery) {
        this.dataQuery = dataQuery;
        telemetryData = new TelemetryData(dataQuery.getOpenApiConfig());
    }


    public List<LatestTelemetryDataInfo> latestData() throws BaseException {
        return telemetryData.getLatestTelemetryData(dataQuery.getDeviceEui(), dataQuery.getChannelIndex(), dataQuery.getMeasurementId());
    }


    public List<ChartPointDataInfo> chartPointData() throws BaseException {
        return telemetryData.getChartPointData(dataQuery.getDeviceEui(), dataQuery.getChannelIndex(), dataQuery.getMeasurementId(), dataQuery.getInterval(), dataQuery.getStartTime(), dataQuery.getEndTime());
    }

    public List<ChartDataListInfo> chartData() throws BaseException {
        return telemetryData.getChartDataList(dataQuery.getDeviceEui(), dataQuery.getChannelIndex(), dataQuery.getMeasurementId(), dataQuery.getInterval(), dataQuery.getStartTime(), dataQuery.getEndTime());
    }


    public List<TelemetryDataInfo> dataList() throws BaseException {
        return telemetryData.getTelemetryDataListCallback(dataQuery.getDeviceEui(), dataQuery.getChannelIndex(), dataQuery.getMeasurementId(), dataQuery.getLimit(), dataQuery.getStartTime(), dataQuery.getEndTime(), dataQuery.getCallback());
    }


}
