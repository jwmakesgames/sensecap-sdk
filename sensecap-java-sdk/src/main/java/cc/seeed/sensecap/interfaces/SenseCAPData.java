package cc.seeed.sensecap.interfaces;

import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.callback.TelemetryDataCallback;
import cc.seeed.sensecap.model.data.ChartDataListInfo;
import cc.seeed.sensecap.model.data.ChartPointDataInfo;
import cc.seeed.sensecap.model.data.LatestTelemetryDataInfo;
import cc.seeed.sensecap.model.data.TelemetryDataInfo;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/11/16 10:05
 * @Version V1.0
 */
public interface SenseCAPData {
    List<LatestTelemetryDataInfo> getLatestTelemetryData(String deviceEui, int channelIndex, int measurementId) throws BaseException;

    List<TelemetryDataInfo> getTelemetryDataList(String deviceEui, int channelIndex, int measurementId, int limit, long startTime, long endTime) throws BaseException;

    List<ChartPointDataInfo> getChartPointData(String deviceEui, int channelIndex, int measurementId, int interval, long startTime, long endTime) throws BaseException;

    List<ChartDataListInfo> getChartDataList(String deviceEui, int channelIndex, int measurementId, int interval, long startTime, long endTime) throws BaseException;

    List<TelemetryDataInfo> getTelemetryDataListCallback(String deviceEui, int channelIndex, int measurementId, int limit, long startTime, long endTime, TelemetryDataCallback var) throws BaseException;
}
