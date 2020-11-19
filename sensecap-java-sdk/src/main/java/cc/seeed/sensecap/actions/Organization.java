package cc.seeed.sensecap.actions;

import cc.seeed.sensecap.api.url.PathConst;
import cc.seeed.sensecap.common.utils.JsonUtils;
import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.exception.HttpResponseMessageCode;
import cc.seeed.sensecap.interfaces.SenseCAPOrganization;
import cc.seeed.sensecap.model.resp.HttpResponseMessage;
import com.alibaba.fastjson.JSONObject;

import java.io.IOException;

/**
 * @Author AG
 * @Description
 * @Date 2020/11/13 10:22
 * @Version V1.0
 */
public class Organization implements SenseCAPOrganization {

    private final OpenApiConfig openApiConfig;

    public Organization(OpenApiConfig openApiConfig) {
        this.openApiConfig = openApiConfig;
    }

    @Override
    public long getOrganizationId() throws BaseException {
        try {
            HttpResponseMessage httpResponseMessage = openApiConfig.apiRequestOperation.getOperation(PathConst.VIEW_ORGANIZATION);
            JSONObject jsonObject = JsonUtils.toJsonObject(httpResponseMessage);
            Long orgId = jsonObject.getLong("orgId");
            return orgId;
        } catch (NullPointerException var1) {
            throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
        } catch (IOException var2) {
            throw new BaseException(var2.getMessage());
        }
    }
}
