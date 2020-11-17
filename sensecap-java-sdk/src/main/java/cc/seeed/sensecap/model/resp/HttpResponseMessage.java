package cc.seeed.sensecap.model.resp;

import cc.seeed.sensecap.exception.HttpResponseMessageCode;

import java.io.Serializable;

/**
 * @Author AG
 * @Description
 * @Date 2020/8/14 14:16
 * @Version V1.0
 */

public class HttpResponseMessage<T> implements Serializable {

    /**
     * 通用成功
     */
    public static final HttpResponseMessage success = new HttpResponseMessage(HttpResponseMessageCode.SUCCESS);

    /**
     * 通用失败
     */
    public static final HttpResponseMessage error = new HttpResponseMessage(HttpResponseMessageCode.ERROR);

    private int code;
    private String msg;
    private T data;

    public HttpResponseMessage(){

    }
    /**
     * 返回结果（成功、失败）
     *
     * @param result
     */
    public HttpResponseMessage(HttpResponseMessageCode result) {
        this.code = result.getCode();
        this.msg = result.getMsg();
    }

    /**
     * 返回成功结果，并携带数据
     *
     * @param data
     */
    public HttpResponseMessage(T data) {
        this.code = success.getCode();
        this.msg = success.getMsg();
        this.data = data;
    }

    public HttpResponseMessage(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public HttpResponseMessage(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public HttpResponseMessage(int code, String msg) {
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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
