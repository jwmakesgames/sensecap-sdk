package cc.seeed.sensecap.actions;

import cc.seeed.sensecap.api.url.PathConst;
import cc.seeed.sensecap.common.constant.TimeConstant;
import cc.seeed.sensecap.common.utils.DateUtils;
import cc.seeed.sensecap.common.utils.StringUtil;
import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.config.mqtt.CallBackMapCache;
import cc.seeed.sensecap.config.mqtt.MqttConnectionInfo;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.exception.HttpResponseMessageCode;
import cc.seeed.sensecap.interfaces.SenseCAPData;
import cc.seeed.sensecap.manager.MqttManager;
import cc.seeed.sensecap.manager.OrganizationManager;
import cc.seeed.sensecap.model.callback.SensorMqttCallback;
import cc.seeed.sensecap.model.callback.TelemetryDataCallback;
import cc.seeed.sensecap.model.data.*;
import cc.seeed.sensecap.model.resp.HttpResponseMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.Time;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author AG
 * @Description
 * @Date 2020/11/16 09:53
 * @Version V1.0
 */
public class TelemetryData implements SenseCAPData {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final static String CC_HOST = "tcp://sensecap-openstream.seeed.cc";
    private final static String CN_HOST = "tcp://sensecap-openstream.seeed.cn";
    private final static int DATA_DAY = 365;

    private final OpenApiConfig openApiConfig;
    private MqttManager mqttManager;
    private OrganizationManager organizationManager;
    private Device device;

    public TelemetryData(OpenApiConfig openApiConfig) {
        this.openApiConfig = openApiConfig;
        this.organizationManager = new OrganizationManager(openApiConfig);
        this.mqttManager = new MqttManager();
        this.device = new Device(openApiConfig);
    }

    @Override
    public List<LatestTelemetryDataInfo> getLatestTelemetryData(String deviceEui, int channelIndex, int measurementId) throws BaseException {
        try {

            if (StringUtils.isBlank(deviceEui)) {
                throw new BaseException("MissingArgument");
            }
            Map<String, String> query = Maps.newHashMapWithExpectedSize(3);

            query.put("device_eui", deviceEui);
            if (channelIndex > 0) {
                query.put("channel_index", String.valueOf(channelIndex));
            }
            if (measurementId > 0) {
                query.put("measurement_id", String.valueOf(measurementId));
            }

            HttpResponseMessage operation = openApiConfig.apiRequestOperation.getOperation(PathConst.VIEW_LATEST_TELEMETRY_DATA, query);
            if (operation.getCode() != 0) {
                logger.warn("operation:{}", operation);
                throw new BaseException(operation.getMsg());
            }
            JSONArray objects = JSON.parseArray(operation.getData().toString());
            List<LatestTelemetryDataInfo> list = Lists.newArrayListWithExpectedSize(objects.size());
            for (int i = 0; i < objects.size(); i++) {
                LatestTelemetryDataInfo latestTelemetryDataInfo = new LatestTelemetryDataInfo();
                JSONObject jsonObject = objects.getJSONObject(i);

                latestTelemetryDataInfo.setChannelIndex(jsonObject.getInteger("channel_index"));
                JSONArray points = jsonObject.getJSONArray("points");
                List<PointsInfo> pointsInfos = JSON.parseArray(points.toJSONString(), PointsInfo.class);

                latestTelemetryDataInfo.setPoints(pointsInfos);
                list.add(latestTelemetryDataInfo);
            }
            return list;
        } catch (NullPointerException var1) {
            logger.warn("e:{}", var1);
            throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
        } catch (IOException var2) {
            logger.warn("e:{}", var2);
            throw new BaseException(var2.getCause().getMessage());
        }
    }

