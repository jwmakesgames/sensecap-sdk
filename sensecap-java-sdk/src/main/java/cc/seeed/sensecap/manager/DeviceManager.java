package cc.seeed.sensecap.manager;

import cc.seeed.sensecap.actions.Device;
import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.interfaces.SenseCAPDevice;
import cc.seeed.sensecap.model.device.*;
import cc.seeed.sensecap.queries.device.DeviceBinder;
import cc.seeed.sensecap.queries.device.DeviceQuery;
import cc.seeed.sensecap.queries.device.DeviceQueryBuilder;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/25 14:46
 * @Version V1.0
 */

public final class DeviceManager implements SenseCAPDevice {
    private final OpenApiConfig openApiConfig;
    private final Device device;

    public DeviceManager(OpenApiConfig openApiConfig) {
        this.openApiConfig = openApiConfig;
        this.device = new Device(openApiConfig);
    }

    public DeviceQueryBuilder createDeviceQuery() {
        return DeviceQuery.newBuilder(openApiConfig);
    }

    public DeviceBinder.DeviceBinderBuilder createBinder() {
        return DeviceBinder.newBuilder(openApiConfig);
    }

    @Override
    public void moveDevices(String groupUUID, List<String> deviceEuis) throws BaseException {
        device.moveDevices(groupUUID, deviceEuis);
    }

    @Override
    public List<DeviceBaseInfo> getDeviceList(int deviceType, String groupUUID) throws BaseException {
        return device.getDeviceList(deviceType, groupUUID);
    }

    @Override
    public List<DeviceInfo> getDeviceInfo(List<String> deviceEuis, int deviceType) throws BaseException {
        return device.getDeviceInfo(deviceEuis, deviceType);
    }

    @Override
    public List<DeviceChannelInfo> getDeviceChannelList(List<String> deviceEuis) throws BaseException {
        return device.getDeviceChannelList(deviceEuis);
    }

    @Override
    public List<DeviceStatusInfo> getDeviceRunningStatusList(List<String> deviceEuis) throws BaseException {
        return device.getDeviceRunningStatusList(deviceEuis);
    }

    @Override
    public List<DeviceMeasurementInfo> getDeviceMeasurementList() throws BaseException {
        return device.getDeviceMeasurementList();
    }

    @Override
    public boolean bindDevice(String eui, String code, String deviceName, String groupUUID, String longitude, String latitude) throws BaseException {
        return device.bindDevice(eui, code, deviceName, groupUUID, longitude, latitude);
    }

    @Override
    public boolean deleteDevices(List<String> deviceEuis) throws BaseException {
        return device.deleteDevices(deviceEuis);
    }

    public DeviceChannelInfo getDeviceChannel(String deviceEui) throws BaseException {
        return device.getDeviceChannel(deviceEui);
    }
}

