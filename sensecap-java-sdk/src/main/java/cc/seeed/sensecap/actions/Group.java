package cc.seeed.sensecap.actions;


import cc.seeed.sensecap.api.url.PathConst;
import cc.seeed.sensecap.config.OpenApiConfig;
import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.exception.HttpResponseMessageCode;
import cc.seeed.sensecap.interfaces.SenseCAPGroup;
import cc.seeed.sensecap.model.group.GroupInfo;
import cc.seeed.sensecap.model.resp.HttpResponseMessage;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author AG
 * @Description
 * @Date 2020/9/9 14:29
 * @Version V1.0
 */

public class Group implements SenseCAPGroup {

    private OpenApiConfig openApiConfig;

    public Group(OpenApiConfig openApiConfig) {
        this.openApiConfig = openApiConfig;
    }

    public GroupInfo create(String groupName) throws BaseException {

        Map<String, String> bodys = Maps.newHashMapWithExpectedSize(1);
        if (StringUtils.isBlank(groupName)) {
            throw new BaseException(HttpResponseMessageCode.NOT_PARAM);
        }
        bodys.put("name", groupName);
        try {
            HttpResponseMessage operation = openApiConfig.apiRequestOperation.postOperation(PathConst.CREATE_GROUP, bodys);
            if (operation.getCode() > 0) {
                throw new BaseException(operation.getCode(), operation.getMsg());
            }
            GroupInfo groupInfo = JSON.parseObject(operation.getData().toString(), GroupInfo.class);
            return groupInfo;
        } catch (NullPointerException var1) {
            throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
        } catch (IOException var2) {
            throw new BaseException(var2.getMessage());
        }
    }


    @Override
    public List<GroupInfo> getGroupList() throws BaseException {
        Map<String, String> bodys = Maps.newHashMapWithExpectedSize(1);

        try {
            HttpResponseMessage operation = openApiConfig.apiRequestOperation.postOperation(PathConst.LIST_GROUP, bodys);
            if (operation.getCode() > 0) {
                throw new BaseException(operation.getCode(), operation.getMsg());
            }
            List<GroupInfo> groupInfos = JSON.parseArray(operation.getData().toString(), GroupInfo.class);
            return groupInfos;
        } catch (NullPointerException var1) {
            throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
        } catch (IOException var2) {
            throw new BaseException(var2.getMessage());
        }
    }


    public void rename(String groupUUID, String groupName) throws BaseException {

        if (StringUtils.isBlank(groupName)) {
            throw new BaseException(HttpResponseMessageCode.NOT_PARAM);
        }
        if (StringUtils.isBlank(groupUUID)) {
            throw new BaseException(HttpResponseMessageCode.NOT_PARAM);
        }
        Map<String, String> body = Maps.newHashMapWithExpectedSize(2);
        body.put("group_uuid", groupUUID);
        body.put("name", groupName);
        try {
            HttpResponseMessage httpResponseMessage = openApiConfig.apiRequestOperation.postOperation(PathConst.UPDATE_GROUP, body);
            if (httpResponseMessage.getCode() > 0) {
                throw new BaseException(httpResponseMessage.getCode(), httpResponseMessage.getMsg());
            }
        } catch (NullPointerException var1) {
            throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
        } catch (IOException var2) {
            throw new BaseException(var2.getMessage());
        }
    }

    public void remove(String groupUUID) throws BaseException {
        if (StringUtils.isBlank(groupUUID)) {
            throw new BaseException(HttpResponseMessageCode.NOT_PARAM);
        }
        Map<String, String> query = Maps.newHashMapWithExpectedSize(1);
        query.put("group_uuid", groupUUID);
        try {
            HttpResponseMessage httpResponseMessage = openApiConfig.apiRequestOperation.postOperation(query, PathConst.DELETE_GROUP);
            if (httpResponseMessage.getCode() > 0) {
                throw new BaseException(httpResponseMessage.getCode(), httpResponseMessage.getMsg());
            }
        } catch (NullPointerException var1) {
            throw new BaseException(HttpResponseMessageCode.OPEN_API_RETURN_NULL);
        } catch (IOException var2) {
            throw new BaseException(var2.getMessage());
        }
    }
}
