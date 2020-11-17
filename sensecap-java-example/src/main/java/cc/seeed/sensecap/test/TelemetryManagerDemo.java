package cc.seeed.sensecap.test;

import callback.DataCallBack;
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

    static {
        String accessId = "593D8C03822F9F55";
        String accessKey = "E602E108EE6CA76AFBCBC235CF8A2C2AC8AEE774BB7399F9CD1F3C534EA44978";
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
                .deviceEui("2CF7F12110700077")
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
                .deviceEui("2CF7F12110700077")
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
                .deviceEui("2CF7F12110700077")
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
                .deviceEui("2CF7F12110700077")
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
/*
    public static void getTelemetryDataListCallBackV1() throws BaseException {
        String accessId = "KL7YGS1TQP5DZYTO";
        String accessKey = "195957AE1F95469EB822E2944CBC95BEC0C7422F2028476F948B8436F4578D8F";
        int region = RegionType.SENSECAP_CC.getRegion();

        OpenApiConfig openApiConfig = new OpenApiConfig(accessId, accessKey, region);
        SenseCAP client = new SenseCAPClientBuilder().build(accessId, accessKey, region);
        List<TelemetryDataInfo> telemetryDataList = client.getTelemetryDataList("2CF7F1201470028B", 1, 4097, 30, 0, 0);
        System.out.println(JSON.toJSONString(telemetryDataList));
        SenseCAPClientBuilder.build(openApiConfig).getTelemetryManager()
                .deviceEui("2CF7F1201470028B")
                .channelIndex(1)
                .limit(300)
                .measurementId(4097)
                .startTime(1599206725516L)
                //.endTime(1599448448000L)
                .getTelemetryDataList(telemetryDataResult -> {
                    System.out.println("***** 4097----->>>> " + telemetryDataResult.toString());
                });

        System.out.println("connection.....");
    }


    public static void getChartPointDataBatch() throws BaseException {
        String accessId = "593D8C03822F9F55";
        String accessKey = "E602E108EE6CA76AFBCBC235CF8A2C2AC8AEE774BB7399F9CD1F3C534EA44978";
        int region = RegionType.SENSECAP_CC.getRegion();
        OpenApiConfig openApiConfig = new OpenApiConfig(accessId, accessKey, region);

        List<DeviceChartPointParam >list=Lists.newArrayList();
        DeviceChartPointParam deviceChartPointParam=new DeviceChartPointParam();
        deviceChartPointParam.setDeviceEui("2CF7F12010700061");
        deviceChartPointParam.setStartTime(1597051308000L);
        list.add(deviceChartPointParam);
        List<ChartPointDataBatch> chartPointDataBatch = SenseCAPClientBuilder.build(openApiConfig)
                .getTelemetryManager()
                .deviceChartPointParams(list)
                .getChartPointDataBatch();
        System.out.println(JSON.toJSONString(chartPointDataBatch));
    }
*/
}