    @Override
    public List<TelemetryDataInfo> getTelemetryDataList(String deviceEui, int channelIndex, int measurementId, int limit, long startTime, long endTime) throws BaseException {
        try {
            if (StringUtils.isBlank(deviceEui)) {
                throw new BaseException("MissingArgument");
            }
            Map<String, String> query = Maps.newHashMapWithExpectedSize(6);
            query.put("device_eui", deviceEui);
            if (channelIndex > 0) {
                query.put("channel_index", String.valueOf(channelIndex));
            }
            if (measurementId > 0) {
                query.put("measurement_id", String.valueOf(measurementId));
            }
            if (limit > 0) {
                query.put("limit", String.valueOf(limit));
            }
            if (startTime > 0) {
                query.put("time_start", String.valueOf(startTime));
            }
            if (endTime > 0) {
                query.put("time_end", String.valueOf(endTime));
            }
            if (startTime > endTime && endTime > 0) {
                throw new BaseException("InvalidArgument");
            }
            HttpResponseMessage operation = openApiConfig.apiRequestOperation.getOperation(PathConst.LIST_TELEMETRY_DATA, query);

            if (operation.getCode() != 0) {
                throw new BaseException(operation.getMsg());
            }
            JSONObject object = JSON.parseObject(operation.getData().toString());
            JSONArray objects = object.getJSONArray("list");
            if (CollectionUtils.isEmpty(objects)) {
                return Collections.emptyList();
            }
            List<TelemetryDataInfo> list = Lists.newArrayListWithExpectedSize(objects.size());
            JSONArray cmArray = objects.getJSONArray(0);
            JSONArray vtArray = objects.getJSONArray(1);

            for (int i = 0; i < cmArray.size(); i++) {
                TelemetryDataInfo telemetryDataInfo = new TelemetryDataInfo();
                JSONArray channelArray = cmArray.getJSONArray(i);
                JSONArray jsonArray = vtArray.getJSONArray(i);
                telemetryDataInfo.setDeviceEui(deviceEui);
                telemetryDataInfo.setChannelIndex(channelArray.getInteger(0));
                telemetryDataInfo.setMeasurementId(channelArray.getInteger(1));
                if (CollectionUtils.isEmpty(jsonArray)) {
                    continue;
                }
                List<TelemetryValueInfo> telemetryValueInfos = Lists.newArrayListWithExpectedSize(jsonArray.size());
                for (int j = 0; j < jsonArray.size(); j++) {
                    TelemetryValueInfo telemetryValueInfo = new TelemetryValueInfo();
                    JSONArray valueArray = JSON.parseArray(jsonArray.get(j).toString());
                    String time = valueArray.getString(1);
                    Double value = valueArray.getDouble(0);
                    telemetryValueInfo.setTime(time);
                    telemetryValueInfo.setTimestamp(DateUtils.dateTimeToUnixTimestamp(time));
                    telemetryValueInfo.setValue(value);
                    telemetryValueInfos.add(telemetryValueInfo);
                }
                telemetryDataInfo.setValueList(telemetryValueInfos);
                list.add(telemetryDataInfo);
            }
            return list;

        } catch (NullPointerException var1) {
            throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
        } catch (IOException var2) {
            throw new BaseException(var2.getMessage());
        }
    }

