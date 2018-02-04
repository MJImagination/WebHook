package com.ancun.webhook.base;

import com.ancun.webhook.model.PublicityData;

import java.util.List;

/**
 * 返回信息
 *
 * @author jarvan
 * @since 2017年12月25日 15:59
 */
public class RespBodyLayUi<T> {

    private int code = TdResponseInfo._100000.getCode();

    private String msg = TdResponseInfo._100000.getMsg();

    private  long totalElements;

    private List<PublicityData> data;

    public RespBodyLayUi(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public RespBodyLayUi(TdResponseInfo enumInfo) {
        this.code = enumInfo.getCode();
        this.msg = enumInfo.getMsg();
    }

    @Deprecated
    public RespBodyLayUi(TdResponseInfo enumInfo, T data  ) {
        this(enumInfo.getCode(), enumInfo.getMsg());
    }

    public RespBodyLayUi(int code, String msg, T data) {
        this(code, msg);
    }

    public RespBodyLayUi(long totalElements,List<PublicityData> publicityData) {
        this.totalElements = totalElements;
        this.data = publicityData;
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

    public long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(long totalElements) {
        this.totalElements = totalElements;
    }

    public List<PublicityData> getData() {
        return data;
    }

    public void setData(List<PublicityData> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "RespBodyLayUi{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", totalElements=" + totalElements +
                ", data=" + data +
                '}';
    }
}
