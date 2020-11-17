package cc.seeed.sensecap.actions;

import cc.seeed.sensecap.api.url.PathConst;
import cc.seeed.sensecap.common.utils.JsonUtils;
import cc.seeed.sensecap.common.utils.StringUtil;
import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.exception.HttpResponseMessageCode;
import cc.seeed.sensecap.interfaces.SenseCAPDevice;
import cc.seeed.sensecap.model.device.*;
import cc.seeed.sensecap.model.resp.HttpResponseMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author AG
 * @Description
 * @Date 2020/11/13 14:20
 * @Version V1.0
 */
public class Device implements SenseCAPDevice {

    private final static int DEVICE_MAX_SIZE = 50;

    private OpenApiConfig openApiConfig;

    public Device(OpenApiConfig openApiConfig) {
        this.openApiConfig = openApiConfig;
    }

    public void moveDevices(String groupUUID, List<String> deviceEuis) throws BaseException {

        if (CollectionUtils.isEmpty(deviceEuis)) {
            throw new BaseException("MissingArgument : deviceEuis");
        }
        List<List<String>> subLists = getSubLists(deviceEuis, DEVICE_MAX_SIZE);
        for (List<String> devices : subLists) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("group_uuid", groupUUID);
                jsonObject.put("devices", devices);
                HttpResponseMessage httpResponseMessage = openApiConfig.apiRequestOperation.postJsonOperation(PathConst.MOVE_DEVICES_TO, jsonObject.toJSONString());
                if (httpResponseMessage.getCode() != 0) {
                    throw new BaseException(httpResponseMessage.getMsg());
                }
            } catch (NullPointerException var1) {
                throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
            } catch (IOException var2) {
                throw new BaseException(var2.getMessage());
            }
        }
    }


    public List<DeviceBaseInfo> getDeviceList(int deviceType, String groupUUID) throws BaseException {
        List<DeviceBaseInfo> list = null;
        Map<String, String> query = Maps.newHashMapWithExpectedSize(2);
        if (deviceType < 1) {
            throw new BaseException("MissingArgument");
        }
        if (deviceType > 0) {
            query.put("device_type", String.valueOf(deviceType));
        }

        query.put("group_uuid", groupUUID);
        try {
            HttpResponseMessage httpResponseMessage = openApiConfig.apiRequestOperation.getOperation(PathConst.LIST_DEVICES, query);
            list = JsonUtils.parseArray(httpResponseMessage, DeviceBaseInfo.class);
        } catch (NullPointerException var1) {
            throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
        } catch (IOException var2) {
            throw new BaseException(var2.getCause().getMessage());
        }
        return list;
    }


    public List<DeviceInfo> getDeviceInfo(List<String> deviceEuis, int deviceType) throws BaseException {
        List<DeviceInfo> list = Lists.newArrayList();
        List<List<String>> subLists = getSubLists(deviceEuis, DEVICE_MAX_SIZE);
        for (List<String> devices : subLists) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("device_euis", devices);
                if (deviceType > 0) {
                    jsonObject.put("device_type", deviceType);
                }

                HttpResponseMessage httpResponseMessage = openApiConfig.apiRequestOperation.postJsonOperation(PathConst.VIEW_DEVICES, jsonObject.toJSONString());
                if (httpResponseMessage.getCode() != 0) {
                    throw new BaseException(httpResponseMessage.getMsg());
                }
                Object data = httpResponseMessage.getData();
                JSONArray objects = JSON.parseArray(data.toString());

                for (int i = 0; i < objects.size(); i++) {
                    JSONObject object = objects.getJSONObject(i);
                    DeviceInfo deviceInfo = JSON.parseObject(object.toJSONString(), DeviceInfo.class);
                    PositionInfo position = object.getObject("position", PositionInfo.class);
                    SimInfo simInfo = object.getObject("sim", SimInfo.class);
                    deviceInfo.setPosition(position);
                    deviceInfo.setSim(simInfo);
                    list.add(deviceInfo);
                }
            } catch (NullPointerException var1) {
                throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
            } catch (IOException var2) {
                throw new BaseException(var2.getCause().getMessage());
            }
        }
        return list;
    }


    public List<DeviceChannelInfo> getDeviceChannelList(List<String> deviceEuis) throws BaseException {
        List<List<String>> subLists = getSubLists(deviceEuis, DEVICE_MAX_SIZE);
        List<DeviceChannelInfo> list = Lists.newArrayList();
        for (List<String> devices : subLists) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("device_euis", devices);
                HttpResponseMessage httpResponseMessage = openApiConfig.apiRequestOperation.postJsonOperation(PathConst.LIST_DEVICE_CHANNELS, jsonObject.toJSONString());
                if (httpResponseMessage.getCode() != 0) {
                    throw new BaseException(httpResponseMessage.getMsg());
                }
                JSONArray objects = JSON.parseArray(httpResponseMessage.getData().toString());

                for (int i = 0; i < objects.size(); i++) {
                    JSONObject deviceChannelJson = objects.getJSONObject(i);
                    DeviceChannelInfo deviceChannelInfo = new DeviceChannelInfo();
                    deviceChannelInfo.setDeviceEui(deviceChannelJson.getString("device_eui"));
                    JSONArray channels = deviceChannelJson.getJSONArray("channels");
                    List<ChannelInfo> channelInfos = Lists.newArrayListWithExpectedSize(channels.size());
                    for (int j = 0; j < channels.size(); j++) {
                        JSONObject channelsJSONObject = channels.getJSONObject(j);
                        ChannelInfo channelInfo = JSON.parseObject(channelsJSONObject.toJSONString(), ChannelInfo.class);
                        JSONArray jsonMeasurementIds = channelsJSONObject.getJSONArray("measurement_ids");
                        List<Integer> measurementIds = JSON.parseArray(jsonMeasurementIds.toJSONString(), Integer.class);
                        channelInfo.setMeasurementIds(measurementIds);
                        channelInfos.add(channelInfo);
                    }
                    deviceChannelInfo.setChannels(channelInfos);
                    list.add(deviceChannelInfo);
                }


            } catch (NullPointerException var1) {
                throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
            } catch (IOException var2) {
                throw new BaseException(var2.getCause().getMessage());
            }
        }
        return list;
    }

    public DeviceChannelInfo getDeviceChannel(String deviceEui) throws BaseException {
        try {
            List<String> device = Lists.newArrayListWithExpectedSize(1);
            device.add(deviceEui);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("device_euis", device);
            HttpResponseMessage httpResponseMessage = openApiConfig.apiRequestOperation.postJsonOperation(PathConst.LIST_DEVICE_CHANNELS, jsonObject.toJSONString());
            if (httpResponseMessage.getCode() != 0) {
                throw new BaseException(httpResponseMessage.getMsg());
            }
            JSONArray objects = JSON.parseArray(httpResponseMessage.getData().toString());
            JSONObject deviceChannelJson = objects.getJSONObject(0);
            DeviceChannelInfo deviceChannelInfo = new DeviceChannelInfo();
            deviceChannelInfo.setDeviceEui(deviceChannelJson.getString("device_eui"));
            JSONArray channels = deviceChannelJson.getJSONArray("channels");
            List<ChannelInfo> channelInfos = Lists.newArrayListWithExpectedSize(channels.size());
            for (int j = 0; j < channels.size(); j++) {
                JSONObject channelsJSONObject = channels.getJSONObject(j);
                ChannelInfo channelInfo = JSON.parseObject(channelsJSONObject.toJSONString(), ChannelInfo.class);
                JSONArray jsonMeasurementIds = channelsJSONObject.getJSONArray("measurement_ids");
                List<Integer> measurementIds = JSON.parseArray(jsonMeasurementIds.toJSONString(), Integer.class);
                channelInfo.setMeasurementIds(measurementIds);
                channelInfos.add(channelInfo);
            }
            deviceChannelInfo.setChannels(channelInfos);
            return deviceChannelInfo;


        } catch (NullPointerException var1) {
            throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
        } catch (IOException var2) {
            throw new BaseException(var2.getCause().getMessage());
        }
    }

    public List<DeviceStatusInfo> getDeviceRunningStatusList(List<String> deviceEuis) throws BaseException {
        List<DeviceStatusInfo> list = Lists.newArrayList();
        List<List<String>> subLists = getSubLists(deviceEuis, DEVICE_MAX_SIZE);

        for (List<String> devices : subLists) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("device_euis", devices);
                HttpResponseMessage httpResponseMessage = openApiConfig.apiRequestOperation.postJsonOperation(PathConst.VIEW_DEVICE_RUNNING_STATUS, jsonObject.toJSONString());
                List<DeviceStatusInfo> deviceStatusInfos = JsonUtils.parseArray(httpResponseMessage, DeviceStatusInfo.class);
                list.addAll(deviceStatusInfos);
            } catch (NullPointerException var1) {
                throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
            } catch (IOException var2) {
                throw new BaseException(var2.getCause().getMessage());
            }
        }
        return list;
    }


    public List<DeviceMeasurementInfo> getDeviceMeasurementList() throws BaseException {
        try {
            HttpResponseMessage operation = openApiConfig.apiRequestOperation.getOperation(PathConst.LIST_MEASUREMENT_REFERENCE);
            if (operation.getCode() != 0) {
                throw new BaseException(operation.getMsg());
            }
            JSONArray objects = JSON.parseArray(operation.getData().toString());
            List<DeviceMeasurementInfo> list = Lists.newArrayListWithExpectedSize(objects.size());
            for (int i = 0; i < objects.size(); i++) {
                JSONObject jsonObject = objects.getJSONObject(i);
                DeviceMeasurementInfo deviceMeasurementInfo = JSON.parseObject(jsonObject.toJSONString(), DeviceMeasurementInfo.class);
                JSONArray jsonSensorMeasurement = jsonObject.getJSONArray("sensor_measurement");
                List<MeasurementInfo> measurementInfos = JSON.parseArray(jsonSensorMeasurement.toJSONString(), MeasurementInfo.class);
                deviceMeasurementInfo.setSensorMeasurement(measurementInfos);
                list.add(deviceMeasurementInfo);
            }
            return list;
        } catch (NullPointerException var1) {
            throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
        } catch (IOException var2) {
            throw new BaseException(var2.getCause().getMessage());
        }
    }


    public boolean bindDevice(String eui, String code, String deviceName, String groupUUID, String longitude, String latitude) throws BaseException {
        try {
            Map<String, String> body = Maps.newHashMapWithExpectedSize(6);
            if (StringUtils.isBlank(eui) || StringUtils.isBlank(code)) {
                throw new BaseException("MissingArgument");
            }

            body.put("eui", eui);
            body.put("code", code);
            if (StringUtils.isNotBlank(deviceName)) {
                body.put("device_name", deviceName);
            }
            if (StringUtils.isNotBlank(groupUUID)) {
                body.put("group_uuid", groupUUID);
            }
            if (StringUtils.isNotBlank(longitude)) {
                body.put("longitude", longitude);
            }
            if (StringUtils.isNotBlank(latitude)) {
                body.put("latitude", latitude);
            }


            HttpResponseMessage httpResponseMessage = openApiConfig.apiRequestOperation.postOperation(PathConst.BIND_DEVICE, body);
            if (httpResponseMessage.getCode() != 0) {
                throw new BaseException(httpResponseMessage.getMsg());
            }
            return true;
        } catch (NullPointerException var1) {
            throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
        } catch (IOException var2) {
            throw new BaseException(var2.getCause().getMessage());
        }

    }


    public boolean deleteDevices(List<String> deviceEuis) throws BaseException {
        List<List<String>> subLists = getSubLists(deviceEuis, DEVICE_MAX_SIZE);
        for (List<String> devices : subLists) {
            try {
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("device_euis", devices);
                HttpResponseMessage httpResponseMessage = openApiConfig.apiRequestOperation.postJsonOperation(PathConst.DELETE_DEVICES, jsonObject.toJSONString());
                if (httpResponseMessage.getCode() != 0) {
                    throw new BaseException(httpResponseMessage.getMsg());
                }
            } catch (NullPointerException var1) {
                throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
            } catch (IOException var2) {
                throw new BaseException(var2.getCause().getMessage());
            }

        }
        return true;
    }


    public boolean deleteDevice(String deviceEui) throws BaseException {
        try {
            List<String> device = Lists.newArrayListWithExpectedSize(1);
            device.add(deviceEui);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("device_euis", device);
            HttpResponseMessage httpResponseMessage = openApiConfig.apiRequestOperation.postJsonOperation(PathConst.DELETE_DEVICES, jsonObject.toJSONString());
            if (httpResponseMessage.getCode() != 0) {
                throw new BaseException(httpResponseMessage.getMsg());
            }
            return true;
        } catch (NullPointerException var1) {
            throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
        } catch (IOException var2) {
            throw new BaseException(var2.getCause().getMessage());
        }
    }


    /**
     * 切割设备数目最多一组50个
     *
     * @param allData
     * @param size
     * @return
     */
    public List<List<String>> getSubLists(List<String> allData, int size) throws BaseException {
        if (CollectionUtils.isEmpty(allData)) {
            throw new BaseException("MissingArgument");
        }
        allData = allData.stream().distinct().collect(Collectors.toList());
        List<List<String>> result = new ArrayList<>();
        for (int begin = 0; begin < allData.size(); begin = begin + size) {
            int end = (begin + size > allData.size() ? allData.size() : begin + size);
            result.add(allData.subList(begin, end));
        }
        return result;
    }
}
