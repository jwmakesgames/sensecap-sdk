package cc.seeed.sensecap.config;

import cc.seeed.sensecap.common.enums.RegionType;
import cc.seeed.sensecap.common.utils.ApiRequestOperation;
import cc.seeed.sensecap.common.utils.HttpUtils;
import cc.seeed.sensecap.model.auth.OrganizationAuth;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/13 16:56
 * @Version V1.0
 */
public final class OpenApiConfig {

    /**
     * 密钥ID
     */
    public final String accessId;

    /**
     * API访问密钥
     */
    public final String accessKey;

    /**
     * 参考 RegionType
     */
    public final int region;

    /**
     * 请求地址
     */
    public final String domain;

    public final String token;

    public final ApiRequestOperation apiRequestOperation;


    public OpenApiConfig(String accessId, String accessKey, int region) {
        this.accessId = accessId;
        this.accessKey = accessKey;
        this.region = region;
        this.domain = RegionType.getDomainByRegion(region);
        this.token = HttpUtils.getHeader(accessId, accessKey);
        apiRequestOperation = new ApiRequestOperation(domain, token);
    }


}
