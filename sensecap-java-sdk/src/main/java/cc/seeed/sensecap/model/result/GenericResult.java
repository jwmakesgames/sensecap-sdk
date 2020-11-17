package cc.seeed.sensecap.model.result;

import cc.seeed.sensecap.exception.HttpResponseMessageCode;
import org.apache.commons.lang3.StringUtils;

/**
 * @Author AG
 * @Description A generic result that contains some basic response options, such as code
 * @Date 2020/9/9 14:43
 * @Version V1.0
 */
public class GenericResult  {
    private int code;
    private String msg = "success";


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        if (StringUtils.isNotBlank(msg)) {
            this.msg = msg;
        }
    }

    GenericResult(String msg) {
        this.msg = msg;
    }

    GenericResult(int code, String msg) {
        this.msg = msg;
        this.code = code;
    }

    GenericResult() {

    }


    public static <T> T error(String msg) {
        return (T) new GenericResult(msg);
    }

    public static <T> T error(HttpResponseMessageCode httpResponseMessageCode) {
        return (T) new GenericResult(httpResponseMessageCode.getCode(), httpResponseMessageCode.getMsg());
    }

    public static <T> T error(int code, String msg) {
        return (T) new GenericResult(code, msg);
    }

    public static <T> T success() {
        return (T) new GenericResult();
    }

}
