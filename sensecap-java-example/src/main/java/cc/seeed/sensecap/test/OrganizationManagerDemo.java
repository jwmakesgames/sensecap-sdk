package cc.seeed.sensecap.test;

import cc.seeed.sensecap.SenseCAPClient;
import cc.seeed.sensecap.SenseCAPClientBuilder;
import cc.seeed.sensecap.common.enums.RegionType;
import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.exception.BaseException;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/25 14:49
 * @Version V1.0
 */
public class OrganizationManagerDemo {

    private static SenseCAPClient senseCAPClient;

    static {
        String accessId = " ";
        String accessKey = " ";
        int region = RegionType.SENSECAP_CC.getRegion();
        OpenApiConfig openApiConfig = new OpenApiConfig(accessId, accessKey, region);
        senseCAPClient = new SenseCAPClientBuilder().buildConfig(openApiConfig);
    }


    public static void main(String[] args) throws BaseException {
        getOrganizationId();
    }

    //获取组织ID
    public static void getOrganizationId() throws BaseException {
        long organizationId = senseCAPClient.getOrganizationManager()
                .getOrganizationId();
        System.out.println(organizationId);
    }
}
