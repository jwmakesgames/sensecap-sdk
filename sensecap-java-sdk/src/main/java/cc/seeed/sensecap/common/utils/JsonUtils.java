package cc.seeed.sensecap.common.utils;

import cc.seeed.sensecap.exception.BaseException;
import cc.seeed.sensecap.model.resp.HttpResponseMessage;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/18 11:07
 * @Version V1.0
 */
public class JsonUtils {


    public static <T> T parseObject(HttpResponseMessage message, Class<T> clazz) throws BaseException {
        if (message.getCode() != 0) {
            throw new BaseException(message.getMsg());
        }
        if (StringUtils.isBlank(message.getData().toString())) {
            throw new NullPointerException();
        }
        return JSON.parseObject(message.getData().toString(), clazz);
    }

    public static JSONObject toJsonObject(HttpResponseMessage message) throws BaseException {
        if (message.getCode() != 0) {
            throw new BaseException(message.getMsg());
        }
        return JSON.parseObject(message.getData().toString());
    }


    public static <T> List<T> parseArray(HttpResponseMessage message, Class<T> clazz) throws BaseException {
        if (message.getCode() != 0) {
            throw new BaseException(message.getMsg());
        }
        return JSON.parseArray(message.getData().toString(), clazz);
    }
}
