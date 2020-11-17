package cc.seeed.sensecap.exception;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * @Author ascgoogle
 * @Description
 * @Date 2020/8/13 16:38
 * @Version V1.0
 */
public class BaseException extends IOException {

    private static final long serialVersionUID = -3913902031489277776L;

    private int code;
    private String errMsg;
    private Throwable causeThrowable;


    public BaseException() {
    }

    public BaseException(HttpResponseMessageCode httpResponseMessageCode) {
        this.errMsg = httpResponseMessageCode.getMsg();
    }

    public BaseException(String errMsg) {
        super(errMsg);
        this.errMsg = errMsg;
    }

    public BaseException(int code ,String errMsg) {
        super(errMsg);
        this.code=code;
        this.errMsg = errMsg;
    }

    public BaseException(Throwable throwable) {
        super(throwable);
        this.setCauseThrowable(throwable);
        this.errMsg = getErrMsg();
    }

    public BaseException(String errMsg, Throwable throwable) {
        super(errMsg, throwable);
        this.errMsg = errMsg;
        this.setCauseThrowable(throwable);
    }


    public String getErrMsg() {
        if (!StringUtils.isBlank(this.errMsg)) {
            return this.errMsg;
        } else {
            return this.causeThrowable != null ? this.causeThrowable.getMessage() : "";
        }
    }


    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    public void setCauseThrowable(Throwable throwable) {
        this.causeThrowable = this.getCauseThrowable(throwable);
    }

    private Throwable getCauseThrowable(Throwable t) {
        return t.getCause() == null ? t : this.getCauseThrowable(t.getCause());
    }

    public String toString() {
        return " ErrMsg:" + this.getErrMsg();
    }
}