    /**
     * 数据点返回规则说明：将庞大的数据段分成小数据段，然后输出每个小段的平均值，最长返回一年的数据，
     *                 每个测量量最多返回250个点，超过250个点将自动重新划分时间段返回250个点
     *
     * @param deviceEui
     * @param channelIndex
     * @param measurementId
     * @param interval
     * @param startTime
     * @param endTime
     * @return
     * @throws BaseException
     */
    @Override
    public List<ChartPointDataInfo> getChartPointData(String deviceEui, int channelIndex, int measurementId, int interval, long startTime, long endTime) throws BaseException {
        try {

            if (StringUtils.isBlank(deviceEui)) {
                throw new BaseException("MissingArgument");
            }
            Map<String, String> query = Maps.newHashMapWithExpectedSize(6);
            query.put("device_eui", deviceEui);

            if (channelIndex > 0) {
                query.put("channel_index", String.valueOf(channelIndex));
            }
            if (measurementId > 0) {
                query.put("measurement_id", String.valueOf(measurementId));
            }
            if (interval > 0) {
                query.put("limit", String.valueOf(interval));
            }
            if (startTime > 0) {
                query.put("time_start", String.valueOf(startTime));
            }
            if (endTime > 0) {
                query.put("time_end", String.valueOf(endTime));
            }
            if (startTime > endTime && endTime > 0) {
                throw new BaseException("InvalidArgument");
            }
            HttpResponseMessage operation = openApiConfig.apiRequestOperation.getOperation(PathConst.AGGREGATE_CHART_POINTS, query);
            if (operation.getCode() != 0) {
                throw new BaseException(operation.getMsg());
            }
            if (StringUtils.isBlank(operation.getData().toString())) {
                return Collections.EMPTY_LIST;
            }
            List<ChartPointDataInfo> list = Lists.newArrayList();
            JSONArray objects = JSON.parseArray(operation.getData().toString());
            for (int i = 0; i < objects.size(); i++) {
                ChartPointDataInfo chartPointDataInfo = new ChartPointDataInfo();
                JSONObject jsonObject = JSON.parseObject(objects.get(i).toString());
                JSONArray lists = jsonObject.getJSONArray("lists");
                if (CollectionUtils.isEmpty(lists)) {
                    continue;
                }
                chartPointDataInfo.setChanelIndex(jsonObject.getInteger("channel"));
                chartPointDataInfo.setMeasurementId(lists.getJSONObject(0).getInteger("measurement_id"));
                List<Object[]> value = Lists.newArrayListWithExpectedSize(lists.size());

                for (int j = 0; j < lists.size(); j++) {
                    JSONObject var1 = lists.getJSONObject(j);
                    Object[] var2 = new Object[2];
                    var2[0] = DateUtils.dateTimeToUnixTimestamp(var1.getString("time")) / 1000;
                    var2[1] = var1.getDouble("average_value");
                    value.add(var2);
                }
                chartPointDataInfo.setValue(value);
                list.add(chartPointDataInfo);
            }
            return list;
        } catch (NullPointerException var1) {
            throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
        } catch (IOException var2) {
            logger.warn("var2:{}", var2);
            throw new BaseException(var2.getMessage());
        }
    }


