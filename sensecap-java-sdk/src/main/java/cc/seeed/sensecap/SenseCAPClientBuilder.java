package cc.seeed.sensecap;

import cc.seeed.sensecap.config.OpenApiConfig;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/1 15:38
 * @Version V1.0
 */
public class SenseCAPClientBuilder implements SenseCAP {


    public SenseCAPClient buildConfig(String accessId, String accessKey, int region) {
        return new SenseCAPClient(accessId, accessKey, region);
    }

    public static SenseCAPClient buildConfig(OpenApiConfig openApiConfig) {
        return new SenseCAPClient(openApiConfig);
    }

}
