package cc.seeed.sensecap.test;
import cc.seeed.sensecap.SenseCAPClient;
import cc.seeed.sensecap.SenseCAPClientBuilder;
import cc.seeed.sensecap.common.enums.RegionType;
import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.device.DeviceChannelInfo;
import cc.seeed.sensecap.model.device.DeviceInfo;
import cc.seeed.sensecap.model.device.DeviceStatusInfo;
import cc.seeed.sensecap.model.result.DeviceResult;
import com.google.common.collect.Lists;
import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/25 15:05
 * @Version V1.0
 */
public class DeviceManagerDemo {

    private static SenseCAPClient senseCAPClient;
    private static String deviceEui=" ";

    static {
        String accessId = " ";
        String accessKey = " ";
        int region = RegionType.SENSECAP_CC.getRegion();
        OpenApiConfig openApiConfig = new OpenApiConfig(accessId, accessKey, region);
        senseCAPClient = new SenseCAPClientBuilder().buildConfig(openApiConfig);
    }

    public static void main(String[] args) throws BaseException {
        //moveDeivces();
        //getDeviceList();
        //getDeviceInfoList();
        //getDeviceChannelList();
        getDeviceRunningStatusList();

    }

    public static void moveDeivces() throws BaseException {
        List<String> deviceEuis = Lists.newArrayList();
        deviceEuis.add(deviceEui);
        senseCAPClient.getDeviceManager().moveDevices("C1B08E961947A308", deviceEuis);
    }


    public static void getDeviceList() throws BaseException {
        DeviceResult deviceResult = senseCAPClient.getDeviceManager().createDeviceQuery()
                .deviceType(2)
                .groupUUID("")
                .build()
                .execute();
        List list = deviceResult.deviceList();
        System.out.println(list.toString());
    }


    public static void getDeviceInfoList() throws BaseException {
        List<String> deviceEuis = Lists.newArrayList();
        deviceEuis.add(deviceEui);
        DeviceResult deviceResult = senseCAPClient.getDeviceManager().createDeviceQuery()
                .deviceEuis(deviceEuis)
                .deviceType(2)
                .build()
                .execute();
        List<DeviceInfo> deviceInfos = deviceResult.deviceInfos();
        System.out.println(deviceInfos.toString());

    }


    public static void getDeviceChannelList() throws BaseException {
        List<String> deviceEuis = Lists.newArrayList();
        deviceEuis.add(deviceEui);
        DeviceResult deviceResult = senseCAPClient.getDeviceManager().createDeviceQuery()
                .deviceEuis(deviceEuis)
                .build()
                .execute();
        List<DeviceChannelInfo> deviceChannelInfos = deviceResult.channelList();
        System.out.println(deviceChannelInfos.toString());
    }

    public static void getDeviceRunningStatusList() throws BaseException {
        List<String> deviceEuis = Lists.newArrayList();
        deviceEuis.add(deviceEui);
        DeviceResult deviceResult = senseCAPClient.getDeviceManager().createDeviceQuery()
                .deviceEuis(deviceEuis)
                .build()
                .execute();
        List<DeviceStatusInfo> deviceStatusInfos = deviceResult.runningStatusList();
        System.out.println(deviceStatusInfos);
    }
    //eui, code, deviceName, groupUUID, longitude, latitude
    public static void bindDevice() throws BaseException {
        senseCAPClient.getDeviceManager().createBinder()
                .eui("")
                .code("")
                .deviceName("")
                .groupUUID("")
                .longitude("")
                .latitude("")
                .build()
                .bind();
    }

    static void deleteDevices() throws BaseException {
        List<String> deviceEuis = Lists.newArrayList();
        deviceEuis.add(deviceEui);
        boolean b = senseCAPClient.getDeviceManager().deleteDevices(deviceEuis);
    }

}
