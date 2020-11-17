package cc.seeed.sensecap.model.result;

import cc.seeed.sensecap.actions.Device;
import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.device.DeviceBaseInfo;
import cc.seeed.sensecap.model.device.DeviceChannelInfo;
import cc.seeed.sensecap.model.device.DeviceInfo;
import cc.seeed.sensecap.model.device.DeviceStatusInfo;
import cc.seeed.sensecap.queries.device.DeviceQuery;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/11/13 14:46
 * @Version V1.0
 */
public class DeviceResult extends GenericResult {
    private DeviceQuery deviceQuery;
    private Device device;

    public DeviceResult(DeviceQuery deviceQuery, OpenApiConfig openApiConfig) {
        this.deviceQuery = deviceQuery;
        this.device = new Device(openApiConfig);
    }

    public List<DeviceBaseInfo> deviceList() throws BaseException {
        List<DeviceBaseInfo> deviceList = device.getDeviceList(deviceQuery.getDeviceType(), deviceQuery.getGroupUUID());
        return deviceList;
    }

    public List<DeviceInfo> deviceInfos() throws BaseException {
        List<DeviceInfo> deviceInfo = device.getDeviceInfo(deviceQuery.getDeviceEuis(), deviceQuery.getDeviceType());
        return deviceInfo;
    }

    public List<DeviceChannelInfo> channelList() throws BaseException {
        List<DeviceChannelInfo> deviceChannelList = device.getDeviceChannelList(deviceQuery.getDeviceEuis());
        return deviceChannelList;
    }

    public List<DeviceStatusInfo> runningStatusList() throws BaseException {
        List<DeviceStatusInfo> deviceRunningStatusList = device.getDeviceRunningStatusList(deviceQuery.getDeviceEuis());
        return deviceRunningStatusList;
    }


}
