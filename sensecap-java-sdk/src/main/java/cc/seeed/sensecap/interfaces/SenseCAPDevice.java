package cc.seeed.sensecap.interfaces;

import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.device.*;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/11/13 14:19
 * @Version V1.0
 */
public interface SenseCAPDevice {

    void moveDevices(String groupUUID, List<String> deviceEuis) throws BaseException;

    List<DeviceBaseInfo> getDeviceList(int deviceType, String groupUUID) throws BaseException;

    List<DeviceInfo> getDeviceInfo(List<String> deviceEuis, int deviceType) throws BaseException;

    List<DeviceChannelInfo> getDeviceChannelList(List<String> deviceEuis) throws BaseException;

    List<DeviceStatusInfo> getDeviceRunningStatusList(List<String> deviceEuis) throws BaseException;

    List<DeviceMeasurementInfo> getDeviceMeasurementList() throws BaseException;

    boolean bindDevice(String eui, String code, String deviceName, String groupUUID, String longitude, String latitude) throws BaseException;

    boolean deleteDevices(List<String> deviceEuis) throws BaseException;
}
