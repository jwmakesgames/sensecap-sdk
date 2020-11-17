package cc.seeed.sensecap.test;

import cc.seeed.sensecap.SenseCAPClient;
import cc.seeed.sensecap.SenseCAPClientBuilder;
import cc.seeed.sensecap.common.enums.RegionType;
import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.device.*;
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

    static {
        String accessId = "KUFE0W52P8QJX8DB";
        String accessKey = "28E07CA0D52B48C69886D7ACF1AD683F3690EE0E1E0C4201B88A26A61344625C";
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
        deviceEuis.add("2CF7F12122500055");
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
        deviceEuis.add("2CF7F12122500055");
        DeviceResult deviceResult = senseCAPClient.getDeviceManager().createDeviceQuery()
                .deviceEuis(deviceEuis)
                .deviceType(2)
                .build()
                .execute();
        List<DeviceInfo> deviceInfos = deviceResult.deviceInfos();
        System.out.println(deviceInfos.toString());

    }


    static void getDeviceChannelList() throws BaseException {
        List<String> deviceEuis = Lists.newArrayList();
        deviceEuis.add("2CF7F12122500055");
        DeviceResult deviceResult = senseCAPClient.getDeviceManager().createDeviceQuery()
                .deviceEuis(deviceEuis)
                .build()
                .execute();
        List<DeviceChannelInfo> deviceChannelInfos = deviceResult.channelList();
        System.out.println(deviceChannelInfos.toString());
    }

    static void getDeviceRunningStatusList() throws BaseException {
        List<String> deviceEuis = Lists.newArrayList();
        deviceEuis.add("2CF7F12122500055");
        DeviceResult deviceResult = senseCAPClient.getDeviceManager().createDeviceQuery()
                .deviceEuis(deviceEuis)
                .build()
                .execute();
        List<DeviceStatusInfo> deviceStatusInfos = deviceResult.runningStatusList();
        System.out.println(deviceStatusInfos);
    }
    //eui, code, deviceName, groupUUID, longitude, latitude
    static void bindDevice() throws BaseException {
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
        deviceEuis.add("2CF7F12122500055");
        boolean b = senseCAPClient.getDeviceManager().deleteDevices(deviceEuis);
    }

}