    /**
     * 自定义时间间隔生成折线图数据
     * 1. 一天内(含)，返回所有数据点
     * 2. 时间大于一天，返回interval范围数据点平均值
     * 3. 【interval】勿小于数据上报最小间隔
     * 4. 数据丢包补点规则说明:
     *       补点规则
     *           interval<=60 每缺两个小时的数据中间补一个点
     *           interval>60  每缺 interval*2 小时的数据中间补一个点
     *       首位补点
     *           比较【查询时间开始点】和【实际数据首位时间点】按照补点规则补点，满足规则则补上首位点时间
     *       末尾补点
     *           比较【查询时间结束点】和【实际数据末尾时间点】按照补点规则补点，满足规则则补上末尾点，末尾时间按查询结束时间精确到具体秒补点
     * @param deviceEui
     * @param channelIndex
     * @param measurementId
     * @param interval  分钟
     * @param startTime
     * @param endTime
     * @return
     * @throws BaseException
     */
    @Override
    public List<ChartDataListInfo> getChartDataList(String deviceEui, int channelIndex, int measurementId, int interval, long startTime, long endTime) throws BaseException {
        if(interval==0){
            interval=30;
        }
        List<ChartDataListInfo> listInfoList = Lists.newArrayList();

        long now = System.currentTimeMillis();
        if ((now - startTime) > TimeConstant.ONE_DAY_MILLISECOND * DATA_DAY) {
            startTime = now - TimeConstant.ONE_DAY_MILLISECOND * DATA_DAY;
        }
        if ((now - endTime) > TimeConstant.ONE_DAY_MILLISECOND * DATA_DAY) {
            endTime = now;
        }

        List<TelemetryDataInfo> telemetryDataList = getTelemetryDataList(deviceEui, channelIndex, measurementId, 0, startTime, endTime);
        if (CollectionUtils.isEmpty(telemetryDataList)) {
            return listInfoList;
        }


        long intervalTime = getInterval(startTime, endTime, interval);
        long pointInterval = getPointInterval(interval);
        long startKey = startTime / intervalTime + 1;
        long endKey = endTime / intervalTime + 1;
        for (TelemetryDataInfo telemetryDataInfo : telemetryDataList) {
            Map<Long, ValueStatisticsInfo> valueMap = Maps.newTreeMap();
            ChartDataListInfo chartDataListInfo = new ChartDataListInfo();
            int channel = telemetryDataInfo.getChannelIndex();
            chartDataListInfo.setChannelIndex(channel);
            chartDataListInfo.setMeasurementId(telemetryDataInfo.getMeasurementId());
            List<TelemetryValueInfo> valueList = telemetryDataInfo.getValueList();
            for (TelemetryValueInfo telemetryValueInfo : valueList) {
                ValueStatisticsInfo valueStatisticsInfo = null;
                long timestamp = telemetryValueInfo.getTimestamp();
                long key = timestamp / intervalTime + 1;
                if (valueMap.containsKey(key)) {
                    valueStatisticsInfo = valueMap.get(key);
                } else {
                    valueStatisticsInfo = new ValueStatisticsInfo();
                    //SECOND
                    valueStatisticsInfo.setTime(key * intervalTime / 1000);
                }
                int count = valueStatisticsInfo.getCount();
                valueStatisticsInfo.setCount(++count);
                valueStatisticsInfo.setTotal(valueStatisticsInfo.getTotal() + telemetryValueInfo.getValue());
                valueMap.put(key, valueStatisticsInfo);
            }
            List<Long> keyList = Lists.newArrayList();
            for (Long key : valueMap.keySet()) {
                keyList.add(key);
            }
            for (int m = 0; m < keyList.size() - 1; m++) {
                long key1 = keyList.get(m);
                long key2 = keyList.get(m + 1);
                if ((key2 - key1) * (intervalTime / 1000) > pointInterval) {
                    valueMap.put((key1 + key2) / 2, null);
                }
            }

            long end = now / 1000L;
            Long listEndKey = keyList.get(keyList.size() - 1);
            ValueStatisticsInfo var1 = valueMap.get(endKey);
            if ((endKey - listEndKey) * (intervalTime / 1000L) > pointInterval) {
                long key1 = (endKey + listEndKey) / 2;
                valueMap.put((key1), null);
                valueMap.put(end, var1);
            }
            if (endKey == listEndKey) {
                valueMap.remove(endKey);
                valueMap.put(end, var1);
            }
            if (endKey > listEndKey && (endTime - startTime) > TimeConstant.ONE_DAY_MILLISECOND) {
                valueMap.put(end, var1);
            }

            Long listStartKey = keyList.get(0);
            if ((listStartKey - startKey) * (intervalTime / 1000L) > pointInterval) {
                long key2 = (listStartKey + startKey) / 2;
                valueMap.put((key2), null);
                valueMap.put(startKey, null);
            }
            if (listStartKey > startKey && (endTime - startTime) > TimeConstant.ONE_DAY_MILLISECOND) {
                valueMap.put(startKey, null);
            }

            List<Object[]> list = Lists.newArrayList();
            for (Long key : valueMap.keySet()) {
                Object[] objects = new Object[2];
                ValueStatisticsInfo valueStatisticsInfo = valueMap.get(key);
                if (key < now / 10000) {
                    objects[0] = key * (intervalTime / 1000);
                } else {
                    objects[0] = key;
                }
                if (valueStatisticsInfo != null) {
                    objects[1] = StringUtil.divide(valueStatisticsInfo.getTotal(), valueStatisticsInfo.getCount(), 2);
                }
                list.add(objects);
            }
            chartDataListInfo.setValues(list);
            listInfoList.add(chartDataListInfo);
        }
        return listInfoList;
    }


