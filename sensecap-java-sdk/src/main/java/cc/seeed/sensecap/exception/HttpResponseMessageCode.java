package cc.seeed.sensecap.exception;

/**
 * 接口返回结果 编号
 * com.feidiao.jancee.fdiot.app.common.vo
 *
 * @author jancee
 * @date 2017/7/7
 */
public enum HttpResponseMessageCode {

    //Base
    SUCCESS(0, "Success"),
    ERROR(1, "Error"),


    ERR_SERVER_404(404, "Url not found"),
    ERR_SERVER_500(500, "Internal server error"),




    OPEN_API_RETURN_NULL(10001,"NoResponse"),
    InvalidArgument(10002,"InvalidArgument"),
    NOT_PARAM(10003, "MissingArgument"),

    ValidateFail(10004,"ValidateFail"),

    ;
    private int code;
    private String msg;

    HttpResponseMessageCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

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
        this.msg = msg;
    }


    @Override
    public String toString() {
        return code + "";
    }
}
