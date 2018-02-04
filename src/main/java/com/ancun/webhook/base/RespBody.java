package com.ancun.webhook.base;

/**
 * 返回信息
 *
 * @author jarvan
 * @since 2017年12月25日 15:59
 */
public class RespBody<T> {

    private int code = TdResponseInfo._100000.getCode();

    private String msg = TdResponseInfo._100000.getMsg();

    private T data;

    public RespBody(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespBody(TdResponseInfo enumInfo) {
        this.code = enumInfo.getCode();
        this.msg = enumInfo.getMsg();
    }

    @Deprecated
    public RespBody(TdResponseInfo enumInfo, T data) {
        this(enumInfo.getCode(), enumInfo.getMsg());
        this.data = data;
    }

    public RespBody(int code, String msg, T data) {
        this(code, msg);
        this.data = data;
    }

    public RespBody(T data) {
        this.data = data;
    }

    public RespBody() {
        this.data = data;
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

    @Override
    public String toString() {
        return "RespBody{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