    @Override
    public List<TelemetryDataInfo> getTelemetryDataListCallback(String deviceEui, int channelIndex, int measurementId, int limit, long startTime, long endTime, TelemetryDataCallback var) throws BaseException {
        Set<String> keys = Sets.newHashSet();
        List<TelemetryDataInfo> telemetryDataList = Lists.newArrayList();
        if (endTime > 0 || startTime > 0) {
            telemetryDataList = getTelemetryDataList(deviceEui, channelIndex, measurementId, limit, startTime, endTime);
            if (endTime == 0) {
                for (TelemetryDataInfo telemetryDataInfo : telemetryDataList) {
                    List<TelemetryValueInfo> valueList = telemetryDataInfo.getValueList();
                    valueList.stream().map(x ->
                            new TelemetryDataResult()
                                    .setValue(x.getValue())
                                    .setTimestamp(x.getTimestamp())
                                    .setMeasurementId(telemetryDataInfo.getMeasurementId())
                                    .setChannelIndex(telemetryDataInfo.getChannelIndex())
                                    .setDeviceEui(telemetryDataInfo.getDeviceEui())
                    ).forEach(x -> {
                        keys.add(x.getChannelIndex() + "-" + x.getMeasurementId());
                    });

                }
            } else {
                return telemetryDataList;
            }
        } else {
            if (measurementId < 1 || channelIndex < 1) {
                device.getDeviceChannel(deviceEui).getChannels()
                        .stream()
                        .filter(c -> channelIndex > 0 ? c.getChannelIndex() == channelIndex : true)
                        .forEach(x -> {
                            x.getMeasurementIds()
                                    .stream()
                                    .filter(m -> measurementId > 0 ? m == measurementId : true)
                                    .forEach(m -> {
                                        keys.add(x.getChannelIndex() + "-" + m);
                                    });
                        });
            } else {
                keys.add(channelIndex + "-" + measurementId);
            }

        }
        if (var == null) {
            return telemetryDataList;
        }

        List<Map<String, TelemetryDataCallback>> mapList = CallBackMapCache.getSimpleCache().get(deviceEui);
        if (mapList == null) {
            mapList = Lists.newArrayListWithExpectedSize(1);
        }
        Map<String, TelemetryDataCallback> map = Maps.newHashMapWithExpectedSize(keys.size());

        long organizationId = organizationManager.getOrganizationId();
        String[] topics = new String[keys.size()];
        int[] qos = new int[keys.size()];
        int i = 0;
        for (String key : keys) {
            String[] split = key.split("-");
            topics[i] = "/device_sensor_data/" + organizationId + "/" + deviceEui + "/" + split[0] + "/+/" + split[1];
            map.put(topics[i], var);
            qos[i] = 1;
            i++;
        }
        mapList.add(map);
        int region = openApiConfig.region;
        String host = CN_HOST;
        if (region == 2) {
            host = CC_HOST;
        }

        String clientId = "org-" + organizationId + "-default";
        String userName = "org-" + organizationId;

        mqttManager.initMqtt(new MqttConnectionInfo()
                .setMqttCallback(new SensorMqttCallback())
                .setTopics(topics)
                .setPassWord(openApiConfig.accessKey)
                .setUserName(userName)
                .setOrgId(organizationId)
                .setHost(host)
                .setQos(qos)
                .setClientId(clientId)
        );
        CallBackMapCache.getSimpleCache().put(deviceEui, mapList);
        return telemetryDataList;
    }


    /**
     * 一天内返回所有点
     * 大于1天按interval 平均计算点值
     * @param interval / MINUTE
     * @return MILLISECOND
     */
    public long getInterval(long startTime, long endTime, int interval) {
        if (endTime - startTime <= TimeConstant.ONE_DAY_MILLISECOND) {
            return 1000L;
        }
        return interval * TimeConstant.ONE_MINUTE_MILLISECOND;
    }

    /**
     * 1.每缺2个点补一个
     * 2.或者每两个钟补一个
     *
     * @param interval : MINUTE
     * @return
     */
    public long getPointInterval(int interval) {
        if (interval < 60) {
            return 7200L;
        }
        return interval * 2 * TimeConstant.ONE_MINUTE_SECOND;
    }
}
