package cc.seeed.sensecap.test;

import cc.seeed.sensecap.SenseCAPClient;
import cc.seeed.sensecap.SenseCAPClientBuilder;
import cc.seeed.sensecap.common.enums.RegionType;
import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.callback.TelemetryDataCallback;
import cc.seeed.sensecap.model.data.*;
import cc.seeed.sensecap.model.result.DataResult;
import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/2 10:51
 * @Version V1.0
 */
public class TelemetryManagerDemo {

    private static SenseCAPClient senseCAPClient;
    private static String deviceEui="";
    static {
        String accessId = "";
        String accessKey = "";
        int region = RegionType.SENSECAP_CC.getRegion();
        OpenApiConfig openApiConfig = new OpenApiConfig(accessId, accessKey, region);
        senseCAPClient = new SenseCAPClientBuilder().buildConfig(openApiConfig);
    }


    public static void main(String[] args) throws BaseException {

        //getLatestTelemetryData();
        getChartPointData();
        //getTelemetryDataList();
        //getTelemetryDataListCallBack();
    }


    public static void getLatestTelemetryData() throws BaseException {
        DataResult dataResult = senseCAPClient.getDataManager().createDataQuery()
                .deviceEui(deviceEui)
                .channelIndex(1)
                .measurementId(4099)
                .build()
                .execute();
        List<LatestTelemetryDataInfo> latestTelemetryDataInfos = dataResult.latestData();
        System.out.println(latestTelemetryDataInfos.toString());
    }


    public static void getChartPointData() throws BaseException {
        /**
         * 将庞大的数据段分成小数据段，然后输出每个小段的平均值，最长返回一年的数据，
         * 每个测量量最多返回250个点，超过250个点将自动重新划分时间段返回250个点
         */
        DataResult dataResult = senseCAPClient.getDataManager().createDataQuery()
                .startTime(1605600000000L)
                .deviceEui(deviceEui)
                .build()
                .execute();
        List<ChartPointDataInfo> chartPointDataInfos = dataResult.chartPointData();
        System.out.println(JSON.toJSONString(chartPointDataInfos));

        /**
         * 一天内(含)，返回所有数据点
         * 时间大于一天，返回interval范围数据点平均值
         * interval勿小于数据上报最小间隔
         */
        DataResult dataResult1 = senseCAPClient.getDataManager().createDataQuery()
                .startTime(1605600000000L)
                .deviceEui("2CF7F12110700077")
                .interval(30)
                .build()
                .execute();
        List<ChartDataListInfo> listInfoList = dataResult1.chartData();
        System.out.println(JSON.toJSONString(listInfoList));

    }

    public static void getTelemetryDataList() throws BaseException {
        DataResult dataResult = senseCAPClient.getDataManager().createDataQuery()
                .deviceEui(deviceEui)
                .channelIndex(1)
                .measurementId(4099)
                .startTime(1602385871000L)
                .endTime(1604385871000L)
                .limit(10)
                .build()
                .execute();
        // List<TelemetryDataInfo> list = dataResult.dataList();
        // System.out.println(JSON.toJSONString(list.toString()));

    }


    public static void getTelemetryDataListCallBack() throws BaseException {
        DataResult dataResult = senseCAPClient.getDataManager().createDataQuery()
                .deviceEui(deviceEui)
                .channelIndex(1)
                //.measurementId(4100)
                .startTime(1605385871000L)
                //.endTime(1605496271000L)
                .limit(100)
                .callback(new TelemetryDataCallback() {
                    @Override
                    public void messageArrived(TelemetryDataResult var) throws BaseException {
                        System.out.println("Test获取消息： " + var.toString());
                    }
                })
                .build()
                .execute();
        List<TelemetryDataInfo> list = dataResult.dataList();
        System.out.println(JSON.toJSONString(list));


    }
}
