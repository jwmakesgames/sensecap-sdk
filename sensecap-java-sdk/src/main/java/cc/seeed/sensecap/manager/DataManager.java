package cc.seeed.sensecap.manager;

import cc.seeed.sensecap.actions.TelemetryData;
import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.interfaces.SenseCAPData;
import cc.seeed.sensecap.model.callback.TelemetryDataCallback;
import cc.seeed.sensecap.model.data.ChartDataListInfo;
import cc.seeed.sensecap.model.data.ChartPointDataInfo;
import cc.seeed.sensecap.model.data.LatestTelemetryDataInfo;
import cc.seeed.sensecap.model.data.TelemetryDataInfo;
import cc.seeed.sensecap.queries.data.DataQuery;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/11/16 10:08
 * @Version V1.0
 */
public class DataManager implements SenseCAPData {

    private final OpenApiConfig openApiConfig;
    private final TelemetryData telemetryData;


    public DataManager(OpenApiConfig openApiConfig) {
        this.openApiConfig = openApiConfig;
        this.telemetryData = new TelemetryData(openApiConfig);
    }

    public DataQuery.DataQueryBuilder createDataQuery() {
        return DataQuery.newBuilder(openApiConfig);
    }

    @Override
    public List<LatestTelemetryDataInfo> getLatestTelemetryData(String deviceEui, int channelIndex, int measurementId) throws BaseException {
        return telemetryData.getLatestTelemetryData(deviceEui, channelIndex, measurementId);
    }

    @Override
    public List<TelemetryDataInfo> getTelemetryDataList(String deviceEui, int channelIndex, int measurementId, int limit, long startTime, long endTime) throws BaseException {
        return telemetryData.getTelemetryDataList(deviceEui, channelIndex, measurementId, limit, startTime, endTime);
    }

    @Override
    public List<ChartPointDataInfo> getChartPointData(String deviceEui, int channelIndex, int measurementId, int interval, long startTime, long endTime) throws BaseException {
        return telemetryData.getChartPointData(deviceEui, channelIndex, measurementId, interval, startTime, endTime);
    }

    @Override
    public List<ChartDataListInfo> getChartDataList(String deviceEui, int channelIndex, int measurementId, int interval, long startTime, long endTime) throws BaseException {
        return telemetryData.getChartDataList(deviceEui, channelIndex, measurementId, interval, startTime, endTime);
    }

    @Override
    public List<TelemetryDataInfo> getTelemetryDataListCallback(String deviceEui, int channelIndex, int measurementId, int limit, long startTime, long endTime, TelemetryDataCallback var) throws BaseException {
        return telemetryData.getTelemetryDataListCallback(deviceEui, channelIndex, measurementId, limit, startTime, endTime, var);
    }
}
